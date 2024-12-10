package dot.javaFX.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class SaveBtnController {

    @FXML
    private Button scannReceiptBtn = null;

    private Stage stage;
    private MainInteractor mainInteractor;

    public Button getScannReceiptBtn() {
        return scannReceiptBtn;
    }
       
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public MainInteractor getMainInteractor(){
        return this.mainInteractor;
    }

    public void setMainInteractor(MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

        private File directoryChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Ablage Ordner der Ecxel Datei");
        // directoryChooser.setInitialDirectory(mainController.getFileHandler().getDocumentsDirectory());
        File selectedDirectory = directoryChooser.showDialog(stage);
        return selectedDirectory;
    }

    @FXML
    public void handleSaveBtn() throws NumberFormatException, IOException, ParseException {
        mainInteractor.saveExcelInDirectory(directoryChooser());
    }

}
