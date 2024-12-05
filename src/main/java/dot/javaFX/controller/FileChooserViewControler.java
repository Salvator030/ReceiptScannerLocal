package dot.javaFX.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileChooserViewControler {

   private MainInteractor interactor;
   private Stage stage;

   public void setInteractor(MainInteractor mainInteractor){
    this.interactor = mainInteractor;
   }
  

    public void setStage(Stage stage) {
    this.stage = stage;
  }


    @FXML
    private Text filePathText = null;
    
    @FXML 
    private Button selectReceiptBtn = null;


     public void clearFilePathText() {
        filePathText.setText("");
    }

    public Text getFilePathText(){
        return filePathText;
    }

     private File getFileFromFileChooser() {
        FileChooser fileChooser = new FileChooser();
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("*.jpg, *.png, *.tif",
                new ArrayList<String>(Arrays.asList("*.jpg", "*.png", "*.tif")));
        fileChooser.getExtensionFilters().add(extFilter);
        return fileChooser.showOpenDialog(stage);
    }

    @FXML
    public void onClickFileCooserBtn() {
      interactor.setInputFileInModel(getFileFromFileChooser());
    }

    
}
