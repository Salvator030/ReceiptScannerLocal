package dot.javaFX.controller;

import static org.mockito.Mockito.*;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
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

import dot.Main;

import org.testfx.api.FxToolkit;

@ExtendWith(MockitoExtension.class)
public class SaveBtnController_Test extends ApplicationTest {

    @Mock
    private MainInteractor mainInteractor;

    @InjectMocks
    private SaveBtnController controller;

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dot/javaFX/fxml/SaveBtn.fxml"));
           VBox vBox = null;
        try {
            vBox = loader.load();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
            controller = loader.getController();

            // Setze die gemockten Objekte
            controller.setMainInteractor(mainInteractor);
            controller.setStage(stage);

            stage.setScene(new Scene(vBox));
            stage.show();
        });
    }

    @Test
    void testHandleSaveBtn() throws IOException, ParseException, NumberFormatException {
        Platform.runLater(() -> {
            // Erstelle ein Mock-Verzeichnis
            File mockDirectory = mock(File.class);
            when(mockDirectory.getPath()).thenReturn("test-directory");

            // Mock das DirectoryChooser Verhalten
            DirectoryChooser directoryChooser = spy(new DirectoryChooser());
            doReturn(mockDirectory).when(directoryChooser).showDialog(any(Stage.class));

            // Setze das DirectoryChooser Objekt im Controller
            controller.setStage(stage);

            // Führe die Methode aus
            try {
                controller.handleSaveBtn();
            } catch (NumberFormatException | IOException | ParseException e) {
                e.printStackTrace();
            }

            // Verifiziere, dass das MainInteractor das richtige Verzeichnis gesetzt hat
            verify(mainInteractor).saveExcelInDirectory(mockDirectory);
        });
    }
}
