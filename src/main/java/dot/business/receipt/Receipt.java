package dot.business.receipt;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.input.DataFormat;

public class Receipt {

    private int indexInTable;
    private String shopName;
    private double summ;
    private String date;
    private String purpose;
    private final SimpleDateFormat formaterFullDate = new SimpleDateFormat("dd.MM.yyyy");;
    private final SimpleDateFormat formaterMonthAndYear = new SimpleDateFormat("MM.yyyy");;

    public Receipt(String date, String shopName, double summ) {
        this.shopName = shopName;
        this.summ = summ;
        if(date .length() == 8){
            String[] temp = date.split(".");
            this.date = temp[0]+"."+temp[1]+".20"+temp[2];
        }
        this.date = date;
    }

 
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public double getSumm() {
        return summ;
    }

    public void setSumm(double summ) {
        this.summ = summ;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getIndexInTable() {
        return indexInTable;
    }


    public void setIndexInTable(int indexInTable) {
        this.indexInTable = indexInTable;
    }


    public String getPurpose() {
        return purpose;
    }


    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    

}
