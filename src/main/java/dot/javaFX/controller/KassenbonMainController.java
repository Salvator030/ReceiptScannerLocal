package dot.javaFX.controller;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import dot.business.receipt.Receipt;
import dot.business.receipt.ReceiptScanner;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class KassenbonMainController {

     private Stage stage;
     private File receiptFile;
     private List<Receipt> receipts = new ArrayList<>();
     private ReceiptScanner receiptScanner = new ReceiptScanner();

     @FXML
     Text filePathText = null;

     @FXML
     TableView<ReceiptsValuesTableRow> receiptValuesTable = null;

     ObservableList<ReceiptsValuesTableRow> observableArrayList = FXCollections.observableArrayList();

     public void setStage(Stage stage) {
          this.stage = stage;
     }

     private void setReceiptFile(File file) {
          this.receiptFile = file;
     }

     private File getFileFromFileChooser() {
          FileChooser fileChooser = new FileChooser();
          FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.jpg, *.png, *.tif",
                    new ArrayList<String>(Arrays.asList("*.jpg", "*.png", "*.tif")));
          fileChooser.getExtensionFilters().add(extFilter);
          return fileChooser.showOpenDialog(stage);
     }

     @FXML
     public void handelSelectReceiptBtn() {
          setReceiptFile(getFileFromFileChooser());
          System.out.print(receiptFile.getAbsolutePath());
          filePathText.setText(receiptFile.getAbsolutePath());
     }

     @FXML
     public void handelScannReceiptBtn() {
          receiptScanner.setReceiptImage(receiptFile);
          Receipt receipt = receiptScanner.scannReceipt();
          receipts.add(receipt);
          observableArrayList.add(new ReceiptsValuesTableRow(receipts.size(), receipt, "test"));
          receiptValuesTable.setItems(observableArrayList);

     }

     @FXML
     public void handleSaveBtn() {
          System.out.println("c");
     }

}