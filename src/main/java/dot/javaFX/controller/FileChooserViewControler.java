package dot.javaFX.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class FileChooserViewControler {

   private MainInteractor interactor;

   public void setInteractor(MainInteractor mainInteractor){
    this.interactor = mainInteractor;
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

    @FXML
    public void onClickFileCooserBtn() {
      interactor.handelChooseInputFileBtn();
    }

    
}
