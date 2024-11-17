package dot.javaFX.controller;

import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.ComboBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;

public class TableViewController {

   @FXML
   private TableView<ReceiptsValuesTableRow> receiptsTable = null;

   @FXML
   private TableColumn<ReceiptsValuesTableRow, String> dateColumn = null;

   @FXML
   private TableColumn<ReceiptsValuesTableRow, String> shopNameColumn = null;

   @FXML
   private TableColumn<ReceiptsValuesTableRow, String> purposeColumn = null;

   @FXML
   private TableColumn<ReceiptsValuesTableRow, String> summColumn = null;

   private ObservableList<String> purposeOptions = FXCollections.observableArrayList("Büromaterial", "Lebensmittel",
         "Sachmittel",
         "null");
   ComboBoxTableCell<ReceiptsValuesTableRow, String> comboBoxTableCell = new ComboBoxTableCell<>(purposeOptions);

   

   public TableView<ReceiptsValuesTableRow> getReceiptsTable() {
      return receiptsTable;
   }


   private void initDateColumn() {
      dateColumn.setCellFactory(TextFieldTableCell.<ReceiptsValuesTableRow>forTableColumn());
      dateColumn.setOnEditCommit(event -> {
         final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
         ((ReceiptsValuesTableRow) event.getTableView().getItems()
               .get(event.getTablePosition().getRow())).setDate(value);
         receiptsTable.refresh();
      });
   }

   private void initShopNameColumn() {
      shopNameColumn.setCellFactory(TextFieldTableCell.<ReceiptsValuesTableRow>forTableColumn());
      shopNameColumn.setOnEditCommit(event -> {
         final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
         ((ReceiptsValuesTableRow) event.getTableView().getItems()
               .get(event.getTablePosition().getRow())).setShopName(value);
         receiptsTable.refresh();
      });
   }

   private void initPurposeColumn() {
      purposeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(purposeOptions));
      purposeColumn.setOnEditCommit(event -> {
         final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
         ((ReceiptsValuesTableRow) event.getTableView().getItems()
               .get(event.getTablePosition().getRow())).setPurpose(value);
         receiptsTable.refresh();
      });
   }

   private void initSummColumn() {
      summColumn.setCellFactory(TextFieldTableCell.<ReceiptsValuesTableRow>forTableColumn());
      summColumn.setOnEditCommit(event -> {
         final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
         ((ReceiptsValuesTableRow) event.getTableView().getItems()
               .get(event.getTablePosition().getRow())).setSumm(value);
         receiptsTable.refresh();
      });
   }

   @FXML
   public void initialize() {
      receiptsTable.setEditable(true);
      initDateColumn();
      initShopNameColumn();
      initPurposeColumn();
      initSummColumn();

   }

}
