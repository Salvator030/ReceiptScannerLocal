package dot.business.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dot.javaFX.objects.ReceiptsValuesTableRow;

public class FastexcelHelper_Test {

    FastexcelHelper fastexcelHelper = new FastexcelHelper();

    HashMap<String, ArrayList<ReceiptsValuesTableRow>> map = new HashMap<String, ArrayList<ReceiptsValuesTableRow>>();
    List<ReceiptsValuesTableRow> rowList = new ArrayList<>(Arrays.asList(
        new ReceiptsValuesTableRow("01.2024"),
        new ReceiptsValuesTableRow("02.01.2024"),
        new ReceiptsValuesTableRow("02.2024"),
        new ReceiptsValuesTableRow("12.02.2024"),
        new ReceiptsValuesTableRow("15.02.2024"),
        new ReceiptsValuesTableRow("05.2024"),
        new ReceiptsValuesTableRow("05.05.2024"),
        new ReceiptsValuesTableRow("01.2025")));

      
    

    @Test
    public void spliReceiptRowsListByDate_ListWithDiverentRows_outputCorectMap() {

        ArrayList<ReceiptsValuesTableRow> rows = new ArrayList<ReceiptsValuesTableRow>(Arrays.asList(rowList.get(1)));
        map.put(rowList.get(0).getDate(), rows);
        rows = new ArrayList<ReceiptsValuesTableRow>(Arrays.asList(rowList.get(3), rowList.get(4)));
        map.put(rowList.get(2).getDate(), rows);
        rows = new ArrayList<ReceiptsValuesTableRow>(Arrays.asList(rowList.get(6)));
        map.put(rowList.get(5).getDate(), rows);
        map.put(rowList.get(7).getDate(), new ArrayList<>());

        HashMap<String, List<ReceiptsValuesTableRow>> ouput = fastexcelHelper.spliReceiptRowsListByDate(rowList);
        assertEquals(map, ouput);
    }

    @Test
    public void mergeData_toDiverntLists_outputCorectList(){
        List<ReceiptsValuesTableRow> newList = new ArrayList<>(Arrays.asList(
            new ReceiptsValuesTableRow("01.2024"),
            new ReceiptsValuesTableRow("02.01.2024"),
   
            new ReceiptsValuesTableRow("03.2024"),
            new ReceiptsValuesTableRow("16.03.2024"),
            new ReceiptsValuesTableRow("30.03.2024"),
            new ReceiptsValuesTableRow("05.2024"),
            new ReceiptsValuesTableRow("15.05.2024")));

            List<ReceiptsValuesTableRow> acceptet = new ArrayList<>(Arrays.asList(
                new ReceiptsValuesTableRow("01.2024"),
                new ReceiptsValuesTableRow("02.01.2024"),
                new ReceiptsValuesTableRow("02.2024"),
                new ReceiptsValuesTableRow("12.02.2024"),
                new ReceiptsValuesTableRow("15.02.2024"),
                new ReceiptsValuesTableRow("03.2024"),
                new ReceiptsValuesTableRow("16.03.2024"),
                new ReceiptsValuesTableRow("30.03.2024"),
                new ReceiptsValuesTableRow("05.2024"),
                new ReceiptsValuesTableRow("15.05.2024"),
                new ReceiptsValuesTableRow("01.2025")));

                fastexcelHelper.mergeData(newList, rowList);
                assertEquals(acceptet, newList);
    }
}
