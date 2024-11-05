package dot.business.receipt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemScanner {

    public String getStoreName(String result) {
        String[] resultArray = result.split("\n");
        for (String resultString : resultArray) {
            resultString = resultString.toLowerCase();
            if ((!resultString.contains("beleg") || !resultString.matches(".*[kassenbon].*")
                    || !resultString.matches("^.*[quittung].*")) && !resultString.isBlank()) {
                return resultString;
            }
        }
        return null;
    }

    public double getTotalSumm(String result) {
        // final String[] SYNONYMS_FOR_SUMM = { "summe", "zu zahlen", "gesamt", "betrag" };
        // final int COUNTER = SYNONYMS_FOR_SUMM.length;
       
        System.out.println("result: " + result);
            // String regex = ".*([A-Za-z]*)\\s(\\d+[,.]{1}\\d{2})\\s?[€]";
            String regex = "\\d{1-4}";
            Matcher m = Pattern.compile(regex).matcher(result);
            System.out.println(m.group(1));
            return Double.parseDouble(m.group(1));

            // for (int i = 0; i < COUNTER; i++) {
            //     if (string.toLowerCase().contains(SYNONYMS_FOR_SUMM[i])) {
            //         return Double.parseDouble(string.replaceAll("[^0-9,]", "").replace(',', '.'));
            //     }
            // }
      
    }

    public String getDate(String result) {
        String regex = ".*(\\d{2}.\\d{2}.\\d{4}).*";
        Matcher m = Pattern.compile(regex).matcher(result);
        return m.group(1);
    }
}
