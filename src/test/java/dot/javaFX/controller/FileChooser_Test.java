package dot.javaFX.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationTest;

import dot.Main;
import org.testfx.api.FxToolkit;

@ExtendWith(MockitoExtension.class)
public class FileChooser_Test extends ApplicationTest {

    @Mock
    private MainInteractor mainInteractor;

    @InjectMocks
    private FileChooserViewControler controller;

    private static Stage stage;

    @BeforeAll
    public static void setUpBeforeClass() throws Exception {
        // Initialisiere JavaFX einmal pro JVM-Lauf
        FxToolkit.registerPrimaryStage();
        FxToolkit.setupApplication(Main.class);
    }

    @BeforeEach
    public void setUp() throws Exception {
        // Initialisiere die Bühne (Stage) für jeden Test
        stage = FxToolkit.setupStage(stage -> {

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dot/javaFX/fxml/FileChooserView.fxml"));
            VBox vBox = null;
            try {
               vBox = loader.load();
            } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
            }
            controller = loader.getController();

            // Mock-Objekte setzen
            controller.setInteractor(mainInteractor);
            controller.setStage(stage);

            stage.setScene(new Scene(vBox));
            stage.show();
        });
    }

    @Test
    void testClearFilePathText() {
        Platform.runLater(() -> {
            controller.clearFilePathText();
            assertEquals("", controller.getFilePathText().getText());
        });
    }

    @Test
    void testOnClickFileCooserBtn() throws Exception {
        Platform.runLater(() -> {
            // Erstelle eine Mock-Datei
            File mockFile = mock(File.class);
            when(mockFile.getPath()).thenReturn("test-file.jpg");

            // Mock das FileChooser Verhalten
            FileChooser fileChooser = spy(new FileChooser());
            doReturn(mockFile).when(fileChooser).showOpenDialog(any(Stage.class));

            // Setze das FileChooser Objekt im Controller
            controller.setStage(stage);

            // Führe die Methode aus
            controller.onClickFileCooserBtn();

            // Verifiziere, dass das MainInteractor die richtige Datei gesetzt hat
            verify(mainInteractor).setInputFileInModel(mockFile);
        });
    }
}
