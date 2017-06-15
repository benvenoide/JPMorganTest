package it.benvenutobertacco;

/**
 * Created by benvenoide on 14/06/17.
 */
public class Message {

    private int messageType;

    private String productName;

    private float productPrice;

    private int numberOfProducts;

    private String operationOnProducts;

    public int getMessageType() {
        return messageType;
    }

    public String getProductName() {
        return productName;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public int getNumberOfProducts() {
        return numberOfProducts;
    }

    public String getOperationOnProducts() {
        return operationOnProducts;
    }

    // DEBUG
    public String toString(){

        StringBuilder result = new StringBuilder();

        result.append("messageType: ").append(messageType).append("\n");
        result.append("productName: ").append(productName).append("\n");
        result.append("productPrice: ").append(productPrice).append("\n");
        result.append("numberOfProducts: ").append(numberOfProducts).append("\n");
        result.append("operationOnProducts: ").append(operationOnProducts);

        return result.toString();
    }

}
