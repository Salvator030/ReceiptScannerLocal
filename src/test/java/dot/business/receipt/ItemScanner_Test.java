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

    }
}
