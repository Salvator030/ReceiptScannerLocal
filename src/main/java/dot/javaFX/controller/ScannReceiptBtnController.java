package dot.javaFX.controller;

import java.io.File;

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

 Task<Void> task1 = new Task<Void>() {  //create Task
            @Override
            protected Void call() throws Exception {
                scannReceiptBtn.setVisible(false);
                progressIndicator.setVisible(true); //progress indicator set visible
                receiptScanner.setReceiptImage(mainController.getReceiptFile());
                Receipt receipt = receiptScanner.scannReceipt();
                mainController.addReceiptInList(receipt);
                mainController.addReceiptInTable(receipt);

                return null;
            }

            @Override
            protected void succeeded() {
                progressIndicator.setVisible(false); //progress indicator set not visible
                scannReceiptBtn.setVisible(true);
            }
        };

        Thread thread1 = new Thread(task1);  //assign Task into thread
        thread1.start();



      
    }

}
