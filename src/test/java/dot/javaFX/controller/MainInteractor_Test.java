package dot.javaFX.controller;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;

import javafx.stage.Stage;
import dot.javaFX.models.MainViewModel;

public class MainInteractor_Test {

    private final File bon1 = new File("../../../../../resources/testBon4.jpg");
    private MainViewModel model = new MainViewModel();
    MainInteractor mainInteractor = new MainInteractor(model, null);

    
    
    @Test
     
    public void handelScannReceiptBtnTest_giveCorectReceiptImage_corectOutput() throws InterruptedException{
        
        assertEquals(0, model.getTableRows().size());
        model.setInputFile(bon1);
        mainInteractor.handelScannReceiptBtn();
        Thread.sleep(30000);
    
            assertEquals(1, model.getTableRows().size());;
        

    }
} 
