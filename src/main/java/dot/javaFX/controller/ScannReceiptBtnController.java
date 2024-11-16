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
    ProgressIndicator progressIndicator = null;

    @FXML
    Button scannReceiptBtn = null;

    private MainInteractor mainInteractor;

    public void setMainInteractor(MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    @FXML
    public void handelScannReceiptBtn() {
        mainInteractor.handelScannReceiptBtn(scannReceiptBtn, progressIndicator);
    }

}
