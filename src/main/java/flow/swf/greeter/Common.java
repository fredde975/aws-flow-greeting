package flow.swf.greeter;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;

public class Common {

    static AmazonSimpleWorkflow createSWFClient() {
        //env. variables set by IntelliJ
        String swfAccessId = System.getenv("AWS_ACCESS_KEY_ID");
        String swfSecretKey = System.getenv("AWS_SECRET_KEY");
        AWSCredentials awsCredentials = new BasicAWSCredentials(swfAccessId, swfSecretKey);
        ClientConfiguration config = new ClientConfiguration().withSocketTimeout(70*1000);

        AmazonSimpleWorkflow client = new AmazonSimpleWorkflowClient(awsCredentials, config);
        client.setEndpoint("https://swf.eu-west-1.amazonaws.com");
        return client;
    }
}
