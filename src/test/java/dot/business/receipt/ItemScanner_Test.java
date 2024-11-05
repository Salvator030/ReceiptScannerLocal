package dot.business.receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class ItemScanner_Test {
    ItemScanner itemScanner = new ItemScanner();

@Test
    public void getTotalSumm_diverntSting_matchCorectOuput(){
      Double target = 22.22;
        String[] testStrings = {"Summe 22.22€", "Summe\t22.22€", "Summe 22.22 €", "Summe\t22.22 €","Summe\t22.22\t€", "22.22€", "22.22 €","22.22\t€"," 22.22€"," 22.22 €"," 22.22\t€","\t22.22€","\t22.22\t€"};
// for(String s : testStrings){
//      assertEquals(target, itemScanner.getTotalSumm(s));
// }
assertEquals(22.00, "22.00");
    }
}
