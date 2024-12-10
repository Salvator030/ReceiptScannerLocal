package dot.javaFX.objects;

import dot.asserts.EPurpose;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Collections;
import org.junit.jupiter.api.Test;

public class ReceiptsValuesTableRow_Test {
    private ObservableList<ReceiptsValuesTableRow> receipList = FXCollections
            .observableArrayList();

    String[] dates = { "22.02.2024", "30.01.2024", "05.03.2024", "10.12.2023", "06.2024" };

    private void setRows() {
        try {
            ReceiptsValuesTableRow r1, r2, r3, r4, r5;

            r1 = new ReceiptsValuesTableRow(1,
                   dates[0], "Aldi", EPurpose.LEBENSMITTEL,10.00);
            r2 = new ReceiptsValuesTableRow(2, (dates[1]), "Edeka",
                    EPurpose.LEBENSMITTEL, 20.25);
            r3 = new ReceiptsValuesTableRow(3, (dates[2]), "McPaper",
                    EPurpose.BÜROMATERIAL, 5.95);
            r4 = new ReceiptsValuesTableRow(4,(dates[3]), "Saturn",
                    EPurpose.SACHMITTEL, 99.99);
            r5 = new ReceiptsValuesTableRow(dates[4]);
            receipList.add(r1);
            receipList.add(r2);
            receipList.add(r3);
            receipList.add(r4);
            receipList.add(r5);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @Test
    public void test_CompareTo_withDifferentDate() {
        setRows();
        Collections.sort(receipList);
        String output = "";
        for (ReceiptsValuesTableRow r : receipList) {
            output += r.getDate() + " ";
        }
        String strB = dates[3] + " " + dates[1] + " " + dates[0] + " " + dates[2] + " " + dates[4] + " ";
        assertEquals(strB, output);
    }

}
