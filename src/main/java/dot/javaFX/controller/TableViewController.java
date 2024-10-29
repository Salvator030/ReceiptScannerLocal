package dot.javaFX.controller;

import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class TableViewController {

   @FXML
   private TableView<ReceiptsValuesTableRow> receiptValuesTable = null;

   @FXML
   private TableColumn<ReceiptsValuesTableRow, String> dateColumn = null;

   @FXML
   private TableColumn<ReceiptsValuesTableRow, String> shopNameColumn = null;

   @FXML
   private TableColumn<ReceiptsValuesTableRow, String> purposeColumn = null;
   
   @FXML
   private TableColumn<ReceiptsValuesTableRow, String> summColumn = null;

   ObservableList<ReceiptsValuesTableRow> observableArrayList = FXCollections.observableArrayList();
   ObservableList<String> purposeOptions = FXCollections.observableArrayList("Option 1", "Option 2", "Option 3",
         "null");
   ComboBoxTableCell<ReceiptsValuesTableRow, String> comboBoxTableCell = new ComboBoxTableCell<>(purposeOptions);

   @FXML
   public void initialize() {
      receiptValuesTable.setEditable(true);
      dateColumn.setCellFactory(TextFieldTableCell.<ReceiptsValuesTableRow>forTableColumn());

      dateColumn.setOnEditCommit(event -> {
         final String value = event.getNewValue() != null ?
         event.getNewValue() : event.getOldValue();
         ((ReceiptsValuesTableRow) event.getTableView().getItems()
            .get(event.getTablePosition().getRow())).setDate(value);
      });
      shopNameColumn.setCellFactory(TextFieldTableCell.<ReceiptsValuesTableRow>forTableColumn());
      purposeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(purposeOptions));
      summColumn.setCellFactory(TextFieldTableCell.<ReceiptsValuesTableRow>forTableColumn());

   }

   @FXML
   public void addRow(ReceiptsValuesTableRow receiptsValuesTableRow) {
      observableArrayList.add(receiptsValuesTableRow);
      receiptValuesTable.setItems(observableArrayList);
   }

}
