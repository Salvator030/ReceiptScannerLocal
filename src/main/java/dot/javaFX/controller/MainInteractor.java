package dot.javaFX.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import dot.business.handler.FileHandler;
import dot.business.receipt.Receipt;
import dot.business.receipt.ReceiptScanner;
import dot.javaFX.models.MainViewModel;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.beans.binding.Bindings;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainInteractor {

    MainViewModel mainViewModel;
    Stage stage;
    ReceiptScanner receiptScanner;
    FileHandler fileHandler;

    public MainInteractor(MainViewModel mainViewModel, Stage stage) {
        this.mainViewModel = mainViewModel;
        this.receiptScanner = new ReceiptScanner();

        mainViewModel.inputFileSetProperty()
                .bind(Bindings.createBooleanBinding(() -> checkIfFileSet(), mainViewModel.inputFileProperty()));
        mainViewModel.filePathStringProperty().bind(Bindings.createStringBinding(() -> setNewPath(), mainViewModel.inputFileProperty()));
    }

    private boolean checkIfFileSet() {
        if (mainViewModel.getInputFile() != null) {
            return true;
        }
        return false;
    }

    private String setNewPath(){
        if (mainViewModel.getInputFile() != null) {
        return mainViewModel.getInputFile().getAbsolutePath();
        }
        return "";
    }


    private File getFileFromFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.jpg, *.png, *.tif",
                new ArrayList<String>(Arrays.asList("*.jpg", "*.png", "*.tif")));
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showOpenDialog(stage);
    }



    protected void handelChooseInputFileBtn() {

        File file = getFileFromFileChooser();
        if (file != null) {
            mainViewModel.setInputFile(file);
        }
    }

    protected void handelScannReceiptBtn( Event evnt){

         Button btn = (Button) evnt.getSource();
     Task<Void> task1 = new Task<Void>() { // create Task
            @Override
            protected Void call() throws Exception {
                try {
                    btn.setVisible(false);
                    progressIndicator.setVisible(true); // progress indicator set visible
                    receiptScanner.setReceiptImage(mainViewModel.getInputFile());
                    mainViewModel.setInputFile(null);
                    mainViewModel.setScannendReceipt( receiptScanner.scannReceipt(receiptScanner.scanImage()));
                    mainViewModel.addReceiptsList(mainViewModel.getScannedReceipt());
                   mainViewModel.addTablesRows(new ReceiptsValuesTableRow(0, mainViewModel.getScannedReceipt(), "null"));;
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
                scannReceiptBtn.setVisible(true);
                mainController.toggleScannReceiptBtnViewDisable();
                mainController.toggleTableViewDisable();
                mainController.clearFilePathText();
                mainController.toggleSaveBtnDisable();
            }
        };

        Thread thread1 = new Thread(task1); // assign Task into thread
        thread1.start();


    }

}
