package dot.javaFX.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
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
     private File receiptFile;
     private List<Receipt> receipts = new ArrayList<>();

     private VBox tableViewNode;
     private VBox fileChooserNode;
     private StackPane scannBtnNode;

     @FXML
     private VBox tableViewContainer = null;

     @FXML
     private VBox fileChooserContainer = null;

     @FXML
     private  StackPane scannReceiptBtnContainer = null;

     private TableViewController tableViewController;
     private FileChooserViewControler fileChooserViewController;
     private ScannReceiptBtnController scannReceiptBtnController;

     public void setStage(Stage stage) {
          this.stage = stage;
     }

     public void setReceiptFile(File file) {
          this.receiptFile = file;
     }

     public File getReceiptFile() {
          return receiptFile;
     }

     public void addReceiptInList(Receipt receipt) {
          receipts.add(receipt);
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

     @FXML
     private void initialize() {
          initTableViewConroller();
          initFileChooserContorller();
          initScannReceiptBtnController();
     }

     public void toggleScannReceiptBtnViewDisable(){
          scannBtnNode.setDisable(!scannBtnNode.isDisable());
     }

     public void toggleTableViewDisable(){
          tableViewNode.setDisable(!tableViewNode.isDisable());
     }

     public void clearFilePathText(){
          fileChooserViewController.clearFilePathText();
     }

     
     public void addReceiptInTable(Receipt receipt) {
          tableViewController.addRow(new ReceiptsValuesTableRow(receipts.size(), receipt, "null"));
     }

     @FXML
     public void handleSaveBtn() {
          System.out.println("c");
     }

}
