package dot.javaFX.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.IOException;

import dot.Main;
import dot.asserts.EPurpose;
import dot.javaFX.objects.ReceiptsValuesTableRow;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.testfx.framework.junit5.ApplicationTest;
import org.testfx.api.FxToolkit;

@ExtendWith(MockitoExtension.class)
public class TableViewController_test extends ApplicationTest {

    @InjectMocks
    private TableViewController controller;

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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/dot/javaFX/fxml/TableView.fxml"));
            VBox vbox = null;
            try {
                vbox = loader.load();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            controller = loader.getController();

            stage.setScene(new Scene(vbox));
            stage.show();
        });
    }

    @Test
    void testInitialize() {
        Platform.runLater(() -> {
            controller.initialize();

            TableView<ReceiptsValuesTableRow> table = controller.getReceiptsTable();
            assertEquals(true, table.isEditable());

            TableColumn<ReceiptsValuesTableRow, String> dateColumn = (TableColumn<ReceiptsValuesTableRow, String>) table
                    .getColumns().get(1);
            assertEquals("Datum", dateColumn.getText());
        });
    }

    @Test
    void testAddRow() {
        Platform.runLater(() -> {
            controller.initialize();

            TableView<ReceiptsValuesTableRow> table = controller.getReceiptsTable();
            ReceiptsValuesTableRow newRow = new ReceiptsValuesTableRow(1, "01.01.2021", "ShopName",
                    EPurpose.LEBENSMITTEL, 10.00);
            table.getItems().add(newRow);

            assertEquals(1, table.getItems().size());
            assertEquals("01.01.2021", table.getItems().get(0).getDate());
        });
    }

    @Test
    void testEditDateColumn() {
        Platform.runLater(() -> {
            controller.initialize();

            TableView<ReceiptsValuesTableRow> table = controller.getReceiptsTable();
            ReceiptsValuesTableRow newRow = new ReceiptsValuesTableRow(1, "02.02.2022", "ShopName",
                    EPurpose.LEBENSMITTEL, 10.00);
            table.getItems().add(newRow);

            TableColumn<ReceiptsValuesTableRow, String> dateColumn = (TableColumn<ReceiptsValuesTableRow, String>) table
                    .getColumns().get(0);

            // Simuliere eine Bearbeitung
            dateColumn.getOnEditCommit()
                    .handle(new TableColumn.CellEditEvent<>(table, null, TableColumn.editCommitEvent(), "02.02.2022"));

            assertEquals("02.02.2022", table.getItems().get(0).getDate());
        });
    }
}
