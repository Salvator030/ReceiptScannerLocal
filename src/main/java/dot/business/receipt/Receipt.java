package dot.business.receipt;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Receipt {

    private SimpleStringProperty shopName;
    private SimpleStringProperty summ;
    private SimpleStringProperty date;
    private SimpleStringProperty purpose;

    public Receipt(String date, String shopName, String summ, String purpose) {
        this.shopName = new SimpleStringProperty(shopName);
        this.date = new SimpleStringProperty(date);
        this.summ = new SimpleStringProperty(summ);
        this.purpose = new SimpleStringProperty(purpose);

    };

    public Receipt(String date, String shopName, String summ) {
        this.shopName = new SimpleStringProperty(shopName);
        this.date = new SimpleStringProperty(date);
        this.summ = new SimpleStringProperty(summ);
        this.purpose = new SimpleStringProperty("nicht gesetz");

    }

    public String getShopName() {
        return shopName.get();
    }

    public SimpleStringProperty shopNameProperty() {
        return this.shopName;
    }

    public void setShopName(String shopName) {
        this.shopName.set(shopName);
    }

    public String getSumm() {
        return this.summ.get();
    }

    public SimpleStringProperty summProperty(){
        return this.summ;
    }

    public void setSumm(String summ) {
        this.summ.set(summ);
    }

    public String getDate() {
        return date.get();
    }

    public SimpleStringProperty dateProperty(){
        return this.date;
    }
    public void setDate(String date) {
        this.date.set(date);
    }

    public String getPurpose() {
        return purpose.get();
    }

    public SimpleStringProperty purposeProperty(){
        return this.purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose.set(purpose);
    }

}

// private String checkDate(String date) {
// System.out.println(date);
// if (date.length() == 8) {
// String[] temp = date.split("[.]");
// System.out.println(temp.length);
// System.out.println(temp[0]);
// System.out.println(temp[1]);
// System.out.println(temp[2]);
// return temp[0] + "." + temp[1] + ".20" + temp[2];

// }
// return date;
// }
