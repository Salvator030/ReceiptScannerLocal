package dot.javaFX.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

import dot.business.excel.FastexcelHelper;
import dot.business.handler.FileHandler;
import dot.business.receipt.Receipt;
import dot.business.receipt.ReceiptScanner;
import dot.javaFX.models.MainViewModel;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
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
        this.excelHelper= new FastexcelHelper();

        mainViewModel.inputFileSetProperty()
                .bind(Bindings.createBooleanBinding(() -> checkIfFileSet(), mainViewModel.inputFileProperty()));
        mainViewModel.filePathStringProperty()
                .bind(Bindings.createStringBinding(() -> setNewPath(), mainViewModel.inputFileProperty()));
        mainViewModel.tableRowListEmptyProperty().bind(
                Bindings.createBooleanBinding(() -> checkIsTableRowListEmpty(), mainViewModel.tableRowsProperty()));
                mainViewModel.receiptsListEmptyProperty().bind(Bindings.createBooleanBinding(() -> checkIsreceiptsListEmpty(), mainViewModel.receiptsListProperty()));

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

    private boolean checkIsreceiptsListEmpty(){
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

    protected void handelChooseInputFileBtn() {

        File file = getFileFromFileChooser();
        if (file != null) {
            mainViewModel.setInputFile(file);
        }
    }

    protected void handelScannReceiptBtn(Button btn, ProgressIndicator progressIndicator) {

        Task<Void> task1 = new Task<Void>() { // create Task
            @Override
            protected Void call() throws Exception {
                try {
                    btn.setVisible(false);
                    progressIndicator.setVisible(true); // progress indicator set visible

                    receiptScanner.setReceiptImage(mainViewModel.getInputFile());
                    mainViewModel.setInputFile(null);
                    Receipt receipt = receiptScanner.scannReceipt(receiptScanner.scanImage());
                    mainViewModel.setScannendReceipt(receipt);

                    mainViewModel.addReceiptsList(receipt);
                    mainViewModel.addTablesRows(
                            new ReceiptsValuesTableRow(mainViewModel.getReceiptsList().size(), receipt, "null"));
                Collections.sort(mainViewModel.getTableRows());
                } catch (Exception e) {

                    for (StackTraceElement s : e.getStackTrace()) {
                        System.err.println(s);
                    }
                }
                return null;
            }

            @Override
            protected void succeeded() {
                progressIndicator.setVisible(false); // progress indicator set not visible
                btn.setVisible(true);
            }
        };

        Thread thread1 = new Thread(task1); // assign Task into thread
        thread1.start();

    }

}
