package flow.swf.greeter;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;

public class GreeterMain {

    public static void main(String[] args) throws Exception {
        ClientConfiguration config = new ClientConfiguration().withSocketTimeout(70*1000);

        //String swfAccessId = System.getenv("AWS_ACCESS_KEY_ID");
        //String swfSecretKey = System.getenv("AWS_SECRET_KEY");
        String swfAccessId = "AKIAI27LYLNPH6GKZ4GQ";
        String swfSecretKey = "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";
        AWSCredentials awsCredentials = new BasicAWSCredentials(swfAccessId, swfSecretKey);

        AmazonSimpleWorkflow service = new AmazonSimpleWorkflowClient(awsCredentials, config);
        service.setEndpoint("https://swf.us-east-1.amazonaws.com");

        String domain = "helloWorldWalkthrough";

        GreeterWorkflowClientExternalFactory factory = new GreeterWorkflowClientExternalFactoryImpl(service, domain);
        GreeterWorkflowClientExternal greeter = factory.getClient("someID");
        greeter.greet();
    }
}