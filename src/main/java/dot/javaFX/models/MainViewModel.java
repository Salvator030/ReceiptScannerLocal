package dot.javaFX.models;

import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableListValue;
import javafx.collections.FXCollections;
import java.io.File;
import java.util.List;

import dot.business.receipt.Receipt;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class MainViewModel {

  private final ObjectProperty<File> inputFile = new SimpleObjectProperty<File>(null);
  private final BooleanProperty inputFileSet = new SimpleBooleanProperty(false);
  private final StringProperty filePathSting = new SimpleStringProperty(null);

  private final ObjectProperty<Receipt> scannedReceipt = new SimpleObjectProperty<Receipt>(new Receipt("", "", 0));
private final BooleanProperty scanning = new SimpleBooleanProperty(false); 
  private final ListProperty<ReceiptsValuesTableRow> tableRows = new SimpleListProperty<ReceiptsValuesTableRow>(FXCollections.observableArrayList());
   private final BooleanProperty tableRowsListEmty = new SimpleBooleanProperty();
  private final ListProperty<Receipt> receiptsList = new SimpleListProperty<Receipt>(FXCollections.observableArrayList() );
private final BooleanProperty receiptsListEmty = new SimpleBooleanProperty(true);
  private final ObjectProperty<File> outputDirectory = new SimpleObjectProperty<>(null);
  private final ObjectProperty<File> ecxelFile = new SimpleObjectProperty<File>(null);
  private final BooleanProperty ecxelFileSet = new SimpleBooleanProperty(false);

 

// inputFile
  public File getInputFile() {
    return inputFile.get();
  }

  public ObjectProperty<File> inputFileProperty() {
    return inputFile;
  }

  public void setInputFile(File file) {
    inputFile.set(file);
  }

  // inputFileSet
  public boolean isInputFileSet() {
    return inputFileSet.get();
  }

  public BooleanProperty inputFileSetProperty() {
    return inputFileSet;
  }

  public void setInputFileSet(boolean isSet) {
    inputFileSet.set(isSet);
  }

  // filePathSting
  public String getFilePathSting() {
    return filePathSting.get();
  }

  public StringProperty filePathStringProperty() {
    return filePathSting;
  }

  public void setFilePathString(String path) {
    this.filePathSting.set(path);
  }

  // scannedReceipt

  public Receipt getScannedReceipt() {
    return scannedReceipt.get();
  }

  public ObjectProperty<Receipt> scannedReceiptProperty() {
    return scannedReceipt;
  }

  public void setScannendReceipt(Receipt receipt) {
    scannedReceipt.set(receipt);
  }

  // scanning
  public boolean isSanning(){
    return this.scanning.get();
  }

  public BooleanProperty scanningProperty(){
    return this.scanning;
  }

  public void setScanning(boolean isScanning){
    this.scanning.set(isScanning);
  }

  // tableRows

  public List<ReceiptsValuesTableRow> getTableRows() {
    return this.tableRows.get();
  }

  public ObservableListValue<ReceiptsValuesTableRow> tableRowsProperty() {
    return this.tableRows;
  }

  public void addTablesRows(ReceiptsValuesTableRow row) {
    this.tableRows.add(row);
  }

  // tableRowsListEmty
  public boolean isTableRowListEmpty() {
    return tableRowsListEmty.get();
  }

  public BooleanProperty tableRowListEmptyProperty() {
    return tableRowsListEmty;
  }

  public void setTableRowListEmpty(boolean isEmpty) {
    this.tableRowsListEmty.set(isEmpty);
  }

  // receiptsList
  public List<Receipt> getReceiptsList() {
    return this.receiptsList.get();
  }

  public ObservableListValue<Receipt> receiptsListProperty() {
    return this.receiptsList;
  }

  public void addReceiptsList(Receipt receipt) {
    this.receiptsList.get().add(receipt);
  }

  // receiptsListEmty
  public boolean isReceiptsListEmpty(){
    return this.receiptsListEmty.get();
  }

  public  BooleanProperty receiptsListEmptyProperty(){
    return this.receiptsListEmty;
  }

  public void setReceiptsListEmpty(boolean empty){
    this.receiptsListEmty.set(empty);
  }

  // outputDirectory
  public File getOutputDirectory(){
    return this.outputDirectory.get();
  }

  public ObjectProperty<File> outputDirectoryProperty(){
    return this.outputDirectory;
  }

  public void setOutputDirectory(File directory){
    this.outputDirectory.set(directory);
  }

  // ecxelFile
  public File getEcxelFile() {
    return ecxelFile.get();
  }

  public ObjectProperty<File> ecxelFileProperty() {
    return ecxelFile;
  }

  public void setEcxelFile(File file) {
    this.ecxelFile.set(file);
  }

  // ecxelFileSet
  public Boolean isEcxelFileSet() {
    return ecxelFileSet.get();
  }

  public BooleanProperty ecxelFileSetPRoperty() {
    return ecxelFileSet;
  }

  public void setEcxelFIleSet(Boolean isSet) {
    ecxelFileSet.set(isSet);
  }





}