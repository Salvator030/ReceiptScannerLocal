package dot.business.excel;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import dot.javaFX.objects.ReceiptsValuesTableRow;
import dot.javaFX.objects.ReceiptsValuesTableRow_Test;

public class FastexcelHelper_Test {

    FastexcelHelper fastexcelHelper = new FastexcelHelper();

    HashMap<String, ArrayList<ReceiptsValuesTableRow>> map = new HashMap<String, ArrayList<ReceiptsValuesTableRow>>();

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
        assertEquals(map, ouput);
    }

    private String datasToString(List<ReceiptsValuesTableRow> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (ReceiptsValuesTableRow row : list) {
            stringBuilder.append(row.getDate() + " ");
        }

        return stringBuilder.toString();

    }

    @Test
    public void mergeData_toDiverntLists_outputCorectList() {

        List<ReceiptsValuesTableRow> rowList = new ArrayList<>(Arrays.asList(
            new ReceiptsValuesTableRow("01.2024"),
            new ReceiptsValuesTableRow("02.01.2024"),
            new ReceiptsValuesTableRow("03.01.2024"),
            new ReceiptsValuesTableRow("10.01.2024"),
            new ReceiptsValuesTableRow("15.01.2024"),
            new ReceiptsValuesTableRow("16.01.2024"),
            new ReceiptsValuesTableRow("20.01.2024"),
            new ReceiptsValuesTableRow("21.01.2024")));

        List<ReceiptsValuesTableRow> newList = new ArrayList<>(Arrays.asList(
                new ReceiptsValuesTableRow("01.2024"),
                new ReceiptsValuesTableRow("01.01.2024"),
                new ReceiptsValuesTableRow("03.01.2024"),
                new ReceiptsValuesTableRow("04.01.2024"),
                new ReceiptsValuesTableRow("10.01.2024"),
                new ReceiptsValuesTableRow("11.01.2024"),
                new ReceiptsValuesTableRow("15.01.2024")));

        List<ReceiptsValuesTableRow> acceptetList = new ArrayList<>(Arrays.asList(
            
                new ReceiptsValuesTableRow("01.2024"),
                new ReceiptsValuesTableRow("01.01.2024"),
                new ReceiptsValuesTableRow("02.01.2024"),
                new ReceiptsValuesTableRow("03.01.2024"),
                new ReceiptsValuesTableRow("04.01.2024"),
                new ReceiptsValuesTableRow("10.01.2024"),
                new ReceiptsValuesTableRow("11.01.2024"),
                new ReceiptsValuesTableRow("15.01.2024"),
                new ReceiptsValuesTableRow("16.01.2024"),
                new ReceiptsValuesTableRow("20.01.2024"),
                new ReceiptsValuesTableRow("21.01.2024")));

        fastexcelHelper.mergeData(newList, rowList);

        String newListString = datasToString(newList);
        String acceptetString = datasToString(acceptetList);
        assertEquals(acceptetString, newListString);
    }
}
