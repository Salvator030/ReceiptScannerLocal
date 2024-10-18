package dot.business.receipt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

 public class ItemScanner {

    public  String getStoreName(String result) {
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
