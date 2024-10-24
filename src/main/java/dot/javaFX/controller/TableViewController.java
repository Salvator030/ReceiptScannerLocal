package dot.javaFX.controller;

import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

public class TableViewController {

     @FXML
     TableView<ReceiptsValuesTableRow> receiptValuesTable = null;

     ObservableList<ReceiptsValuesTableRow> observableArrayList = FXCollections.observableArrayList();

     @FXML
     public void addRow(ReceiptsValuesTableRow receiptsValuesTableRow){
        observableArrayList.add(receiptsValuesTableRow);
        receiptValuesTable.setItems(observableArrayList);
     }

    
}
