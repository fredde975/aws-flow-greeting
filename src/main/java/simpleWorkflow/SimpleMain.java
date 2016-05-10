package simpleWorkflow;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import flow.swf.greeter.GreeterWorkflowClientExternal;
import flow.swf.greeter.GreeterWorkflowClientExternalFactory;
import flow.swf.greeter.GreeterWorkflowClientExternalFactoryImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public class SimpleMain {

    public static void main(String[] args) throws Exception {
        ClientConfiguration config = new ClientConfiguration().withSocketTimeout(70*1000);

        //String swfAccessId = System.getenv("AWS_ACCESS_KEY_ID");
        //String swfSecretKey = System.getenv("AWS_SECRET_KEY");
        String swfAccessId = "AKIAI27LYLNPH6GKZ4GQ";
        String swfSecretKey = "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";
        AWSCredentials awsCredentials = new BasicAWSCredentials(swfAccessId, swfSecretKey);

        AmazonSimpleWorkflow service = new AmazonSimpleWorkflowClient(awsCredentials, config);
        service.setEndpoint("https://swf.eu-west-1.amazonaws.com");

        String domain = "orderWorkflow";

        OrderWorkflowClientExternalFactory factory = new OrderWorkflowClientExternalFactoryImpl(service, domain);
        OrderWorkflowClientExternal workflow = factory.getClient(UUID.randomUUID().toString());
        workflow.processOrder(generateOrderList());
    }

    private static List<Order> generateOrderList(){
        int minNumber = 1;
        int minAmount = 1;
        int maxAmount = 99;
        int maxNumber = 99;
        float minPrice = 1;
        float maxPrice = 100;

        List<Order> orderList = new ArrayList<>();
        int randomNumber = minNumber + (int)(Math.random() * ((maxNumber - minNumber) + 1));

        for(int i = 0; i < randomNumber; i++) {
            int randomAmount = minAmount + (int)(Math.random() * ((maxAmount - minAmount) + 1));
            Float randomPrice = new Float(minPrice + (int)(Math.random() * ((maxPrice - minPrice) + 1)));
            orderList.add(new Order(randomAmount, randomPrice));
        }

        return orderList;
    }
}