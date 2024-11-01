package dot.javaFX.objects;

import dot.business.receipt.Receipt;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.SimpleDateFormat;
import java.util.Collections;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class ReceiptsValuesTableRow_Test {
    private ObservableList<ReceiptsValuesTableRow> receipList = FXCollections
            .observableArrayList();

            String[] dates = {"22.02.2024","30.01.2024", "05.03.2024","10.12.2023"};
    private void setRows() {
        try {
            ReceiptsValuesTableRow r1, r2, r3, r4;

            r1 = new ReceiptsValuesTableRow(1,
                    new Receipt(new SimpleDateFormat("dd.MM.yyyy").parse(dates[0]), "Aldi", 10.00), "Lebensmittel");
            r2 = new ReceiptsValuesTableRow(2, new SimpleDateFormat("dd.MM.yyyy").parse(dates[1]), "Edeka",
                    "Lebensmittel", 20.25);
            r3 = new ReceiptsValuesTableRow(3, new SimpleDateFormat("dd.MM.yyyy").parse(dates[2]), "McPaper",
                    "Buromaterial", 5.95);
            r4 = new ReceiptsValuesTableRow(4, new SimpleDateFormat("dd.MM.yyyy").parse(dates[3]), "Saturn",
                    "Sachmittel", 99.99);
            receipList.add(r1);
            receipList.add(r2);
            receipList.add(r3);
            receipList.add(r4);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }

    @Test
    public void test_CompareTo_withDifferentDate(){
          setRows();
        Collections.sort(receipList);
            String output ="";
            for (ReceiptsValuesTableRow r : receipList){
                output += r.getDate() + " ";
            }
            String ref = dates[3] + " " + dates[1] + " " + dates[0] + " " + dates[2] + " ";
            assertEquals(dates[3] , output);
    }


}
