package dot.javaFX.controller;

import java.io.IOException;
import dot.javaFX.models.MainViewModel;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class KassenbonMainController {

     private Stage stage;

     private VBox tableViewNode;
     private VBox fileChooserNode;
     private StackPane scannBtnNode;
     private VBox saveBtnNode;
     private DialogPane changeValuesDialogNode;
     private Stage changeValuesDialogStage;

      private MainViewModel mainViewModel = new MainViewModel();
     private MainInteractor mainInteractor = new MainInteractor(mainViewModel, stage);

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
     private DialogChancheValuesController chancheValuesController;

     public void setStage(Stage stage) {
          this.stage = stage;
     }

     public MainInteractor getMainInteractor() {
          return mainInteractor;
     }

     private void initFileChooserContorller() {
          try {
               FXMLLoader fileChooserViewLoader = new FXMLLoader(
                         getClass().getResource("../fxml/FileChooserView.fxml"));
               fileChooserNode = fileChooserViewLoader.load();
               fileChooserViewController = fileChooserViewLoader.getController();
               fileChooserContainer.getChildren().add(fileChooserNode);
               fileChooserViewController.setInteractor(mainInteractor);
               fileChooserViewController.getFilePathText().textProperty().bind(mainViewModel.filePathStringProperty());

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
               scannBtnNode.disableProperty().bind(mainViewModel.inputFileSetProperty().not());
               scannReceiptBtnController.getProgressIndicator().disableProperty().set(false);
               scannReceiptBtnController.getProgressIndicator().visibleProperty()
                         .bind(mainViewModel.scanningProperty());
               scannReceiptBtnController.getScannReceiptBtn().visibleProperty()
                         .bind(mainViewModel.scanningProperty().not());
               scannReceiptBtnController.setMainInteractor(mainInteractor);

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
               tableViewController.getReceiptsTable().itemsProperty().bind(mainViewModel.tableRowsProperty());
               ;
               tableViewNode.disableProperty().bind(mainViewModel.tableRowListEmptyProperty());
          } catch (IOException e) {
               e.printStackTrace();
          }
     }

     private void initSaveBtnView() {
          try {
               System.out.println(getClass().getResource("../fxml/SaveBtn.fxml"));
               FXMLLoader loader = new FXMLLoader(getClass().getResource("../fxml/SaveBtn.fxml"));
               saveBtnNode = loader.load();
               saveBtnController = loader.getController();
               saveBtnContainer.getChildren().add(saveBtnNode);
               saveBtnController.setMainInteractor(mainInteractor);
               saveBtnNode.disableProperty().bind(mainViewModel.receiptsListEmptyProperty());
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

     // public void toggleScannReceiptBtnViewDisable() {
     // scannBtnNode.setDisable(!scannBtnNode.isDisable());
     // }

     // public void toggleTableViewDisable() {
     // if(!receipts.isEmpty()){
     // tableViewNode.setDisable(false);
     // }else{
     // tableViewNode.setDisable(!tableViewNode.isDisable());}
     // }

     // public void toggleSaveBtnDisable() {
     // saveBtnNode.setDisable(!saveBtnNode.isDisable());
     // }

     // public void clearFilePathText() {
     // fileChooserViewController.clearFilePathText();
     // }

     // public void addReceiptInTable(Receipt receipt) {
     // System.out.println("addReceiptInTable");
     // tableViewController.addRow(new ReceiptsValuesTableRow(receipts.size(),
     // receipt, "null"));
     // }

     // public void writeReceiptsToExcel() throws NumberFormatException, IOException,
     // ParseException {

     // excelHelper.writeReceiptsToExcelFiles(tableViewController.getRowList());

     // }
}
