package dot.javaFX.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import java.io.IOException;
import java.text.ParseException;

public class SaveBtnController {

    @FXML
    private Button scannReceiptBtn = null;

    private MainInteractor mainInteractor;

    public Button getScannReceiptBtn() {
        return scannReceiptBtn;
    }

    public void setMainInteractor(MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    @FXML
    public void handleSaveBtn() throws NumberFormatException, IOException, ParseException {
        mainInteractor.handelSafeBtn();
    }

}
