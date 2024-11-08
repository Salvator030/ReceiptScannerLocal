package dot.business.receipt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemScanner {

    public String getStoreName(String result) {

        String name = null;
       String lowCasewresult = result.toLowerCase();
        if ((!lowCasewresult.contains("beleg") || !lowCasewresult.matches(".*[kassenbon].*")
                || !lowCasewresult.matches("^.*[quittung].*")) && !lowCasewresult.isBlank()) {
            name = result;
        }

        return name;
    }

    public Double getTotalSumm(String result) {
        final String[] SYNONYMS_FOR_SUMM = { "summe", "zahlen", "gesamt", "betrag", "eur" };
        final int COUNTER = SYNONYMS_FOR_SUMM.length;

        System.out.println("result: " + result);

        Double summ = null;

        String regex = "^\\D{1}\\w+(?:\\s*\\w*)[:]?\\s+(\\d+[,.]\\d{2})\\s*[€]?$";
        try{
        Matcher m = Pattern.compile(regex).matcher(result);
        System.out.println("Matches: " + m.matches());
        for (String s : SYNONYMS_FOR_SUMM ) {
            if (result.toLowerCase().contains(s)) {
                System.out.println("SYNONYMS_FOR_SUMM[i]: " +s);
                System.out.println("groupe1: " + m.group(1));
                summ = Double.parseDouble(m.group(1).replace(",", "."));
             
            }
        }}catch(Exception e){
            for(StackTraceElement s : e.getStackTrace()){
                System.err.println(s);
            }
        }
        return summ != null ? summ : null;
    }

    public String getDate(String result) {
        String regex = "^(?:\\w*[:]*\\s*)(\\d{2}[.]\\d{2}[.]\\d{2,4}).*\\n?";               
        Matcher m = Pattern.compile(regex).matcher(result);
        System.out.println(m.matches());
        String date = null;
        if (m.matches()) {
            date = m.group(1);
        }
        return date;
    }
}
