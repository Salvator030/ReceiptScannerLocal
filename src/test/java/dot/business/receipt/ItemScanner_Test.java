package dot.business.receipt;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class ItemScanner_Test {
    ItemScanner itemScanner = new ItemScanner();

    @Test
    public void getTotalSumm_diverntSting_matchCorectOuput() {

        String[] array = { "Summe", "Gesamtsumme", "Zu zahlen", "Gesamtbetrag", "gesamt", "Betrag", "EUR" };
        String summ = " 33.03";

        assertEquals(Double.parseDouble(summ), itemScanner.getTotalSumm(array[0] + " " + summ + " €"));
        assertEquals(Double.parseDouble(summ), itemScanner.getTotalSumm(array[1] + " " + summ + " €"));
        assertEquals(Double.parseDouble(summ), itemScanner.getTotalSumm(array[2] + " " + summ + " €"));
        assertEquals(Double.parseDouble(summ), itemScanner.getTotalSumm(array[3] + " " + summ + " €"));
        assertEquals(Double.parseDouble(summ), itemScanner.getTotalSumm(array[4] + " " + summ + "€"));
        assertEquals(Double.parseDouble(summ), itemScanner.getTotalSumm(array[5] + " " + summ + "€"));
        assertEquals(Double.parseDouble(summ), itemScanner.getTotalSumm(array[6] + " " + summ + "€"));

        assertEquals(Double.parseDouble(summ), itemScanner.getTotalSumm(array[0] + ":\t" + summ + "\t€"));
        assertEquals(Double.parseDouble(summ), itemScanner.getTotalSumm(array[1] + ":\t" + summ + "\t€"));
        assertEquals(Double.parseDouble(summ), itemScanner.getTotalSumm(array[2] + "\t" + summ + "\t€"));
        assertEquals(Double.parseDouble(summ), itemScanner.getTotalSumm(array[3] + "\t" + summ + "\t€"));
        assertEquals(Double.parseDouble(summ), itemScanner.getTotalSumm(array[4] + ":" + summ + "€"));
        assertEquals(Double.parseDouble(summ), itemScanner.getTotalSumm(array[5] + " " + summ + "\t€"));
        assertEquals(Double.parseDouble(summ), itemScanner.getTotalSumm(array[6] + " " + summ + "\t €"));


        assertEquals(ExampleReceiptStrings.getExampleReceiptString1_summ(), itemScanner.getTotalSumm("Betrag EUR 11,28\n"));
    }

    @Test
    public void getDate_diverentString_matchCorectOutput(){

        String[] resultArray = {"22.02.2024", "Date 22.02.2024", "Date: 22.02.2024","Date:\t\t22.02.2024","\t\t22.02.2024\t\t" };
        String[] resiltArray2 = {"Summe:\t22,22 €" , "22/02/2024" + "02.2.2024"};

        for (String s : resultArray){
            assertEquals("22.02.2024",itemScanner.getDate(s));
        }
        for (String s : resiltArray2){
             assertEquals(null,itemScanner.getDate(s));
        }

        assertEquals(ExampleReceiptStrings.getExampleReceiptString1_date(), ExampleReceiptStrings.getExampleReceiptString1());
       
    }
}
