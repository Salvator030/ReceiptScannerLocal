package dot.javaFX.objects;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import dot.business.receipt.Receipt;

public class ReceiptsValuesTableRow implements Comparable<ReceiptsValuesTableRow> {

    private String number;
    private String date;
    private String shopName;
    private String purpose;
    private String summ;

    public ReceiptsValuesTableRow(int number, Date date, String shopName, String purpose, Double summ) {
        this.number = Integer.toString(number);
        DateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        this.date = formater.format(date);
        this.shopName = shopName;
        this.purpose = purpose;
        this.summ = "" + summ;
    }

    public ReceiptsValuesTableRow(int number, Receipt receipt, String purpose) {
        this.number = Integer.toString(number);
        DateFormat formater = new SimpleDateFormat("dd.MM.yyyy");
        this.date = formater.format(receipt.getDate());
        this.shopName = receipt.getShopName();
        this.purpose = purpose;
        this.summ = Double.toString(receipt.getSumm());
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
        try {
            return new SimpleDateFormat("dd.MM.yyyy").parse(this.date)
                    .compareTo(new SimpleDateFormat("dd.MM.yyyy").parse(row.getDate()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return 0;
    }

}
