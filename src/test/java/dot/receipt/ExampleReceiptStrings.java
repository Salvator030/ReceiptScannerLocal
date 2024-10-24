package dot.receipt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExampleReceiptStrings {
    private static String exampleReceiptString1 = "* * Kundenbeleg * *\n" + 
    "Blume2000 SE\n" + 
    "\n" + 
    "Filiale 1140\n" + 
    "\n" + 
    "Marzahner Promenade 1 A\n" + 
    "12679 Berlin\n" + 
    "\n" + 
    "Danke für Ihren Einkauf!\n" + 
    "\n" + 
    "Datum: 01.08.2024\n" + 
    "Uhrzeit: 14:04:13 Uhr\n" + 
    "Beleg-Nr. 1867\n" + 
    "Trace-Nr., 119815\n" + 
    "Kartenzahlung\n" + 
    "\n" + 
    "kontakt 1os\n" + 
    "\n" + 
    "SEPA Lastschrift\n" + 
    "\n" + 
    "Online\n" + 
    "\n" + 
    "CDGM\n" + 
    "\n" + 
    "Kurz-BLZ RER\n" + 
    "Kto. HR2I01\n" + 
    "Karte 2 gültig bis 12/27\n" + 
    "IBAN DEB21082901\n" + 
    "G-ID DE16Z7700000020245\n" + 
    "M-ID G529523915867240801 1404\n" + 
    "Terminal-ID \\ 65295239\n" + 
    "Betrag EUR 11,28\n" + 
    "\n" + 
    "Transaktion erfalgreich\n" + 
    "\n" + 
    "Bitte Beleg aufbewahren\n" + 
    "\n" + 
    "Die Belastung erfolgt zum nächstmöglichen\n" + 
    "Bankarbeitstag.\n" + 
    "\n" + 
    "Weitere Informationen zu SEPA-Lastschrift:\n" + 
    "https :www.payone, com/\n" + 
    "SEPA-Lastschrift\n" + 
    "\n" + 
    "Informationen zur\n" + 
    "Datenverarbeitung durch\n" + 
    "PAYONE\n" + 
    "\n" + 
    "WWW. payone, COMm/dsgvo";

    public static String getExampleReceiptString1(){
        return exampleReceiptString1;
    }

    public static String getExampleReceiptString1_name(){
        return "blume2000 SE";
    }

    public static double getExampleReceiptString1_summ(){
        return 11.28;
    }

    public static Date getExampleReceiptString1_date() throws ParseException{
        return  new SimpleDateFormat("dd.MM.yyyy").parse("01.08.2024") ;
    }

}
