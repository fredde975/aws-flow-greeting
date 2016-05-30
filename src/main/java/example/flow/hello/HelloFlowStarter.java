package example.flow.hello;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;


public class HelloFlowStarter {

    public static void main(String[] args) throws Exception {
        ClientConfiguration config = new ClientConfiguration().withSocketTimeout(70*1000);

        //env. variables set by IntelliJ
        String swfAccessId = System.getenv("AWS_ACCESS_KEY_ID");
        String swfSecretKey = System.getenv("AWS_SECRET_KEY");
        AWSCredentials awsCredentials = new BasicAWSCredentials(swfAccessId, swfSecretKey);

        AmazonSimpleWorkflow service = new AmazonSimpleWorkflowClient(awsCredentials, config);
        service.setEndpoint("https://swf.eu-west-1.amazonaws.com");

        //String domain = "HelloDomain2";
        String domain = "helloFlow";

        HelloFlowWorkflowClientExternalFactory factory = new HelloFlowWorkflowClientExternalFactoryImpl(service, domain);
        HelloFlowWorkflowClientExternal client = factory.getClient("someWorkflowID");
        client.sayHelloFlow("Amazon Flow");
    }
}