package dot.javaFX.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.FileChooser;

public class FileChooserViewControler {

    private Stage stage;
    private KassenbonMainController mainController;

    public void setMainController(KassenbonMainController controller) {
        mainController = controller;
        System.out.println("mainController " + mainController);
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    @FXML
    Text filePathText = null;

    private File getFileFromFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.jpg, *.png, *.tif",
                new ArrayList<String>(Arrays.asList("*.jpg", "*.png", "*.tif")));
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showOpenDialog(stage);
    }

    @FXML
    public void handelSelectReceiptBtn() {
        File file = getFileFromFileChooser();
        mainController.setReceiptFile(file);
        filePathText.setText(file.getAbsolutePath());
    }
}
