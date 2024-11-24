package dot.javaFX.controller;

import dot.business.receipt.Receipt;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogChancheValuesController {

    @FXML 
    private DialogPane dialogPane;
    
    @FXML 
    private  TextField dateValue = null;

    @FXML 
    private  TextField nameValue = null;

    @FXML 
    private   TextField summValue = null;

    @FXML 
    private  TextField usesValue = null;

    @FXML
    private  Button changValuesOkBtn = null;
    
    @FXML
    private  Button changeValuesCancelBtn = null;

    private MainInteractor mainInteractor;
    private Receipt receipt;

    public void setMainInteractor(MainInteractor interactor){
        this.mainInteractor = interactor;
    }

    public void setReceipt(Receipt  receipt){
        this.receipt = receipt;
        dateValue.textProperty().bindBidirectional(receipt.dateProperty());
        nameValue.textProperty().bindBidirectional(receipt.shopNameProperty());
        summValue.textProperty().bindBidirectional(receipt.summProperty());
        usesValue.textProperty().bindBidirectional(receipt.purposeProperty());
               
    }

    @FXML
    public void handleChangeValuesOklBtn(){
        System.out.println("OklBtn");
        this.mainInteractor.handleChangeValuesOklBtn();
    }

    @FXML
    public void handleChangeValueCancleBtn(){
        System.out.println("CancelBtn");
        this.mainInteractor.handleChangeValuesCancelBtn();
    }

}
