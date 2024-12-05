package dot.javaFX.controller;

import java.io.IOException;

import dot.business.receipt.Receipt;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.ProgressIndicator;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ScannReceiptBtnController {

    @FXML
    private ProgressIndicator progressIndicator = null;

    @FXML
    private Button scannReceiptBtn = null;
    private MainInteractor mainInteractor;

    DialogPane dialogPane;
    Stage dialogStage;

    public ProgressIndicator getProgressIndicator() {
        return progressIndicator;
    }

    public Button getScannReceiptBtn() {
        return scannReceiptBtn;
    }

    public MainInteractor getMainInteractor() {
        return this.mainInteractor;
    }

    public void setMainInteractor(MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    private void openModalDialog() {
        try {
            FXMLLoader dialogChanchValuesLoader = new FXMLLoader(
                    getClass().getResource("/dot/javaFX/fxml/DialogChangeValues.fxml"));

            dialogPane = dialogChanchValuesLoader.load();
            DialogChancheValuesController controller = dialogChanchValuesLoader.getController();
            controller.setReceipt(mainInteractor.getReceiptValues());

            controller.setMainInteractor(mainInteractor);
            dialogStage = new Stage();
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setScene(new Scene(dialogPane));
            controller.setDialogStage(dialogStage);
            dialogStage.showAndWait();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @FXML
    public void handelScannReceiptBtn() {

        Task<Void> task1 = new Task<Void>() { // create Task
            @Override
            protected Void call() throws Exception {
                try {
                    mainInteractor.toggleScanning();

                    Receipt receipt = mainInteractor.scannReceipt();
                    Platform.runLater(() -> {
                        mainInteractor.setReceiptValues(receipt);
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
                Platform.runLater(() -> {
                    mainInteractor.toggleScanning();
                    openModalDialog();
                });
            }
        };

        Thread thread1 = new Thread(task1); // assign Task into thread
        thread1.start();

    }

}
