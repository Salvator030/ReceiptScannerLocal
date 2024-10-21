package dot.javaFX.controller;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable; 
import javafx.scene.control.Label; 

public class MainController {

    
    @FXML
    private Label label; 

   public void onClickSelectFileBtn(){
        System.out.println("Hello");
   }
    
}
