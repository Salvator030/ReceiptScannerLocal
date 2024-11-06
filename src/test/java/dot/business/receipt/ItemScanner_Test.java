package dot.business.receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;


public class ItemScanner_Test {
    ItemScanner itemScanner = new ItemScanner();

@Test
    public void getTotalSumm_diverntSting_matchCorectOuput(){
    //   String target = "22.00";
    //   String test = "Summe " + target + " €";
    //     String[] testStrings = {"Summe 22.22€", "Summe\t22.22€", "Summe 22.22 €", "Summe\t22.22 €","Summe\t22.22\t€", "22.22€", "22.22 €","22.22\t€"," 22.22€"," 22.22 €"," 22.22\t€","\t22.22€","\t22.22\t€"};
// for(String s : testStrings){
//      assertEquals(target, itemScanner.getTotalSumm(s));
// }
String[] array = {"Summe:","Gesamtsumme","Zu zahlen:","Gesamtbetrag","gesamt","Betrag","EUR"};
String summ = "33.03";


assertEquals(Double.parseDouble(summ),itemScanner.getTotalSumm(array[0] + " " + summ + " €"));
assertEquals(Double.parseDouble(summ),itemScanner.getTotalSumm(array[1] + " " + summ));
assertEquals(Double.parseDouble(summ),itemScanner.getTotalSumm(array[2] + " " + summ + " €"));
assertEquals(Double.parseDouble(summ),itemScanner.getTotalSumm(array[3] + " " + summ + " €"));
assertEquals(Double.parseDouble(summ),itemScanner.getTotalSumm(array[4] + " " + summ));
assertEquals(Double.parseDouble(summ),itemScanner.getTotalSumm(array[5] + " " + summ + " €"));
assertEquals(Double.parseDouble(summ),itemScanner.getTotalSumm(array[6] + " " + summ + " €"));

    }
}
