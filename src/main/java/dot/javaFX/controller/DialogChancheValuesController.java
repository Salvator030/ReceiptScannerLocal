package dot.javaFX.controller;

import dot.asserts.EPurpose;
import dot.business.receipt.Receipt;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class DialogChancheValuesController {

    @FXML
    private DialogPane dialogPane;

    @FXML
    private TextField dateValue = null;

    @FXML
    private TextField nameValue = null;

    @FXML
    private TextField summValue = null;

    @FXML
    private ChoiceBox<EPurpose> usesValue = null;

    @FXML
    private Button changValuesOkBtn = null;

    @FXML
    private Button changeValuesCancelBtn = null;

    private Stage dialogStage;
    private MainInteractor mainInteractor;
    private ObjectProperty<ObservableList<EPurpose>> purposes = new SimpleObjectProperty<ObservableList<EPurpose>>(
            FXCollections.observableArrayList(EPurpose.values()));

    public ChoiceBox<EPurpose> getUsesValue() {
        return usesValue;
    }

    public TextField getDateValue() {
        return dateValue;
    }

    public TextField getNameValue() {
        return nameValue;
    }

    public TextField getSummValue() {
        return summValue;
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    public void setMainInteractor(MainInteractor mainInteractor) {
        this.mainInteractor = mainInteractor;
    }

    public void setReceipt(Receipt receipt) {
        dateValue.textProperty().bindBidirectional(receipt.dateProperty());
        nameValue.textProperty().bindBidirectional(receipt.shopNameProperty());
        summValue.textProperty().bindBidirectional(receipt.summProperty());
        usesValue.valueProperty().bindBidirectional(receipt.purposeProperty());
    }

    @FXML
    public void handleChangeValuesOklBtn() {
        this.mainInteractor.addScannenReciptTotableRows();
        this.dialogStage.close();
    }

    @FXML
    public void handleChangeValueCancleBtn() {
        this.dialogStage.close();
    }

    @FXML
    void initialize() {
        usesValue.getItems().clear();
        usesValue.getItems().addAll(purposes.get());

    }
}
