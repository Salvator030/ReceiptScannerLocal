package dot.business.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.File;
import java.io.IOException;
import org.junit.jupiter.api.Test;
import dot.asserts.EPurpose;
import dot.business.handler.FileHandler;
import dot.javaFX.objects.ReceiptsValuesTableRow;
// import static dot.business.excel.FastexcelHelper.mergeDataOfSameMonth;

public class FastexcelHelper_Test {

    FastexcelHelper fastexcelHelper = new FastexcelHelper(new FileHandler());

    HashMap<String, ArrayList<ReceiptsValuesTableRow>> map = new HashMap<String, ArrayList<ReceiptsValuesTableRow>>();

    final String folder = "src/test/resources/";
    final String path1 = folder + "Kassenbons-Abrechnung-012024.xlsx";
    final String path2 = folder + "Kassenbons-Abrechnung-082024.xlsx";
    final String path3 = folder + "Kassenbons-Abrechnung-012025.xlsx";

    final List<ReceiptsValuesTableRow> inputList = new ArrayList<>(Arrays.asList(

            new ReceiptsValuesTableRow(0, "02.01.2024", "Edeka", EPurpose.LEBENSMITTEL, 2.10),
            new ReceiptsValuesTableRow(0, "04.01.2024", "Netto", EPurpose.LEBENSMITTEL, 5.09),
            new ReceiptsValuesTableRow(0, "01.08.2024", "Edeka", EPurpose.LEBENSMITTEL, 2.10),
            new ReceiptsValuesTableRow(0, "02.08.2024", "Netto", EPurpose.LEBENSMITTEL, 5.09),
            new ReceiptsValuesTableRow(0, "05.08.2024", "Aldi", EPurpose.LEBENSMITTEL, 20.01),
            new ReceiptsValuesTableRow(0, "01.01.2025", "Netto", EPurpose.LEBENSMITTEL, 5.09)));

    @Test
    public void spliReceiptRowsListByDate_ListWithDiverentRows_outputCorectMap() {

        String[] dates = {"01.2024","08.2024","01.2025"};
    Set<String> expected = new HashSet<>();
    expected.addAll(Arrays.asList(dates));

        ArrayList<ReceiptsValuesTableRow> rows = new ArrayList<ReceiptsValuesTableRow>(Arrays.asList(inputList.get(1)));
        map.put(inputList.get(0).getDate(), rows);
        rows = new ArrayList<ReceiptsValuesTableRow>(Arrays.asList(inputList.get(3), inputList.get(4)));
        map.put(inputList.get(2).getDate(), rows);
        rows = new ArrayList<ReceiptsValuesTableRow>(Arrays.asList(inputList.get(5)));
        map.put(inputList.get(5).getDate(), rows);
        // map.put(inputList.get(7).getDate(), new ArrayList<>());

        HashMap<String, List<ReceiptsValuesTableRow>> ouput = fastexcelHelper.spliReceiptRowsListByDate(inputList);
        assertEquals(expected, ouput.keySet());
    }

    
    @Test
    public void writeReceiptsToExcelFiles_withAListOfReceiptRows_FilesExisist() {

        try {

            assertTrue(!Files.exists(Path.of(path1)));
            assertTrue(!Files.exists(Path.of(path2)));
            assertTrue(!Files.exists(Path.of(path3)));
            fastexcelHelper.fileHandler.setOutputFolder(new File(folder));
            fastexcelHelper.writeReceiptsToExcelFiles(inputList);

            assertTrue(Files.exists(Path.of(path1)));
            assertTrue(Files.exists(Path.of(path2)));
            assertTrue(Files.exists(Path.of(path3)));

            Files.delete(Path.of(path1));
            Files.delete(Path.of(path2));
            Files.delete(Path.of(path3));

        } catch (NumberFormatException | IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void fetchTableRowsFromFilesWhenExist_GetExistetFileAndCreatFileWhileNotExist() {

        String testFilePath = folder + "Kassenbons-Abrechnung-012000.xlsx";

        HashMap<String, List<ReceiptsValuesTableRow>> exceptetMap = new HashMap<>();
        exceptetMap.put("012000", new ArrayList<ReceiptsValuesTableRow>());
        exceptetMap.get("012000")
                .addAll(Arrays.asList(new ReceiptsValuesTableRow[] {new ReceiptsValuesTableRow(1, "01.01.2000", "Edeka", EPurpose.LEBENSMITTEL, 2.10),
                new ReceiptsValuesTableRow(2, "02.01.2000", "Netto", EPurpose.LEBENSMITTEL, 5.09),
                new ReceiptsValuesTableRow(3, "05.01.2000", "Aldi", EPurpose.LEBENSMITTEL, 20.01)}));

        HashMap<String, Path> pathMap = new HashMap<String, Path>();
        pathMap.put("012000", Path.of(testFilePath));
        Set<String> keys = new HashSet<String>();
        keys.add("012000");
  

        HashMap<String, List<ReceiptsValuesTableRow>> resMap = fastexcelHelper.fetchTableRowsFromFilesWhenExist(keys,
                pathMap);

        ReceiptsValuesTableRow r = resMap.get("012000").get(0);
        ReceiptsValuesTableRow r2 = resMap.get("012000").get(1);
        ReceiptsValuesTableRow r3 = resMap.get("012000").get(2);
 
        assertEquals("01.01.2000 Edeka 2.1", r.getDate() + " " + r.getShopName() + " " + r.getSumm());
        assertEquals("02.01.2000 Netto 5.09", r2.getDate() + " " + r2.getShopName() + " " + r2.getSumm());
        assertEquals("05.01.2000 Aldi 20.01", r3.getDate() + " " + r3.getShopName() + " " + r3.getSumm());
      
    

    }

}
