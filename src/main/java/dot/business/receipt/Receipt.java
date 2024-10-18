package dot.business.receipt;

import java.util.Date;

public class Receipt {

    private String shopName;
    private double summ;
    private Date date;

    public Receipt(Date date, String shopName, double summ) {
        this.shopName = shopName;
        this.summ = summ;
        this.date = date;
    }

    public Receipt(Object object, Object object2) {
        //TODO Auto-generated constructor stub
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

    public void setDate(Date date) {
        this.date = date;
    }

}
