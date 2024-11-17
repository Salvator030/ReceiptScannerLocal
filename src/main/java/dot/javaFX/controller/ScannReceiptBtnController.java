package dot.javaFX.controller;

import dot.business.receipt.Receipt;
import dot.business.receipt.ReceiptScanner;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.concurrent.Task;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;

public class ScannReceiptBtnController {

    @FXML
   private ProgressIndicator progressIndicator = null;

    @FXML
    private  Button scannReceiptBtn = null;

    

    public ProgressIndicator getProgressIndicator() {
        return progressIndicator;
    }

    public Button getScannReceiptBtn() {
        return scannReceiptBtn;
    }

    private MainInteractor mainInteractor;

    public void setMainInteractor(MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    @FXML
    public void handelScannReceiptBtn() {
        mainInteractor.handelScannReceiptBtn();
    }

}
