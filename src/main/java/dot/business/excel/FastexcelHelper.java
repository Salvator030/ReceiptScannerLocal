package dot.business.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import org.dhatim.fastexcel.reader.ReadableWorkbook;
import org.dhatim.fastexcel.reader.Row;
import org.dhatim.fastexcel.Workbook;
import org.dhatim.fastexcel.Worksheet;
import org.dhatim.fastexcel.reader.Cell;
import org.dhatim.fastexcel.reader.Sheet;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.Date;

import dot.business.handler.FileHandler;
import dot.business.receipt.Receipt;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.collections.transformation.FilteredList;

public class FastexcelHelper {

    FileHandler fileHandler = new FileHandler();

    public Map<Integer, List<String>> readExcel(String fileLocation) {
        Map<Integer, List<String>> data = new HashMap<>();
        try {
            try (FileInputStream file = new FileInputStream(fileLocation);
                    ReadableWorkbook wb = new ReadableWorkbook(file)) {
                Sheet sheet = wb.getFirstSheet();
                try (Stream<Row> rows = sheet.openStream()) {
                    rows.forEach(r -> {
                        data.put(r.getRowNum(), new ArrayList<>());

                        for (Cell cell : r) {
                            data.get(r.getRowNum()).add(cell.getRawValue());
                        }
                    });
                }
            }

        } catch (Exception e) {
            System.err.println(e);
        }
        return data;
    }

    private void createTableHead(Worksheet ws) {
        ws.width(0, 15);
        ws.width(1, 25);
        ws.width(2, 15);

        ws.range(0, 0, 0, 2).style().fontName("Arial").fontSize(16).bold().fillColor("3366FF").set();
        ws.value(0, 0, "Datum");
        ws.value(0, 1, "Laden");
        ws.value(0, 2, "Summe");
    }

    private void createSummRow(Worksheet ws, int rowNumber, double summ) {
        ws.range(rowNumber, 0, rowNumber, 2).style().fontSize(14).bold().set();
        ws.value(rowNumber, 0, "Gesammt:");
        ws.value(rowNumber, 2, summ);
    }

    private double getTotalSumm(List<ReceiptsValuesTableRow> receipts) {
        double summ = 0;
        for (ReceiptsValuesTableRow r : receipts) {
            summ += Double.parseDouble(r.getSumm());
        }
        return summ;
    }

    private List<ReceiptsValuesTableRow> parseDataToReceiptValuesTableRowList(Map<Integer, List<String>> data)
            throws NumberFormatException, ParseException {
        Integer[] keySet = data.keySet().toArray(new Integer[0]);
        int lastRecepitIndex = keySet.length - 2;
        List<ReceiptsValuesTableRow> receiptsList = new ArrayList<>();
        for (int i = 1; i <= lastRecepitIndex; i++) {
            receiptsList.add(new ReceiptsValuesTableRow(receiptsList.size() + 1, data.get(keySet[i]).get(0),
                    data.get(keySet[i]).get(1), "null", Double.parseDouble(data.get(keySet[i]).get(2))));
        }

        return receiptsList;
    }

    private void createTableBody(Worksheet ws, List<ReceiptsValuesTableRow> receiptsList)
            throws NumberFormatException, ParseException {
System.out.println("createTableBody");
      
        int rowNumber = 2;
        for (ReceiptsValuesTableRow r : receiptsList) {
            ws.range(rowNumber, 0, rowNumber, 2).style().wrapText(true).set();
            ws.value(rowNumber, 0, r.getDate());
            ws.value(rowNumber, 1, r.getShopName());
            ws.value(rowNumber, 2, r.getSumm());
            ++rowNumber;
        }
        ++rowNumber;
        createSummRow(ws, rowNumber, getTotalSumm(receiptsList));
    }

