package dot.javaFX.controller;

import dot.business.receipt.Receipt;
import dot.business.receipt.ReceiptScanner;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;

public class ScannReceiptBtnController {

    @FXML
    ProgressIndicator progressIndicator = null;

    @FXML
    Button scannReceiptBtn = null;

    private KassenbonMainController mainController;
    private ReceiptScanner receiptScanner = new ReceiptScanner();

    public void setMainController(KassenbonMainController controller) {
        mainController = controller;
    }

    @FXML
    public void handelScannReceiptBtn() {

        Task<Void> task1 = new Task<Void>() { // create Task
            @Override
            protected Void call() throws Exception {
                try {
                    scannReceiptBtn.setVisible(false);
                    progressIndicator.setVisible(true); // progress indicator set visible
                    receiptScanner.setReceiptImage(mainController.getFileHandler().getInputFile());
                    Receipt receipt = receiptScanner.scannReceipt(receiptScanner.scanImage());
                    mainController.addReceiptInList(receipt);
                    mainController.addReceiptInTable(receipt);
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
