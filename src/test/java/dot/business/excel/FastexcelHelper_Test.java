package dot.business.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Files;
import java.nio.file.Path;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
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

    final   String folder = "src/test/resources/";
  final  String path1 =  folder+ "Kassenbons-Abrechnung-012024.xlsx";
   final String path2 = folder+ "Kassenbons-Abrechnung-082024.xlsx";
    final String path3 = folder+ "Kassenbons-Abrechnung-012025.xlsx";
    @Test
    public void spliReceiptRowsListByDate_ListWithDiverentRows_outputCorectMap() {

        List<ReceiptsValuesTableRow> rowList = new ArrayList<>(Arrays.asList(
                new ReceiptsValuesTableRow("01.2024"),
                new ReceiptsValuesTableRow("02.01.2024"),
                new ReceiptsValuesTableRow("02.2024"),
                new ReceiptsValuesTableRow("12.02.2024"),
                new ReceiptsValuesTableRow("15.02.2024"),
                new ReceiptsValuesTableRow("05.2024"),
                new ReceiptsValuesTableRow("05.05.2024"),
                new ReceiptsValuesTableRow("01.2025")));

        ArrayList<ReceiptsValuesTableRow> rows = new ArrayList<ReceiptsValuesTableRow>(Arrays.asList(rowList.get(1)));
        map.put(rowList.get(0).getDate(), rows);
        rows = new ArrayList<ReceiptsValuesTableRow>(Arrays.asList(rowList.get(3), rowList.get(4)));
        map.put(rowList.get(2).getDate(), rows);
        rows = new ArrayList<ReceiptsValuesTableRow>(Arrays.asList(rowList.get(6)));
        map.put(rowList.get(5).getDate(), rows);
        map.put(rowList.get(7).getDate(), new ArrayList<>());

        HashMap<String, List<ReceiptsValuesTableRow>> ouput = fastexcelHelper.spliReceiptRowsListByDate(rowList);
        assertEquals(map.keySet(), ouput.keySet());
    }

    private String datasToString(List<ReceiptsValuesTableRow> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ReceiptsValuesTableRow row : list) {
            stringBuilder.append(row.getDate() + " ");
        }

        return stringBuilder.toString();

    }

    @Test
    public void writeReceiptsToExcelFiles_withAListOfReceiptRows_FilesExisist(){
      
         List<ReceiptsValuesTableRow> inputList = new ArrayList<>(Arrays.asList(
 
         new ReceiptsValuesTableRow(0,"02.01.2024","Edeka",EPurpose.LEBENSMITTEL,2.10),
         new ReceiptsValuesTableRow(0,"04.01.2024","Netto",EPurpose.LEBENSMITTEL,5.09),
         new ReceiptsValuesTableRow(0,"01.08.2024","Edeka",EPurpose.LEBENSMITTEL,2.10),
         new ReceiptsValuesTableRow(0,"02.08.2024","Netto",EPurpose.LEBENSMITTEL,5.09),
         new ReceiptsValuesTableRow(0,"05.08.2024","Aldi",EPurpose.LEBENSMITTEL,20.01),
         new ReceiptsValuesTableRow(0,"01.01.2025","Netto",EPurpose.LEBENSMITTEL,5.09)));

         try {

            assertTrue(!Files.exists(Path.of( path1)));
            assertTrue(!Files.exists(Path.of( path2)));
            assertTrue(!Files.exists(Path.of( path3)));
            fastexcelHelper.fileHandler.setOutputFolder(new File(folder));
            fastexcelHelper.writeReceiptsToExcelFiles(inputList);

            assertTrue(Files.exists(Path.of( path1)));
            assertTrue(Files.exists(Path.of( path2)));
            assertTrue(Files.exists(Path.of( path3)));

          

        } catch (NumberFormatException | IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
       
    }

    

    @Test
    public void fetchTableRowsFromFilesWhenExist_GetExistetFileAndCreatFileWhileNotExist(){
        String testFilePath  =  folder+ "Kassenbons-Abrechnung-082024.lsx"; 
        HashMap<String,List<ReceiptsValuesTableRow>> exceptetMap = new HashMap<>();
        exceptetMap.put("082024",new ArrayList<ReceiptsValuesTableRow>());
        exceptetMap.get("082024").add(new ReceiptsValuesTableRow(0, "08.2024", "blume2000 se", EPurpose.SACHMITTEL, 11.28));    
        HashMap<String,Path> pathMap = new HashMap<String,Path>();
        pathMap.put("082024",  Path.of(testFilePath));     
        Set<String> keys = new HashSet<String>();
            keys.add("082024");
        keys.add("022024");
        HashMap<String, List<ReceiptsValuesTableRow>> resMap = fastexcelHelper.fetchTableRowsFromFilesWhenExist(keys, pathMap);
       ReceiptsValuesTableRow r = resMap.get("082024").get(0);
        assertTrue(Files.exists(Path.of(path1)));
        assertEquals("01.08.2024 blume2000 se 11.28" ,r.getDate() + " " + r.getShopName() + " " + r.getSumm());
     
        try {
            Files.deleteIfExists(Path.of( path1));
        Files.deleteIfExists(Path.of( path2));
        Files.deleteIfExists(Path.of( path3));

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

  
    // private Method getMergeDataOfSameMonth() throws NoSuchMethodException {
    // Method method =
    // FastexcelHelper.class.getDeclaredMethod("mergeDataOfSameMonth", List.class,
    // List.class);
    // method.setAccessible(true);
    // return method;
    // }

    // @Test
    // public void mergeDataOfSameMonth_toDiverntLists_outputCorectList() {

    // List<ReceiptsValuesTableRow> rowList = new ArrayList<>(Arrays.asList(
    // new ReceiptsValuesTableRow("01.2024"),
    // new ReceiptsValuesTableRow("02.01.2024"),
    // new ReceiptsValuesTableRow("03.01.2024"),
    // new ReceiptsValuesTableRow("10.01.2024"),
    // new ReceiptsValuesTableRow("15.01.2024"),
    // new ReceiptsValuesTableRow("16.01.2024"),
    // new ReceiptsValuesTableRow("20.01.2024"),
    // new ReceiptsValuesTableRow("21.01.2024")));

    // List<ReceiptsValuesTableRow> newList = new ArrayList<>(Arrays.asList(
    // new ReceiptsValuesTableRow("01.2024"),
    // new ReceiptsValuesTableRow("01.01.2024"),
    // new ReceiptsValuesTableRow("03.01.2024"),
    // new ReceiptsValuesTableRow("04.01.2024"),
    // new ReceiptsValuesTableRow("10.01.2024"),
    // new ReceiptsValuesTableRow("11.01.2024"),
    // new ReceiptsValuesTableRow("15.01.2024")));

    // List<ReceiptsValuesTableRow> acceptetList = new ArrayList<>(Arrays.asList(

    // new ReceiptsValuesTableRow("01.2024"),
    // new ReceiptsValuesTableRow("01.01.2024"),
    // new ReceiptsValuesTableRow("02.01.2024"),
    // new ReceiptsValuesTableRow("03.01.2024"),
    // new ReceiptsValuesTableRow("04.01.2024"),
    // new ReceiptsValuesTableRow("10.01.2024"),
    // new ReceiptsValuesTableRow("11.01.2024"),
    // new ReceiptsValuesTableRow("15.01.2024"),
    // new ReceiptsValuesTableRow("16.01.2024"),
    // new ReceiptsValuesTableRow("20.01.2024"),
    // new ReceiptsValuesTableRow("21.01.2024")));

    // getMergeDataOfSameMonth(newList, rowList);

    // String newListString = datasToString(newList);
    // String acceptetString = datasToString(acceptetList);
    // assertEquals(acceptetString, newListString);
    // }
}
