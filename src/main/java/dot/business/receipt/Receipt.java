package dot.business.receipt;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javafx.scene.input.DataFormat;

public class Receipt {

    private String shopName;
    private double summ;
    private Date date;
    private final SimpleDateFormat formaterFullDate = new SimpleDateFormat("dd.MM.yyyy");;
    private final SimpleDateFormat formaterMonthAndYear = new SimpleDateFormat("MM.yyyy");;

    public Receipt(Date date, String shopName, double summ) {
        this.shopName = shopName;
        this.summ = summ;
        this.date = date;
    }

    public Receipt(String date, String shopName, double summ) {
        this.shopName = shopName;
        this.summ = summ;
        try {
            if (date.matches("\\d{2}.\\d{4}")) {
                this.date = formaterMonthAndYear.parse(date);
            } else {
                this.date = formaterFullDate.parse(date);
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

    public Date getDate() {
        return date;
    }

    public String getDateFormatStringFullDate() {
        return formaterFullDate.format(this.date);
    }

    public String getDateFormatStringMonthAndYear() {
        return formaterMonthAndYear.format(this.date);
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
