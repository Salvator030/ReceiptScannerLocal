package dot.business.receipt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemScanner {

    public String getStoreName(String result) {

        String name = null;
        result = result.toLowerCase();
            if ((!result.contains("beleg") || !result.matches(".*[kassenbon].*")
                    || !result.matches("^.*[quittung].*")) && !result.isBlank()) {
                name =  result;
            }
        
        return name;
    }

    public double getTotalSumm(String result) {
        final String[] SYNONYMS_FOR_SUMM = { "summe", "zahlen", "gesamt", "betrag", "eur" };
        final int COUNTER = SYNONYMS_FOR_SUMM.length;
       
        System.out.println("result: " + result);

        Double summ =null;
                     
                String regex = "^\\D{1}\\w+(?:\\s*\\w*)[:]?\\s+(\\d+[,.]\\d{2})\\s*[€]?$";
      
            Matcher m = Pattern.compile(regex).matcher(result);
         System.out.println("Matches: " + m.matches());
            for (int i = 0; i < COUNTER; i++) {
                if (result.toLowerCase().contains(SYNONYMS_FOR_SUMM[i])) {
            
                    System.out.println("groupe1: " + m.group(1));
                    summ =  Double.parseDouble(m.group(1).replace(",", "."));
                    break;
                }
            }
            return summ;      
    }

    public String getDate(String result) {
        String regex = ".*(\\d{2}[.]\\d{2}[.]\\d{4}).*";
        Matcher m = Pattern.compile(regex).matcher(result);
        String date = null;
        if(m.matches()){
            date = m.group(1);
        }
        return date;
    }
}
