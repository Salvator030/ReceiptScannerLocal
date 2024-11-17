package dot.javaFX.objects;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import dot.business.receipt.Receipt;

public class ReceiptsValuesTableRow implements Comparable<ReceiptsValuesTableRow> {

    private String number;
    private String date;
    private String shopName;
    private String purpose;
    private String summ;

    public ReceiptsValuesTableRow(int number, String date, String shopName, String purpose, Double summ) {
        this.number = Integer.toString(number);
        this.date = date;
        this.shopName = shopName;
        this.purpose = purpose;
        this.summ = "" + summ;
    }

    public ReceiptsValuesTableRow(int number, Receipt receipt, String purpose) {
        this.number = Integer.toString(number);
        this.date = receipt.getDate();
        this.shopName = receipt.getShopName();
        this.purpose = purpose;
        this.summ = Double.toString(receipt.getSumm());
    }

    public ReceiptsValuesTableRow(String date) {
        this.number = "";

        this.date = date;
        this.shopName = "";
        this.purpose = "";
        this.summ = "";
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        System.out.println(number);
        this.number = number;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getSumm() {
        return summ;
    }

    public void setSumm(String summ) {
        this.summ = summ;
    }

    @Override
    public int compareTo(ReceiptsValuesTableRow row) {
        String tempDate = this.date;
        String rowDate = row.getDate();
        if (tempDate.matches("\\d{2}.\\d{4}")) {
            tempDate = "01." + date;
        }
        else if(tempDate.equals("nicht erkannt")){
            tempDate = "01.01.2100";
        }
        System.out.println(rowDate);
        if (rowDate.matches("\\d{2}.\\d{4}")) {
            rowDate = "01." + rowDate;
        }
        else if(rowDate.equals("nicht erkannt")){
            rowDate = "01.01.2100";
        }
       
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(tempDate)
                    .compareTo(new SimpleDateFormat("dd.MM.yyyy").parse(rowDate));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

}
