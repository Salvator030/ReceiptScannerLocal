package dot.business.receipt;

import dot.asserts.EPurpose;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;

public class Receipt {

    private SimpleStringProperty shopName;
    private SimpleStringProperty summ;
    private SimpleStringProperty date;
    private SimpleObjectProperty<EPurpose> purpose;

    public Receipt(String date, String shopName, String summ, EPurpose purpose) {
        this.shopName = new SimpleStringProperty(shopName);
        this.date = new SimpleStringProperty(date);
        this.summ = new SimpleStringProperty(summ);
        this.purpose = new SimpleObjectProperty<EPurpose>(purpose);

    };

    public Receipt(String date, String shopName, double summ, EPurpose purpose) {
        this.shopName = new SimpleStringProperty(shopName);
        this.date = new SimpleStringProperty(date);
        this.summ = new SimpleStringProperty(summ +"");
        this.purpose = new SimpleObjectProperty<EPurpose>(purpose);

    };

    public Receipt(String date, String shopName, String summ) {
        this.shopName = new SimpleStringProperty(shopName);
        this.date = new SimpleStringProperty(date);
        this.summ = new SimpleStringProperty(summ);
        this.purpose = new SimpleObjectProperty<EPurpose>();
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

    public EPurpose getPurpose() {
        return purpose.get();
    }

    public SimpleObjectProperty<EPurpose> purposeProperty(){
        return this.purpose;
    }

    public void setPurpose(EPurpose purpose) {
        this.purpose.set(purpose);
    }

    @Override
    public String toString(){
        return "date: " + date.get() + "\tname: " + shopName.get() + "\tsumm: " + summ.get();
    }
}


