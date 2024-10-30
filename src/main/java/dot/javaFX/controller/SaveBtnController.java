package dot.javaFX.controller;

import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;

public class SaveBtnController {

    private Stage stage;
    private KassenbonMainController mainController;

    

    
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainController(KassenbonMainController mainController) {
        this.mainController = mainController;
    }

    
    @FXML
    public void handleSaveBtn() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Ablage Ordner der Ecxel Datei");
        directoryChooser.setInitialDirectory(mainController.getFileHandler().getDocumentsDirectory());
        File selectedDirectory = directoryChooser.showDialog(stage);
        mainController.getFileHandler().setOutputFolder(selectedDirectory);
    }

  



}
