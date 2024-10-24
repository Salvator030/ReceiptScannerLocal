package dot.receipt;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import dot.receipt.ExampleReceiptStrings;
 import dot.business.receipt.ItemScanner;

 class ItemScanner_Test {
 private  ItemScanner itemScanner = new ItemScanner();

    @Test
    public  void test_getStoreName_withExampleReceiptStrings() {
       assertEquals(ExampleReceiptStrings.getExampleReceiptString1_name(),itemScanner.getStoreName(ExampleReceiptStrings.getExampleReceiptString1()));
    }

    @Test
    public void test_getSumm__withExampleReceiptStrings(){
        assertEquals(ExampleReceiptStrings.getExampleReceiptString1_summ(),itemScanner.getTotalSumm(ExampleReceiptStrings.getExampleReceiptString1()) );
    }
 }

    /*
    public  double getTotalSumm(String result) {
        final String[] SYNONYMS_FOR_SUMM = { "summe", "zu zahlen", "gesamt", "betrag" };
        final int COUNTER = SYNONYMS_FOR_SUMM.length;
        String[] resultArray = result.split("\n");
        for (String string : resultArray) {
            for (int i = 0; i < COUNTER; i++) {
                if (string.toLowerCase().contains(SYNONYMS_FOR_SUMM[i])) {
                    return Double.parseDouble(string.replaceAll("[^0-9,]", "").replace(',', '.'));
                }
            }
        }
        return 0;
    }

    public  Date getDate(String result) {
        String regex = ".*(\\d{2}.\\d{2}.\\d{4}).*";
        Matcher m = Pattern.compile(regex).matcher(result);
        Date date = null;
        if (m.find()) {
            try {
                System.out.println(m.group(1));
                date = new SimpleDateFormat("dd.MM.yyyy").parse(m.group(1));
            } catch (ParseException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return date;
    }
}
 */