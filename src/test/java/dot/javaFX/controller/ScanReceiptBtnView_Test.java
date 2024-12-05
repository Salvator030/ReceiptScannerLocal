package dot.javaFX.controller;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.testfx.framework.junit5.ApplicationTest;

import dot.business.receipt.ExampleReceiptStrings;
import dot.javaFX.models.MainViewModel;
import javafx.stage.Stage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javafx.application.Platform;
import javafx.embed.swing.JFXPanel;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.testfx.util.WaitForAsyncUtils.waitForFxEvents;

@ExtendWith(MockitoExtension.class)
public class ScanReceiptBtnView_Test extends ApplicationTest {
    @Mock
    Stage stage;

    @Mock
    private MainInteractor mainInteractor;
    @Mock
    private MainViewModel mainViewModel;
    @InjectMocks
    private ScannReceiptBtnController controller;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dot/javaFX/fxml/ScanReceiptBtnView.fxml"));
        StackPane stackPane = loader.load();
        controller = loader.getController();

        controller.setMainInteractor(mainInteractor);
        when(mainInteractor.getMainViewModel()).thenReturn(mainViewModel);

        stage.setScene(new Scene(stackPane));
        stage.show();
    }

    @BeforeEach
    public void setUp() throws Exception {
        // Initialisiere JavaFX (erforderlich für Tests mit JavaFX)
        new JFXPanel();

        // Lade die FXML-Datei und initialisiere den Controller
        waitForFxEvents();
        Platform.runLater(() -> {
            try {
                start(new Stage());
                // Initialisiert die Stage und lädt die FXML-Datei
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

    }

    @Test
    void testHandelScannReceiptBtn() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Platform.runLater(() -> {
            mainViewModel.setInputFile(ExampleReceiptStrings.getExampleReceiptString3_File());
            when(mainViewModel.isScanning()).thenReturn(true);
            controller.handelScannReceiptBtn();
            // Verifiziere, dass die Methode ausgeführt wurde
            assertEquals(true, controller.getMainInteractor().getMainViewModel().isScanning());
            // Füge einen Listener hinzu, um das Latch zu zählen, wenn der Scan
            // abgeschlossen ist
            controller.getMainInteractor().getMainViewModel().scanningProperty()
                    .addListener((observable, oldValue, newValue) -> {
                        if (!newValue) {
                            latch.countDown();
                        }
                    });

        });
        // Warte, bis die Latch heruntergezählt wird (d.h., der Scan ist abgeschlossen)
        latch.await(10, TimeUnit.SECONDS);
        // Warte maximal 10 Sekunden
        // Verifiziere, dass die Methode ausgeführt wurde
        assertEquals(false, mainViewModel.isScanning());
    }
}
