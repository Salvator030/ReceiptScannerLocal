package dot.javaFX.controller;

import javafx.fxml.FXML;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class SaveBtnController {

    private Stage stage;
    private KassenbonMainController mainController;

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainController(KassenbonMainController mainController) {
        this.mainController = mainController;
    }

    private File directoryChooser() {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle("Ablage Ordner der Ecxel Datei");
        directoryChooser.setInitialDirectory(mainController.getFileHandler().getDocumentsDirectory());
        File selectedDirectory = directoryChooser.showDialog(stage);
        return selectedDirectory;
    }

    @FXML
    public void handleSaveBtn() throws NumberFormatException, IOException, ParseException {
        // File directory = directoryChooser();
        // if (directory != null) {
        //     mainController.getFileHandler().setOutputFolder(directory);
        //     mainController.writeReceiptsToExcel();
        }
    // }

}
