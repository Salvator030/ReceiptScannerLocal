package dot.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

import dot.receipt.Receipt;

public class FastexcelHelper {

    public Map<Integer, List<String>> readExcel(String fileLocation) throws IOException {
        Map<Integer, List<String>> data = new HashMap<>();

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

    // Crete the tabel Body when the excelFile not exist
    private void createTableBody(Worksheet ws, Receipt receipt) {

        ws.range(2, 0, 2, 2).style().wrapText(true).set();
        DateFormat dataFormat = new SimpleDateFormat("dd.MM.yyyy");
        ws.value(2, 0, dataFormat.format(receipt.getDate()));
        ws.value(2, 1, receipt.getShopName());
        ws.value(2, 2, receipt.getSumm());
        ws.range(4, 0, 4, 1).style().fontSize(14).bold().set();
        ws.value(4, 0, "Gesammt:");
        ws.value(4, 1, receipt.getSumm());

    }

    // Crete the tabel Body when the excelFile exist.
    // The file is rewritten. Previous entries are transferred,
    // the new receipt is added and the total is recalculated
    private void createTableBody(Worksheet ws, Map<Integer, List<String>> data, Receipt receipt) {
        Integer[] keySet = data.keySet().toArray(new Integer[0]);
        int lastRecepitIndex = keySet[keySet.length - 2]-1;
        double summ = 0.0d;

        // Write Previus entries
        for (int i = 2; i <= lastRecepitIndex; i++) {
            ws.range(keySet[i], 0, keySet[i], 2).style().wrapText(true).set();
            ws.value(keySet[i], 0, data.get(keySet[i-1]).get(0));
            ws.value(keySet[i], 1, data.get(keySet[i-1]).get(1));
            ws.value(keySet[i], 2, data.get(keySet[i-1]).get(2));
            summ += Double.parseDouble(data.get(keySet[i-1]).get(2));
        }

        // Write current Receipt
        lastRecepitIndex += 1;
        ws.range(lastRecepitIndex, 0, lastRecepitIndex , 2).style().wrapText(true).set();
        DateFormat dataFormat = new SimpleDateFormat("dd.MM.yyyy");
        ws.value(lastRecepitIndex, 0, dataFormat.format(receipt.getDate()));
        ws.value(lastRecepitIndex, 1, receipt.getShopName());
        ws.value(lastRecepitIndex, 2, receipt.getSumm());
   
        //Write total
        summ += receipt.getSumm();
        lastRecepitIndex += 2;
        ws.range(lastRecepitIndex, 0, lastRecepitIndex, 2).style().fontSize(14).bold().set();
        ws.value(lastRecepitIndex, 0, "Gesammt:");
        ws.value(lastRecepitIndex, 2, summ);

    }

    private void createNewExcel(String fileLocation, Receipt receipt) throws IOException {
        try (OutputStream os = Files.newOutputStream(Paths.get(fileLocation));
                Workbook wb = new Workbook(os, "MyApplication", "1.0")) {
            Worksheet ws = wb.newWorksheet("Sheet 1");
            createTableHead(ws);
            createTableBody(ws, receipt);

        }
    }

    private void editExcel(String fileLocation, Receipt receipt) throws IOException {
        Map<Integer, List<String>> data = readExcel(fileLocation);
        try (OutputStream os = Files.newOutputStream(Paths.get(fileLocation));
                Workbook wb = new Workbook(os, "MyApplication", "1.0")) {
            Worksheet ws = wb.newWorksheet("Sheet 1");

            createTableHead(ws);
            createTableBody(ws, data, receipt);
        }

    }

    public void writeExcel(String fileName, Receipt receipt) throws IOException, NumberFormatException, ParseException {
        File currDir = new File(".");
        String path = currDir.getAbsolutePath();
        String fileLocation = path.substring(0, path.length() - 1) + fileName + ".xlsx";

        if (new File(fileLocation).exists()) {
            editExcel(fileLocation, receipt);

        } else {
            createNewExcel(fileLocation, receipt);
        }

    }
}

// private List<Receipt> parseDataToReceiptList(Map<Integer, List<String>> data)
// throws NumberFormatException, ParseException {
// List<Receipt> receiptList = new ArrayList<>();
// int mapSize = data.size();
// for (int i = 1; i < mapSize; i++) {
// List<String> row = data.get(i);
// receiptList.add(new Receipt(new
// SimpleDateFormat("dd.MM.yyyy").parse(row.get(0)), row.get(1),
// Double.parseDouble(row.get(2))));
// }
// return receiptList;
// }

// private double getSummFromData(Map<Integer, List<String>> data) {
// Integer[] keySet = data.keySet().toArray(new Integer[0]);
// return Double.parseDouble(data.get(keySet[keySet.length - 1]).get(1));

// }