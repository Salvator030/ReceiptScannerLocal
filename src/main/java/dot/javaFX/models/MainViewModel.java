package dot.javaFX.models;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableListValue;

import java.io.File;
import java.util.List;

import dot.business.receipt.Receipt;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.beans.Observable;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class MainViewModel {

  private final ObjectProperty<File> inputFile = new SimpleObjectProperty<File>(null);
  private final BooleanProperty inputFileSet = new SimpleBooleanProperty(false);
  private final StringProperty filePathSting = new SimpleStringProperty(null);
  private final ObservableListValue<ReceiptsValuesTableRow> tableRows = new SimpleListProperty<ReceiptsValuesTableRow>(
      null);
      private final ObservableListValue<Receipt> receiptsList = new SimpleListProperty<>();

  
  private final ObjectProperty<File> ecxelFile = new SimpleObjectProperty<File>(null);
  private final BooleanProperty ecxelFileSet = new SimpleBooleanProperty(false);

  private final BooleanProperty tableRowsListEmty = new SimpleBooleanProperty();
  private final ObjectProperty<Receipt> scannedReceipt = new SimpleObjectProperty<Receipt>();

  public File getInputFile() {
    return inputFile.get();
  }

  public ObjectProperty<File> inputFileProperty() {
    return inputFile;
  }

  public void setInputFile(File file) {
    inputFile.set(file);
  }

  public boolean isInputFileSet(){
    return inputFileSet.get();
  }

  public BooleanProperty inputFileSetProperty(){
    return inputFileSet;
  }

  public void setInputFileSet(boolean isSet){
    inputFileSet.set(isSet);
  }

  public String getFilePathSting() {
    return filePathSting.get();
  }

  public StringProperty filePathStringProperty() {
    return filePathSting;
  }

  public void setFilePathString(String path) {
    this.filePathSting.set(path);
  }

  public List<ReceiptsValuesTableRow> getTableRows(){
    return this.tableRows.get();
  }

  public ObservableListValue<ReceiptsValuesTableRow> tableRowsProperty(){
    return this.tableRows;
  }

  public void addTablesRows(ReceiptsValuesTableRow row){
    this.tableRows.add(row);
  }

  public List<Receipt> getReceiptsList(){
    return this.receiptsList.get();
  }

  public ObservableListValue receiptsListProperty(){
    return this.receiptsList;
  }

  public void addReceiptsList(Receipt receipt){
    this.receiptsList.add(receipt);
  }

  public File getEcxelFile() {
    return ecxelFile.get();
  }

  public ObjectProperty<File> ecxelFileProperty() {
    return ecxelFile;
  }

  public void setEcxelFile(File file) {
    this.ecxelFile.set(file);
  }

  public Boolean isEcxelFileSet() {
    return ecxelFileSet.get();
  }

  public BooleanProperty ecxelFileSetPRoperty() {
    return ecxelFileSet;
  }

  public void setEcxelFIleSet(Boolean isSet) {
    ecxelFileSet.set(isSet);
  }

  public boolean isTableRowListEmpty() {
    return tableRowsListEmty.get();
  }

  public BooleanProperty tableRowListProperty() {
    return tableRowsListEmty;
  }

  public void setTableRowListEmpty(boolean isEmpty) {
    this.tableRowsListEmty.set(isEmpty);
  }

  public Receipt getScannedReceipt() {
    return scannedReceipt.get();
  }

  public ObjectProperty<Receipt> scannedReceiptProperty() {
    return scannedReceipt;
  }

  public void setScannendReceipt(Receipt receipt) {
    scannedReceipt.set(receipt);
  }

}