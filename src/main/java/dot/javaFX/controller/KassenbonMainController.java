package dot.javaFX.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import dot.business.excel.FastexcelHelper;
import dot.business.handler.FileHandler;
import dot.business.receipt.Receipt;
import dot.business.receipt.ReceiptScanner;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.StackPane;

public class KassenbonMainController {

     private Stage stage;
     private List<Receipt> receipts = new ArrayList<>();
     private FileHandler fileHandler = new FileHandler();
     private FastexcelHelper excelHelper = new FastexcelHelper();

     private VBox tableViewNode;
     private VBox fileChooserNode;
     private StackPane scannBtnNode;
     private VBox saveBtnNode;

     @FXML
     private VBox tableViewContainer = null;

     @FXML
     private VBox fileChooserContainer = null;

     @FXML
     private StackPane scannReceiptBtnContainer = null;

     @FXML
     private VBox saveBtnContainer = null;

     private TableViewController tableViewController;
     private FileChooserViewControler fileChooserViewController;
     private ScannReceiptBtnController scannReceiptBtnController;
     private SaveBtnController saveBtnController;

     public void setStage(Stage stage) {
          this.stage = stage;
     }

     public FileHandler getFileHandler() {
          return fileHandler;
     }

     public void addReceiptInList(Receipt receipt) {
          receipts.add(receipt);
     }

     private void initFileChooserContorller() {
          try {
               FXMLLoader fileChooserViewLoader = new FXMLLoader(
                         getClass().getResource("../fxml/FileChooserView.fxml"));
               fileChooserNode = fileChooserViewLoader.load();
               fileChooserViewController = fileChooserViewLoader.getController();
               fileChooserContainer.getChildren().add(fileChooserNode);
               fileChooserViewController.setMainController(this);
               fileChooserViewController.setStage(stage);

          } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
     }

     private void initScannReceiptBtnController() {
          try {
               FXMLLoader scannReceiptBtnViewLoader = new FXMLLoader(
                         getClass().getResource("../fxml/ScanReceiptBtnView.fxml"));
               scannBtnNode = scannReceiptBtnViewLoader.load();
               scannReceiptBtnController = scannReceiptBtnViewLoader.getController();
               scannReceiptBtnContainer.getChildren().add(scannBtnNode);
               scannBtnNode.setDisable(true);
               scannReceiptBtnController.setMainController(this);

          } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
          }
     }

     private void initTableViewConroller() {
          try {
               FXMLLoader tableViewLoader = new FXMLLoader(getClass().getResource("../fxml/TableView.fxml"));
               tableViewNode = tableViewLoader.load();
               tableViewController = tableViewLoader.getController();
               tableViewContainer.getChildren().add(tableViewNode);
               tableViewNode.setDisable(true);
          } catch (IOException e) {
               e.printStackTrace();
          }
     }

     private void initSaveBtnView() {
          try {
               FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/SaveBtn.fxml"));
               saveBtnNode = loader.load();
               saveBtnController = loader.getController();
               saveBtnContainer.getChildren().add(saveBtnNode);
               saveBtnController.setMainController(this);
               saveBtnNode.setDisable(true);
          } catch (IOException e) {
               e.printStackTrace();
          }
     }

     @FXML
     private void initialize() {
          initTableViewConroller();
          initFileChooserContorller();
          initScannReceiptBtnController();
          initSaveBtnView();
     }

     public void toggleScannReceiptBtnViewDisable() {
          scannBtnNode.setDisable(!scannBtnNode.isDisable());
     }

     public void toggleTableViewDisable() {
          tableViewNode.setDisable(!tableViewNode.isDisable());
     }

     public void toggleSaveBtnDisable() {
          saveBtnNode.setDisable(!saveBtnNode.isDisable());
     }

     public void clearFilePathText() {
          fileChooserViewController.clearFilePathText();
     }

     public void addReceiptInTable(Receipt receipt) {
          tableViewController.addRow(new ReceiptsValuesTableRow(receipts.size(), receipt, "null"));
     }

     public void writeReceiptsToExcel() throws NumberFormatException, IOException, ParseException {
          excelHelper.writeReceiptsToExcel(fileHandler.getFullOuputFilePath(), receipts);
     }

}
