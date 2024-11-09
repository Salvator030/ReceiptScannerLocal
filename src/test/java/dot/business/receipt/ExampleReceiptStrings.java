package dot.business.receipt;

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

    public static String getExampleReceiptString1() {
        return exampleReceiptString1;
    }

    public static String getExampleReceiptString1_name() {
        return "Blume2000 SE";
    }

    public static String getExampleReceiptString1SummLine() {
        return "\"Betrag EUR 11,28\\n";

    }

    public static double getExampleReceiptString1_summ() {
        return 11.28;
    }

    public static String getExampleReceiptString1_DateLine(){
        return  "Datum: 01.08.2024\n";
    }
    public static String getExampleReceiptString1_date() {
        return "01.08.2024";
    }

    private static String  exampleReceiptString2 = "TEDI GmbH & Co. KG\n" + 
                "Marzahner Promenade 33\n" + 
                "12679 Berlin\n" + 
                "Filiale 5969\n" + 
                "\n" + 
                "Kasse 1\n" + 
                "2060700229 Pflanzgefaess in 3.00\n" + 
                "6972700256 Light.Ladeka.Fla 2.00\n" + 
                "8735100241  Giesskanne 5 L g 3.55\n" + 
                "Kaufsumme: 8.55\n" + 
                "BAR 10.00\n" + 
                "Rückgeld: 1.45\n" + 
                "MWST% Netto + MWST = Brutto\n" + 
                "Mws 19% 7.18 1.37 8.55\n" + 
                "\n" + 
                "TSE-Transaktion: 440026\n" + 
                "TSE-Start: 2023-07-14T13:22:25\n" + 
                "TEE-Stop: -2023-07-14T13:22:50\n" + 
                "TSE-Signaturcount: 916736\n" + 
                "Seriennummer: ftaA8eolMrOEQZJVXOMPiEQ\n" + 
                "\n" + 
                "TSE-Seriennummer: = 4710366d33beacf1c8c33f446c7a\n" + 
                "1199110036361bd12d0dad81be5fa3d\n" + 
                "03\n" + 
                "TSE Signatur: XRB4iYTHOuVNyuzEcgQOH2BAHimwWTSP\n" + 
                "TCMPxBaODUKj9o0qSp/ReDNQs2FVTLC\n" + 
                "klFvMLNVc3rC0nTMUKazqyhYL+xZD8r\n" + 
                "9wC0xW371aAyhHKcTbTg6lhIC0A1wQs\n" + 
                "4JJ0\n" + 
                "\n" + 
                "Filiale|  BonNr|  Bednr|   Datum |Uhrzeit\n" + 
                "F 5969 | 506688| 023444| 14.07.23|  13:22\n" + 
                "Bonstart:            14.07.23    13:22 \n" + 
                "Bonende:             14.07.23    13:22 \n" + 
                "Steuernummer: 316/ 75953/0703\n" + 
                "---------------\n" + 
                "";

                public static String getExampleReceiptString2(){
                    return exampleReceiptString2;
                }

                public static String getExampleReceiptString2_name() {
                    return "TEDI GmbH & Co. KG";
                }

                public static String getExampleReceiptString2_SummLine() {
                    return "Kaufsumme: 8.55\n" ;
            
                }
            
                public static double getExampleReceiptString2_summ() {
                    return 8.55;
                }
            
                public static String getExampleReceiptString2_date() {
                    return "14.07.23";
                }
                public static String getExampleReceiptString2_DateLine(){
                    return   "Bonstart:            14.07.23    13:22 \n";
                }

                private static String exampleReceiptString3 = "A L D I\n" + 
                                        "Woltersdorfer Straße 1, 15566 Schöneiche\n" + 
                                        "\n" + 
                                        "Öffnungszeiten:\n" + 
                                        "Mo-Sa: 07:00 - 20:00\n" + 
                                        "KAFFEE FEIN UND MILD 5 3,99 € 1\n" + 
                                        "KAFFEEM. M. TO GO BECH 14,99 € 2\n" + 
                                        "ANZAHL ARTIKEL 2\n" + 
                                        "\n" + 
                                        "ZU ZAHLEN 18,98 €\n" + 
                                        "\n" + 
                                        "-K-U-N-D-E-N-B-E-L-E-G-\n" + 
                                        "\n" + 
                                        "Termina\n" + 
                                        "\n" + 
                                        "1-ID : 54406313\n" + 
                                        "TA-Nr 3351\n" + 
                                        "\n" + 
                                        "81 BNr 8095\n" + 
                                        "\n" + 
                                        "Kartenzahlung\n" + 
                                        "Kontaktlos\n" + 
                                        "girocard\n" + 
                                        "\n" + 
                                        "EUR 18,98\n" + 
                                        "\n" + 
                                        "PAN ######*********9017\n" + 
                                        "\n" + 
                                        "Karte 2\n" + 
                                        "\n" + 
                                        "EMV-AID\n" + 
                                        "A0000003591010028001\n" + 
                                        "\n" + 
                                        "VU-Nr 0009600034\n" + 
                                        "\n" + 
                                        "AIDPara 0100000002\n" + 
                                        "\n" + 
                                        "Genehmigungs-Nr 260923\n" + 
                                        "Datum 27.09.23 19:05 Uhr\n" + 
                                        "\n" + 
                                        "Zahlung erfolgt\n" + 
                                        "\n" + 
                                        "BJITTE BELEG AUFBEWAHREN\n" + 
                                        "\n" + 
                                        "KARTENZAHLUNG 18,98 €\n" + 
                                        "MWST NETTO MWST-BETRAG BRUTTO\n" + 
                                        "1 7.005 2,73 0,26 3,99\n" + 
                                        "\n" + 
                                        "2 19,00% 12,80 2,39 14,99\n" + 
                                        "\n" + 
                                        "Vielen Dank für den Einkauf\n" + 
                                        "\n" + 
                                        "ALDI SE & Co. KG Werneuchen OT Seefeld\n" + 
                                        "Jetzt sammeln: Miraculous Sammelkarten.\n" + 
                                        "Unser Prospekt auf WhatsApp.\n" + 
                                        "\n" + 
                                        "Jetzt scannen und kostenlos anmelden!\n" + 
                                        "\n" + 
                                        "0034 101 002283 0722 27/09/2023 19:05:28";

                                        public static String getExampleReceiptString3(){
                                            return exampleReceiptString3;
                                        }
                        
                                        public static String getExampleReceiptString3_name() {
                                            return "A L D I";
                                        }
                        
                                        public static String getExampleReceiptString3_SummLine() {
                                            return  "ZU ZAHLEN 18,98 €\n" ;
                                    
                                        }
                                    
                                        public static double getExampleReceiptString3_summ() {
                                            return 18.98;
                                        }
                                    
                                        public static String getExampleReceiptString3_date() {
                                            return "27.09.23";
                                        }
                                        public static String getExampleReceiptString3_DateLine(){
                                            return       "Datum 27.09.23 19:05 Uhr\n";
                                        }

}
