package it.benvenutobertacco;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by benvenoide on 14/06/17.
 */
public class MessageManager {

    private List<Message> messageList;

    private Map<String,List<Report>> reportMap;

    public MessageManager(Reader reader){

        reportMap = new HashMap<String,List<Report>>();

        Gson gson = new Gson();

        try {

            // Convert JSON to Java Object List
            messageList = gson.fromJson(reader, new TypeToken<List<Message>>(){}.getType());

            // DEBUG
            /*for (Message message: messageList) {

                System.out.println("DEBUG --> message: " + message.toString());
            }*/

        } catch (Exception e) {
            System.out.println("ERROR --> json parsing error");
            e.printStackTrace();
        }
    }

    public void run(){

        int count = 0;

        for (Message message: messageList) {

            switch (message.getMessageType()){

                case 1:
                    // do nothing
                    break;

                case 2:
                    addReport(message);
                    break;

                case 3:
                    handleOperationOnProduct(message);
                    break;

                default:
                    // unknown message type, don't add
                    System.out.println("WARN --> unknown message type");
                    System.out.println("message: " + message.toString());
            }

            count++;

            if( count % 10 == 0 ){

                System.out.println("DEBUG --> count: " + count);

                logReport();
            }

            if ( count == 50 ){

                logAdjustments();

                break;
            }
        }
    }


    public void addReport(Message message) {

        Report report = new Report( message.getProductName(),
                                    message.getNumberOfProducts(),
                                    message.getProductPrice());

        if( reportMap.containsKey(message.getProductName()) ){

            reportMap.get(message.getProductName()).add(report);
        }
        else{

            ArrayList<Report> reportList = new ArrayList<Report>();

            reportList.add(report);
            reportMap.put(message.getProductName(), reportList);
        }
    }

    public void handleOperationOnProduct(Message message) {

        // nosense handling if the list is empty
        if( reportMap.containsKey(message.getProductName()) ) {

            for (Report report : reportMap.get(message.getProductName())) {

                boolean shouldBreak = false;
                switch (message.getOperationOnProducts()){

                    case "add":
                        report.add(message.getProductPrice());
                        break;

                    case "subtract":
                        report.subtract(message.getProductPrice());
                        break;

                    case "multiply":
                        report.multiply(message.getProductPrice());
                        break;

                    default:
                        // unknown message type, don't handle
                        System.out.println("WARN --> unknown operation on product");
                        System.out.println("message: " + message.toString());
                        shouldBreak = true;
                }

                if (shouldBreak) break;
            }
        }
    }

    public void logReport(){

        System.out.println("INFO --> total number of sales for each product:");

        for(List<Report> reportList : reportMap.values()) {

            int totalSales = 0;
            String productName = reportList.get(0).getProductName();
            float saleValue = reportList.get(0).getSaleValue();

            for(Report report : reportList) {
                totalSales += report.getSaleNumber();
            }

            System.out.println( " - product: " + productName +
                                " - tot sales: " + totalSales +
                                " - tot value: " + String.format("%.02f", (saleValue * totalSales)) );
        }
    }

    public void logAdjustments(){

        System.out.println("INFO --> application paused, no more messages accepted!");
        System.out.println("INFO --> adjustment report:");

        boolean adjusted = false;
        for (Message message: messageList) {
            if ( message.getMessageType() == 3 ){

                System.out.println(
                        " - product: " + message.getProductName() +
                        " - adjustment operation: " + message.getOperationOnProducts() +
                        " - adjustment value: " + message.getProductPrice());
            }
        }

        if ( !adjusted ){

            System.out.println(" - no adjustments where made on this set of messages...");
        }

    }
}
