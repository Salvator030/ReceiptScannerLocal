package dot.business.receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ReceiptSacanner_test {
    
    ReceiptScanner receiptScanner = new ReceiptScanner();
    
    @Test
    public void scannReceipt_testString_correctOutput(){
        
        Receipt accesesReceipt = new Receipt(ExampleReceiptStrings.getExampleReceiptString1_date(), ExampleReceiptStrings.getExampleReceiptString1_name(),ExampleReceiptStrings.getExampleReceiptString1_summ());
       Receipt acctual= receiptScanner.scannReceipt(ExampleReceiptStrings.getExampleReceiptString1());
        assertEquals(accesesReceipt.getDate() + " " + accesesReceipt.getShopName() + " " + accesesReceipt.getSumm(), acctual.getDate() + " " + acctual.getShopName() + " " + acctual.getSumm());
    }
}