    private void writeRowListToFile(Path fullOutputFilePath, List<ReceiptsValuesTableRow> receipts) throws IOException {
        try (OutputStream os = Files.newOutputStream(fullOutputFilePath);
                Workbook wb = new Workbook(os, "MyApplication", "1.0")) {
            Worksheet ws = wb.newWorksheet("Sheet 1");
            createTableHead(ws);
            createTableBody(ws, receipts);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void mergeDataOfSameMonth(List<ReceiptsValuesTableRow> targetList,
            List<ReceiptsValuesTableRow> sourceList) {
        for (ReceiptsValuesTableRow row : targetList) {
            sourceList.removeIf(r -> r.getDate().equalsIgnoreCase(row.getDate())
                    && r.getPurpose().equalsIgnoreCase(row.getPurpose())
                    && r.getShopName().equalsIgnoreCase(row.getShopName())
                    && r.getSumm().equalsIgnoreCase(row.getSumm()));

        }
        targetList.addAll(sourceList);
        Collections.sort(targetList);

    }

    private void mergeMapOfSomeMonth(HashMap<String, List<ReceiptsValuesTableRow>> targetMap,
            HashMap<String, List<ReceiptsValuesTableRow>> sourceMap, Set<String> keys) {

        for (String key : keys) {
            if (sourceMap.containsKey(key)) {
                mergeDataOfSameMonth(targetMap.get(key), sourceMap.get(key));
            }
        }
    }

    public HashMap<String, List<ReceiptsValuesTableRow>> spliReceiptRowsListByDate(List<ReceiptsValuesTableRow> list) {
        HashMap<String, List<ReceiptsValuesTableRow>> map = new HashMap<>();
        String currentDate = list.get(0).getDate();
        int listSize = list.size();
        for (int i = 0; i < listSize; i++) {
            if (list.get(i).getDate().length() == 7) {
                currentDate = list.get(i).getDate();
                map.put(currentDate, new ArrayList<ReceiptsValuesTableRow>());
            } else {
                map.get(currentDate).add(list.get(i));
            }
        }
        return map;

    }

    public HashMap<String, List<ReceiptsValuesTableRow>> fetchTableRowsFromFilesWhenExist(Set<String> keys,
            HashMap<String, Path> datePathMap) {
        datePathMap = fileHandler.getExcelFilesPathesToReadIn(keys);
        HashMap<String, List<ReceiptsValuesTableRow>> dateRowsMap = new HashMap<>();
        for (String key : keys) {
            if (fileHandler.checkExistFile(datePathMap.get(key))) {
                try {
                    dateRowsMap.put(key,
                            parseDataToReceiptValuesTableRowList(readExcel(datePathMap.get(key).toString())));
                } catch (NumberFormatException | ParseException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return dateRowsMap;
    }

    private void writeReceiptMapToExcelFiles(HashMap<String, List<ReceiptsValuesTableRow>> receiptRowsForMonthMap,
            HashMap<String, Path> pathMap, Set<String> keys) {
        for (String key : keys) {
            try {
                writeRowListToFile(pathMap.get(key), receiptRowsForMonthMap.get(key));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public void writeReceiptsToExcelFiles( List<ReceiptsValuesTableRow> receiptList)

         throws IOException, NumberFormatException, ParseException {
            System.out.println("writeReceiptsToExcelFiles");
            System.out.println("\tspliReceiptRowsListByDate");
        HashMap<String, List<ReceiptsValuesTableRow>> receiptRowsForMonthMap = spliReceiptRowsListByDate(receiptList);
        System.out.println("\treceiptRowsForMonthMap");
        Set<String> keys = receiptRowsForMonthMap.keySet();
        System.out.println("\tgetExcelFilesPathesToReadIn");
        HashMap<String, Path> pathMap = fileHandler.getExcelFilesPathesToReadIn(keys);
        HashMap<String, List<ReceiptsValuesTableRow>> dateRowsMap = fetchTableRowsFromFilesWhenExist(keys, pathMap);
        mergeMapOfSomeMonth(receiptRowsForMonthMap, dateRowsMap, keys);
        writeReceiptMapToExcelFiles(receiptRowsForMonthMap, pathMap, keys);
        

    }
}
