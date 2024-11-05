package dot.business.receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class ItemScanner_Test {
    ItemScanner itemScanner = new ItemScanner();

@Test
    public void getTotalSumm_diverntSting_matchCorectOuput(){
      String target = "22.00";
      String test = "Summe " + target + "3.33 e44.44 22222.2 222222.33";
        String[] testStrings = {"Summe 22.22€", "Summe\t22.22€", "Summe 22.22 €", "Summe\t22.22 €","Summe\t22.22\t€", "22.22€", "22.22 €","22.22\t€"," 22.22€"," 22.22 €"," 22.22\t€","\t22.22€","\t22.22\t€"};
// for(String s : testStrings){
//      assertEquals(target, itemScanner.getTotalSumm(s));
// }
assertEquals(Double.parseDouble(target),itemScanner.getTotalSumm(test));
    }
}
