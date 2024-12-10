package dot.javaFX.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.File;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import javafx.stage.Stage;
import dot.asserts.EPurpose;
import dot.business.receipt.ExampleReceiptStrings;
import dot.business.receipt.Receipt;
import dot.javaFX.models.MainViewModel;
import dot.javaFX.objects.ReceiptsValuesTableRow;

@ExtendWith(MockitoExtension.class)
public class MainInteractor_Test {

    private MainInteractor mainInteractor;

    private MainViewModel mainViewModel;

    Stage stage;

    @BeforeEach
    public void setUp() throws Exception {
        mainViewModel = new MainViewModel();
        mainInteractor = new MainInteractor(mainViewModel, stage);
    }

    //TOTDO TEst schlägt fehl wegen falsch erkanten werten
    // @Test
    // void scannReceipt_test() throws Exception {
    //     mainViewModel.setInputFile(ExampleReceiptStrings.getExampleReceiptString3_File());
    //     // Execute the method
    //     CountDownLatch latch = new CountDownLatch(1);
    //     mainInteractor.scannReceipt();
    //     latch.countDown();
    //     latch.await(10, TimeUnit.SECONDS);
    //     // Verify the result
    //     assertEquals(ExampleReceiptStrings.getExampleReceiptString3_Receipt().toString(), mainViewModel.getScannedReceipt().toString());
    // }

    @Test
    void addScannenReciptTotableRows_test(){
        mainViewModel.setScannendReceipt(ExampleReceiptStrings.getExampleReceiptString3_Receipt());
        mainInteractor.addScannenReciptTotableRows();
        assertEquals(1, mainViewModel.getTableRows().size());
     
    }

    @Test
    void setReceiptValues_test() throws Exception {
        Receipt r = new Receipt(null, null, null);
        mainInteractor.setReceiptValues(r);
        assertEquals(r, mainViewModel.getScannedReceipt());
    }

    @Test
    void saveExcelInDirectory_test(){
            mainViewModel.addTablesRows(new ReceiptsValuesTableRow(1,"03.01.2001","edeka",EPurpose.LEBENSMITTEL,10.00));
        mainViewModel.addTablesRows(new ReceiptsValuesTableRow(1,"01.01.2001","aldi",EPurpose.LEBENSMITTEL,20.00));
        
        String directory = "src/test/resources/";
         String fileName = "Kassenbons-Abrechnung-012001.xlsx";
        File testFile = new File(directory+fileName);
              assertEquals(false, testFile.exists());
        mainInteractor.saveExcelInDirectory(new File(directory));
        assertEquals(true, testFile.exists());
        testFile.delete();
      
    }

}
