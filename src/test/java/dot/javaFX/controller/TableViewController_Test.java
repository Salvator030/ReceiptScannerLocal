package dot.javaFX.controller;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import dot.javaFX.controller.TableViewController;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TableViewController_Test {

    private ObservableList<ReceiptsValuesTableRow> tableList;
    private List<ReceiptsValuesTableRow> testReceiptsRowList;
    private final String equals = "02.2024 11.02.2024 03.2024 22.03.2024 04.2024 01.04.2024 05.2024 30.05.2024 ";

    // TODO check the best practis for test javaFx Viwe Controller, or thing about
    // refactoring( implement Manager class for contoller to handel buisnes logik )
    private void addDateRow(ReceiptsValuesTableRow receiptsValuesTableRow,
            ObservableList<ReceiptsValuesTableRow> list) {
        String serachFor = receiptsValuesTableRow.getDate().substring(3);
        List<ReceiptsValuesTableRow> filtredList = list
                .filtered(r -> r.getDate().equalsIgnoreCase(serachFor));
        if (filtredList.isEmpty()) {
            int i = list.indexOf(receiptsValuesTableRow);
            list.add(i, new ReceiptsValuesTableRow(serachFor));
        }
    }

    public void addRow(ReceiptsValuesTableRow receiptsValuesTableRow,
            ObservableList<ReceiptsValuesTableRow> tableList) {
        tableList.add(receiptsValuesTableRow);
        Collections.sort(tableList);
        addDateRow(receiptsValuesTableRow, tableList);
        // receiptValuesTable.setItems(tableList);
    }

    private void init() {
        tableList = FXCollections
                .observableArrayList();
        testReceiptsRowList = new ArrayList<>(
                Arrays.asList(new ReceiptsValuesTableRow("11.02.2024"), new ReceiptsValuesTableRow("22.03.2024"),
                        new ReceiptsValuesTableRow("01.04.2024"), new ReceiptsValuesTableRow("30.05.2024")));

    }

    @BeforeEach
    public void clean() {
        tableList = FXCollections
                .observableArrayList();
    }

    @Test
    public void test_addDataRow_output() {
        init();
        tableList.add(testReceiptsRowList.get(0));
        addDateRow(testReceiptsRowList.get(0), tableList);
        tableList.add(testReceiptsRowList.get(1));
        addDateRow(testReceiptsRowList.get(1), tableList);
        tableList.add(testReceiptsRowList.get(2));
        addDateRow(testReceiptsRowList.get(2), tableList);
        tableList.add(testReceiptsRowList.get(3));
        addDateRow(testReceiptsRowList.get(3), tableList);

        String out = "";
        for (ReceiptsValuesTableRow r : tableList) {
            out += r.getDate() + " ";
        }

        assertEquals(equals, out);
    }

    @Test
    public void test_addRow_output() {
init();
        addRow(testReceiptsRowList.get(2), tableList);
        addRow(testReceiptsRowList.get(0), tableList);
        addRow(testReceiptsRowList.get(3), tableList);
        addRow(testReceiptsRowList.get(1), tableList);

        String out = "";
        for (ReceiptsValuesTableRow r : tableList) {
            out += r.getDate() + " ";
        }

        assertEquals(equals, out);

    }
}
