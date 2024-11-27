package dot.javaFX.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import dot.asserts.EPurpose;
import dot.business.excel.FastexcelHelper;
import dot.business.handler.FileHandler;
import dot.business.receipt.Receipt;
import dot.business.receipt.ReceiptScanner;
import dot.javaFX.models.MainViewModel;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.DialogPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MainInteractor {

    MainViewModel mainViewModel;
    Stage stage;
    ReceiptScanner receiptScanner;
    FileHandler fileHandler;
    FastexcelHelper excelHelper;
    Stage dialogStage;

    public MainInteractor(MainViewModel mainViewModel, Stage stage) {
        this.mainViewModel = mainViewModel;
        this.stage = stage;
        this.receiptScanner = new ReceiptScanner();
        fileHandler = new FileHandler();
        this.excelHelper = new FastexcelHelper();

        mainViewModel.inputFileSetProperty()
                .bind(Bindings.createBooleanBinding(() -> checkIfFileSet(), mainViewModel.inputFileProperty()));
        mainViewModel.filePathStringProperty()
                .bind(Bindings.createStringBinding(() -> setNewPath(), mainViewModel.inputFileProperty()));
        mainViewModel.tableRowListEmptyProperty().bind(
                Bindings.createBooleanBinding(() -> checkIsTableRowListEmpty(), mainViewModel.tableRowsProperty()));
        mainViewModel.receiptsListEmptyProperty().bind(
                Bindings.createBooleanBinding(() -> checkIsreceiptsListEmpty(), mainViewModel.receiptsListProperty()));

    }

    private boolean checkIfFileSet() {
        if (mainViewModel.getInputFile() != null) {
            return true;
        }
        return false;
    }

    private String setNewPath() {
        if (mainViewModel.getInputFile() != null) {
            return mainViewModel.getInputFile().getAbsolutePath();
        }
        return "";
    }

    private boolean checkIsTableRowListEmpty() {

        return mainViewModel.getTableRows().isEmpty();
    }

    private boolean checkIsreceiptsListEmpty() {
        return mainViewModel.getReceiptsList().isEmpty();
    }

    private File getFileFromFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.jpg, *.png, *.tif",
                new ArrayList<String>(Arrays.asList("*.jpg", "*.png", "*.tif")));
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showOpenDialog(stage);
    }

    private File directoryChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Ablage Ordner der Ecxel Datei");
        // directoryChooser.setInitialDirectory(mainController.getFileHandler().getDocumentsDirectory());
        File selectedDirectory = directoryChooser.showDialog(stage);
        return selectedDirectory;
    }

    private Receipt scannInputFile() {
        receiptScanner.setReceiptImage(mainViewModel.getInputFile());
        mainViewModel.setInputFile(null);
        System.out.println("scann");
        return receiptScanner.scannReceipt(receiptScanner.scanImage());
    }

    private enum MissedAtributes {
        DATE, SHOPNAME, SUMM
    };

    private ArrayList<Enum<MissedAtributes>> getMissedAtriebutsList(Receipt receipt) {
        ArrayList<Enum<MissedAtributes>> missedAtributs = new ArrayList<>();
        if (receipt.getDate() == null) {
            missedAtributs.add(MissedAtributes.DATE);
        }
        if (receipt.getShopName() == null) {
            missedAtributs.add(MissedAtributes.SHOPNAME);
        }
        if (receipt.getSumm() == null) {
            missedAtributs.add(MissedAtributes.SUMM);
        }
        return missedAtributs;
    }

    private void setDefaulReceipttValues(Receipt receipt) {
        if (receipt.getDate() == null) {
            receipt.setDate("nicht lesbar");
        }
        if (receipt.getShopName() == null) {
            receipt.setShopName("nicht lesbar");
        }
        if (receipt.getSumm() == null) {
            receipt.setSumm("nicht lesbar");
        }

        if(receipt.getPurpose() == null){
            receipt.setPurpose(EPurpose.SONSTIGES);
        }
    }

    private void openModalDialog() {
        try {
            FXMLLoader dialogChanchValuesLoader = new FXMLLoader(
                    getClass().getResource("/dot/javaFX/fxml/DialogChangeValues.fxml"));

            DialogPane dialogPane = dialogChanchValuesLoader.load();
            DialogChancheValuesController controller = dialogChanchValuesLoader.getController();
            controller.setReceipt(mainViewModel.getScannedReceipt());
            controller.setMainInteractor(this);
            dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(dialogPane));
            dialogStage.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    private void closeDialog() {
        dialogStage.close();
        mainViewModel.setScannendReceipt(null);
        dialogStage = null;
    }

    protected void handelChooseInputFileBtn() {

        File file = getFileFromFileChooser();
        if (file != null) {
            mainViewModel.setInputFile(file);
        }
    }

    protected void addScannenReciptTotableRows() {
        mainViewModel.addReceiptsList(mainViewModel.getScannedReceipt());
        ReceiptsValuesTableRow row = new ReceiptsValuesTableRow(mainViewModel.getTableRows().size(),
                mainViewModel.getScannedReceipt(), null);
        mainViewModel.addTablesRows(row);
        Collections.sort(mainViewModel.getTableRows());
    }

    protected void handelScannReceiptBtn() {
        System.out.println("start");

        Task<Void> task1 = new Task<Void>() { // create Task
            @Override
            protected Void call() throws Exception {
                try {

                    mainViewModel.setScanning(true);
                    Receipt receipt = scannInputFile();
                  
                    Platform.runLater(() -> {
                          setDefaulReceipttValues(receipt);
                        mainViewModel.setScannendReceipt(receipt);
                       

                    });

                } catch (Exception e) {

                    for (StackTraceElement s : e.getStackTrace()) {
                        System.err.println(s);
                    }
                }

                return null;

            }

            @Override
            protected void succeeded() {
                Platform.runLater(() ->{ mainViewModel.setScanning(false);   openModalDialog();}
                );
            }
        };

        Thread thread1 = new Thread(task1); // assign Task into thread
        thread1.start();
     

    }

    public void handelSafeBtn() {
        mainViewModel.setOutputDirectory(directoryChooser());
        fileHandler.setOutputFolder(mainViewModel.getOutputDirectory());
        try {

            excelHelper.writeReceiptsToExcelFiles(mainViewModel.getTableRows());
            mainViewModel.tableRowsProperty().clear();
        } catch (NumberFormatException | IOException | ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void handleChangeValuesOklBtn() {
        System.out.println("handleChangeValuesOklBtn");
        mainViewModel.addReceiptsList(mainViewModel.getScannedReceipt());
        ReceiptsValuesTableRow row = new ReceiptsValuesTableRow(mainViewModel.getTableRows().size(),
                mainViewModel.getScannedReceipt());
        mainViewModel.addTablesRows(row);
        Collections.sort(mainViewModel.getTableRows());
        mainViewModel.setScannendReceipt(null);
        dialogStage.close();
        dialogStage = null;
    }

    public void handleChangeValuesCancelBtn() {
        System.out.println("handleChangeValuesCancelBtn");
        closeDialog();
    }

}
