package it.benvenutobertacco;

/**
 * Created by benvenoide on 15/06/17.
 */
public class Report {

    private String productName;

    private int saleNumber;

    private float saleValue;

    public Report(String prodName, int num, float value){

        this.productName = prodName;
        this.saleNumber = num;
        this.saleValue = value;
    }

    public void add( float value){
        saleValue += value;
    }

    public void subtract( float value){
        saleValue -= value;
    }

    public void multiply( float value){
        saleValue *= value;
    }

    public String getProductName() {
        return productName;
    }

    public int getSaleNumber() {
        return saleNumber;
    }

    public float getSaleValue() {
        return saleValue;
    }
}
