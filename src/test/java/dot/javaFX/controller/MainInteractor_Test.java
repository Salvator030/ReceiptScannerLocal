package dot.javaFX.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.parallel.Execution;

import org.mockito.junit.MockitoJUnitRunner;
import org.testfx.framework.junit5.ApplicationTest;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.junit.jupiter.api.Assertions.assertEquals;
import javafx.fxml.FXMLLoader;

import javafx.stage.Stage;
import dot.Main;
import dot.asserts.EPurpose;
import dot.business.receipt.ExampleReceiptStrings;
import dot.business.receipt.Receipt;
import dot.javaFX.models.MainViewModel;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class MainInteractor_Test  {

    private final File bon1 = new File("../../../../../resources/testBon4.jpg");
    private MainViewModel model = new MainViewModel();
    
   
    MainViewModel mainViewModel = new MainViewModel();

    @Mock
    Stage stage;

 
     MainInteractor mainInteractor ;
 
    @BeforeAll
    public  void  initMocks() {
        mainInteractor = new MainInteractor(mainViewModel, stage);
    }

    @Test
    public void addScannenReciptTotableRows_Test(){
        mainViewModel.setScannendReceipt(new Receipt("01.01.2000", "Da", "20,00", EPurpose.LEBENSMITTEL));
        mainInteractor.addScannenReciptTotableRows();
        assertEquals(mainViewModel.getTableRows().size(), 1);
        mainViewModel.getTableRows().clear();
    }

    @Test
    public void hanndelScannReceiptBtn_Test(){
        mainViewModel.setInputFile(ExampleReceiptStrings.getExampleReceiptString3_File());
        mainInteractor.handelScannReceiptBtn();
        String[] scannendReceiptValues = {mainViewModel.getScannedReceipt().getDate(),mainViewModel.getScannedReceipt().getShopName(),mainViewModel.getScannedReceipt().getPurpose().toString(), mainViewModel.getScannedReceipt().getSumm()};
        String[] ecxeptetValues = {ExampleReceiptStrings.getExampleReceiptString3_date(),ExampleReceiptStrings.getExampleReceiptString3_name(),} 
        // ass(mainViewModel.getScannedReceipt()., ExampleReceiptStrings.getExampleReceiptString3_Receipt());
        
        

    }
    
  

    // @Override
    // public void start(Stage stage) throws Exception {
    //     new Main().start(stage);
        // mainInteractor = new MainInteractor(model, stage);
    // }

    // @Test

    // public void handelScannReceiptBtnTest_giveCorectReceiptImage_corectOutput() throws InterruptedException {

    //     assertEquals(0, model.getTableRows().size());
    //     model.setInputFile(bon1);
    //     mainInteractor.handelScannReceiptBtn();
    //     Thread.sleep(30000);

    //     assertEquals(1, model.getTableRows().size());
    //     ;

    // }
}
