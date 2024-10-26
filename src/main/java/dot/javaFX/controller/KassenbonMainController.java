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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class KassenbonMainController {

     private Stage stage;
     private File receiptFile;
     private List<Receipt> receipts = new ArrayList<>();
     private ReceiptScanner receiptScanner = new ReceiptScanner();
     private VBox tableViewNode;
     private VBox fileChooserNode;

     @FXML
     VBox tableViewContainer = null;

     @FXML
     VBox fileChooserContainer = null;

     TableViewController tableViewController;
     FileChooserViewControler fileChooserViewController;

     public void setStage(Stage stage) {
          this.stage = stage;
     }

     public void setReceiptFile(File file) {
          this.receiptFile = file;
     }

     private void initTableViewConroller() {
          try {
               FXMLLoader tableViewLoader = new FXMLLoader(getClass().getResource("../fxml/TableView.fxml"));
               tableViewNode = tableViewLoader.load();
               tableViewController = tableViewLoader.getController();
               tableViewContainer.getChildren().add(tableViewNode);
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

     @FXML
     private void initialize() {
          initTableViewConroller();
          initFileChooserContorller();
     }

     @FXML
     public void handelScannReceiptBtn() {
          receiptScanner.setReceiptImage(receiptFile);
          Receipt receipt = receiptScanner.scannReceipt();
          receipts.add(receipt);
          tableViewController.addRow(new ReceiptsValuesTableRow(receipts.size(), receipt, "null"));
     }

     @FXML
     public void handleSaveBtn() {
          System.out.println("c");
     }

}
