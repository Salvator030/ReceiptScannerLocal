package dot.javaFX.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import dot.business.excel.FastexcelHelper;
import dot.business.handler.FileHandler;
import dot.business.receipt.Receipt;
import dot.business.receipt.ReceiptScanner;
import dot.javaFX.models.MainViewModel;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainInteractor {

    MainViewModel mainViewModel;
    Stage stage;
    ReceiptScanner receiptScanner;
    FileHandler fileHandler;
    FastexcelHelper excelHelper;

    public MainInteractor(MainViewModel mainViewModel, Stage stage) {
        this.mainViewModel = mainViewModel;
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

    private boolean isScannedReceiptComplet(Receipt receipt) {
        if (receipt.getDate() != null && receipt.getPurpose() != null && receipt.getShopName() != null
                && receipt.getSumm() != 0) {
            return true;
        }
        return false;
    }

    protected void handelChooseInputFileBtn() {

        File file = getFileFromFileChooser();
        if (file != null) {
            mainViewModel.setInputFile(file);
        }
    }

    protected void handelScannReceiptBtn() {
        System.out.println("start");

        Task<Void> task1 = new Task<Void>() { // create Task
            @Override
            protected Void call() throws Exception {
                try {

                    mainViewModel.setScanning(true);
                    Receipt receipt = scannInputFile();
                    if (isScannedReceiptComplet(receipt)) {
                        Platform.runLater(() -> {
                            mainViewModel.setScannendReceipt(receipt);
                            System.out.println(mainViewModel.getTableRows().size());
                            mainViewModel.addReceiptsList(mainViewModel.getScannedReceipt());
                            ReceiptsValuesTableRow row = new ReceiptsValuesTableRow(mainViewModel.getTableRows().size(),
                                    mainViewModel.getScannedReceipt(), "");
                            mainViewModel.addTablesRows(row);
                            Collections.sort(mainViewModel.getTableRows());
                        });
                    }
                } catch (Exception e) {

                    for (StackTraceElement s : e.getStackTrace()) {
                        System.err.println(s);
                    }
                }
                return null;

            }

            @Override
            protected void succeeded() {
                Platform.runLater(() -> mainViewModel.setScanning(false));
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

}
