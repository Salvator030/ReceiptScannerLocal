// package dot.javaFX.controller;

// import static org.mockito.Mockito.*;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import java.io.IOException;
// import java.util.concurrent.CountDownLatch;
// import java.util.concurrent.TimeUnit;
// import java.util.concurrent.TimeoutException;

// import dot.Main_Test;
// import dot.asserts.EPurpose;
// import dot.business.receipt.Receipt;
// import javafx.application.Platform;
// import javafx.beans.property.SimpleObjectProperty;
// import javafx.beans.property.SimpleStringProperty;
// import javafx.embed.swing.JFXPanel;
// import javafx.fxml.FXMLLoader;
// import javafx.scene.Scene;
// import javafx.scene.layout.StackPane;
// import javafx.stage.Stage;

// import org.junit.jupiter.api.BeforeAll;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.InjectMocks;
// import org.mockito.Mock;
// import org.mockito.MockitoAnnotations;
// import org.mockito.junit.jupiter.MockitoExtension;
// import org.testfx.framework.junit5.ApplicationTest;
// import org.testfx.api.FxToolkit;

// @ExtendWith(MockitoExtension.class)
// public class ScanReceiptBtnView_Test extends ApplicationTest {

//     @Mock
//     private MainInteractor mainInteractor;

//     @Mock
//     private Receipt mockReceipt;

//     @InjectMocks
//     private ScannReceiptBtnController controller;

//     private static Stage stage;

//     @BeforeAll
//     public static void setUpBeforeClass() throws Exception {
//         // Initialisiere JavaFX einmal pro JVM-Lauf
//         FxToolkit.registerPrimaryStage();
//         FxToolkit.setupApplication(Main_Test.class);
//     }

//     @BeforeEach
//     public void setUp() throws Exception {
//         // Initialisiere Mocks
//         MockitoAnnotations.openMocks(this);

//         // Initialisiere das mockReceipt mit den erforderlichen Eigenschaften
//         when(mockReceipt.dateProperty()).thenReturn(new SimpleStringProperty("01.01.2021"));
//         when(mockReceipt.shopNameProperty()).thenReturn(new SimpleStringProperty("ShopName"));
//         when(mockReceipt.summProperty()).thenReturn(new SimpleStringProperty("100"));
//         when(mockReceipt.purposeProperty()).thenReturn(new SimpleObjectProperty<>(EPurpose.LEBENSMITTEL));

//         // Initialisiere die Bühne (Stage) für jeden Test
//         stage = FxToolkit.setupStage(stage -> {
//             FXMLLoader loader = new FXMLLoader(getClass().getResource("/dot/javaFX/fxml/ScanReceiptBtnView.fxml"));
//             StackPane root = null;
//             try {
//                 root = loader.load();
//             } catch (IOException e) {
//                 // TODO Auto-generated catch block
//                 e.printStackTrace();
//             }
//             controller = loader.getController();

//             // Setze die gemockten Objekte
//             controller.setMainInteractor(mainInteractor);

//             stage.setScene(new Scene(root));
//             stage.show();
//         });
//     }

//     @Test
//     void testHandelScannReceiptBtn() throws Exception {
//         // Initialisiere JavaFX-Umgebung
//         new JFXPanel();

//         CountDownLatch latch = new CountDownLatch(1);

//         Platform.runLater(() -> {
//             try {
//                 // Simuliere das Verhalten des MainInteractors
//                 when(mainInteractor.scannReceipt()).thenReturn(mockReceipt);

//                 // Führe die Methode aus
//                 controller.handelScannReceiptBtn();

//                 // Verifiziere, dass das Scanning gestartet wurde
//                 verify(mainInteractor, times(1)).toggleScanning();

//                 // Verwende eine separate Platform.runLater, um sicherzustellen, dass der Verify-Block im JavaFX-Thread ausgeführt wird
          
//                         latch.countDown(); // Zähle den Latch herunter, wenn die Überprüfung abgeschlossen ist
              

//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });

//         // Warte auf die Ausführung von JavaFX-Operationen, aber mit Timeout
//         if (!latch.await(10, TimeUnit.SECONDS)) {
//             throw new TimeoutException("Test timed out while waiting for async operations to complete.");
//         }
//     }

//     @Test
//     void testOpenModalDialog() throws Exception {
//         // Initialisiere JavaFX-Umgebung
//         new JFXPanel();

//         CountDownLatch latch = new CountDownLatch(1);

//         Platform.runLater(() -> {
//             try {
//                 // Initialisiere die Methode openModalDialog
//                 controller.openModalDialog();

//                 // Verifiziere, dass das Dialogfenster erstellt wurde
//                 assertNotNull(controller.dialogPane);
//                 assertNotNull(controller.dialogStage);
//                 assertEquals(true, controller.dialogStage.isShowing());

//                 latch.countDown();
//             } catch (Exception e) {
//                 e.printStackTrace();
//             }
//         });

//         // Warte auf die Ausführung von JavaFX-Operationen, aber mit Timeout
//         if (!latch.await(10, TimeUnit.SECONDS)) {
//             throw new TimeoutException("Test timed out while waiting for async operations to complete.");
//         }
//     }
// }


