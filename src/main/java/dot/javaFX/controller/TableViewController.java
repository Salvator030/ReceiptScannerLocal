package dot.javaFX.controller;

import java.util.Collections;
import java.util.List;

import org.apache.commons.io.output.CloseShieldOutputStream;

import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.print.Collation;
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

   private ObservableList<ReceiptsValuesTableRow> rowList = FXCollections.observableArrayList();
   ObservableList<String> purposeOptions = FXCollections.observableArrayList("Büromaterial", "Lebensmittel",
         "Sachmittel",
         "null");
   ComboBoxTableCell<ReceiptsValuesTableRow, String> comboBoxTableCell = new ComboBoxTableCell<>(purposeOptions);

   public List<ReceiptsValuesTableRow> getRowList() {
      return rowList;
   }

   private void initDateColumn() {
      dateColumn.setCellFactory(TextFieldTableCell.<ReceiptsValuesTableRow>forTableColumn());
      dateColumn.setOnEditCommit(event -> {
         final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
         ((ReceiptsValuesTableRow) event.getTableView().getItems()
               .get(event.getTablePosition().getRow())).setDate(value);
         receiptValuesTable.refresh();
      });
   }

   private void initShopNameColumn() {
      shopNameColumn.setCellFactory(TextFieldTableCell.<ReceiptsValuesTableRow>forTableColumn());
      shopNameColumn.setOnEditCommit(event -> {
         final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
         ((ReceiptsValuesTableRow) event.getTableView().getItems()
               .get(event.getTablePosition().getRow())).setShopName(value);
         receiptValuesTable.refresh();
      });
   }

   private void initPurposeColumn() {
      purposeColumn.setCellFactory(ComboBoxTableCell.forTableColumn(purposeOptions));
      purposeColumn.setOnEditCommit(event -> {
         final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
         ((ReceiptsValuesTableRow) event.getTableView().getItems()
               .get(event.getTablePosition().getRow())).setPurpose(value);
         receiptValuesTable.refresh();
      });
   }

   private void initSummColumn() {
      summColumn.setCellFactory(TextFieldTableCell.<ReceiptsValuesTableRow>forTableColumn());
      summColumn.setOnEditCommit(event -> {
         final String value = event.getNewValue() != null ? event.getNewValue() : event.getOldValue();
         ((ReceiptsValuesTableRow) event.getTableView().getItems()
               .get(event.getTablePosition().getRow())).setSumm(value);
         receiptValuesTable.refresh();
      });
   }

   @FXML
   public void initialize() {
      receiptValuesTable.setEditable(true);
      initDateColumn();
      initShopNameColumn();
      initPurposeColumn();
      initSummColumn();

   }

   public void addDateRow(ReceiptsValuesTableRow receiptsValuesTableRow, ObservableList<ReceiptsValuesTableRow> list) {
      String serachFor = receiptsValuesTableRow.getDate().substring(3);
      List<ReceiptsValuesTableRow> filtredList = list
            .filtered(r -> r.getDate().equalsIgnoreCase(serachFor));
      if (filtredList.isEmpty()) {
         int i = list.indexOf(receiptsValuesTableRow);
         list.add(i, new ReceiptsValuesTableRow(serachFor));
      }
   }

   @FXML
   public void addRow(ReceiptsValuesTableRow receiptsValuesTableRow) {
      try {
         rowList.add(receiptsValuesTableRow);
         Collections.sort(rowList);
         if (!receiptsValuesTableRow.getDate().equals("")) {
            addDateRow(receiptsValuesTableRow, rowList);
         }
         receiptValuesTable.setItems(rowList);
      } catch (Exception e) {
         for (StackTraceElement s : e.getStackTrace()) {
            System.err.println(s);
         }
         ;
      }
   }

}
