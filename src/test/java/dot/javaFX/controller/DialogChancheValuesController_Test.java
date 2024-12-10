package dot.javaFX.controller;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;

import dot.Main;
import dot.asserts.EPurpose;
import dot.business.receipt.Receipt;
import javafx.application.Platform;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.api.FxToolkit;

@ExtendWith(MockitoExtension.class)
public class DialogChancheValuesController_Test extends ApplicationTest {

    @Mock
    private MainInteractor mainInteractor;

    @Mock
    private Receipt mockReceipt;

    @InjectMocks
    private DialogChancheValuesController controller;

    private static Stage stage;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        // Initialisiere JavaFX einmal pro JVM-Lauf
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
    }

    @BeforeEach
    public void setUp() throws Exception {
        // Initialisiere Mocks
        MockitoAnnotations.openMocks(this);

        // Initialisiere die Bühne (Stage) für jeden Test
        stage = FxToolkit.setupStage(stage -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dot/javaFX/fxml/DialogChangeValues.fxml"));
            DialogPane dialogPane = null;
            try {
                dialogPane = loader.load();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            controller = loader.getController();

            // Setze die gemockten Objekte
            controller.setMainInteractor(mainInteractor);
            controller.setDialogStage(stage);

            stage.setScene(new Scene(dialogPane));
            stage.show();
        });
    }

    @Test
    void testInitialize() {
        Platform.runLater(() -> {
            // controller.initialize();

            ChoiceBox<EPurpose> choiceBox = controller.getUsesValue();
            System.out.println( choiceBox.getItems().toString());
            assertEquals(EPurpose.values().length, choiceBox.getItems().size());
        });
    }

    @Test
    void testSetReceipt() {
        Platform.runLater(() -> {
            // Mock-Verhalten definieren
            when(mockReceipt.dateProperty()).thenReturn(new SimpleStringProperty("01.01.2021"));
            when(mockReceipt.shopNameProperty()).thenReturn(new SimpleStringProperty("ShopName"));
            when(mockReceipt.summProperty()).thenReturn(new SimpleStringProperty("100"));
            when(mockReceipt.purposeProperty()).thenReturn(new SimpleObjectProperty<>(EPurpose.LEBENSMITTEL));

            // Führe die Methode aus
            controller.setReceipt(mockReceipt);

            // Verifiziere die Bindung der Werte
            assertEquals("01.01.2021", controller.getDateValue().getText());
            assertEquals("ShopName", controller.getNameValue().getText());
            assertEquals("100", controller.getSummValue().getText());
            assertEquals(EPurpose.LEBENSMITTEL, controller.getUsesValue().getValue());
        });
    }

    @Test
    void testHandleChangeValuesOklBtn() {
        Platform.runLater(() -> {
            // Führe die Methode aus
            controller.handleChangeValuesOklBtn();

            // Verifiziere, dass die Methode im MainInteractor aufgerufen wurde
            verify(mainInteractor).addScannenReciptTotableRows();

            // Verifiziere, dass das Dialogfenster geschlossen wurde
            assertEquals(false, stage.isShowing());
        });
    }

    @Test
    void testHandleChangeValueCancleBtn() {
        Platform.runLater(() -> {
            // Führe die Methode aus
            controller.handleChangeValueCancleBtn();

            // Verifiziere, dass das Dialogfenster geschlossen wurde
            assertEquals(false, stage.isShowing());
        });
    }
}
