package dot.javaFX.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;

import dot.Main_Test;
import dot.javaFX.models.MainViewModel;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
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
public class KassenBonMainController_Test extends ApplicationTest {

    @Mock
    private MainViewModel mainViewModel;

    @Mock
    private TableViewController tableViewController;

    @Mock
    private FileChooserViewControler fileChooserViewController;

    @Mock
    private ScannReceiptBtnController scannReceiptBtnController;

    @Mock
    private SaveBtnController saveBtnController;

    @Mock
    private DialogChancheValuesController chancheValuesController;

    @InjectMocks
    private KassenbonMainController controller;

    private static Stage stage;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        // Initialisiere JavaFX einmal pro JVM-Lauf
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main_Test.class);
    }

    @BeforeEach
    public void setUp() throws Exception {
        // Initialisiere Mocks
        MockitoAnnotations.openMocks(this);

        // Initialisiere die Bühne (Stage) für jeden Test
        stage = FxToolkit.setupStage(stage -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dot/javaFX/fxml/Kassenbon-Main.fxml"));
            VBox root = null;
            try {
                root = loader.load();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            controller = loader.getController();

            // Setze die gemockten Objekte
            controller.setStage(stage);

            stage.setScene(new Scene(root));
            stage.show();
        });
    }

    @Test
    void testInitialize() {
        Platform.runLater(() -> {
            controller.initialize();

            // Verifiziere, dass die Untercontroller initialisiert wurden
            assertNotNull(controller.fileChooserViewController);
            assertNotNull(controller.scannReceiptBtnController);
            assertNotNull(controller.tableViewController);
            assertNotNull(controller.saveBtnController);

            // Verifiziere, dass die Komponenten hinzugefügt wurden
            assertNotNull(controller.fileChooserContainer.getChildren().get(0));
            assertNotNull(controller.scannReceiptBtnContainer.getChildren().get(0));
            assertNotNull(controller.tableViewContainer.getChildren().get(0));
            assertNotNull(controller.saveBtnContainer.getChildren().get(0));
        });
    }

    @Test
    void testSetStage() {
        Platform.runLater(() -> {
            // Führe die Methode aus
            controller.setStage(stage);

            // Verifiziere, dass das Stage-Objekt gesetzt wurde
            assertNotNull(controller.stage);
            assertEquals(stage, controller.stage);
        });
    }

    // Weitere Tests, um die Funktionalität der einzelnen Methoden zu überprüfen
    // (z.B. initFileChooserContorller, initScannReceiptBtnController, initTableViewConroller, initSaveBtnView)
}
