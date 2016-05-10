package humantask;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import flow.swf.greeter.GreeterWorkflowClientExternal;
import flow.swf.greeter.GreeterWorkflowClientExternalFactory;
import flow.swf.greeter.GreeterWorkflowClientExternalFactoryImpl;

public class HumanTaskMain {

    public static void main(String[] args) throws Exception {
        ClientConfiguration config = new ClientConfiguration().withSocketTimeout(70*1000);

        //String swfAccessId = System.getenv("AWS_ACCESS_KEY_ID");
        //String swfSecretKey = System.getenv("AWS_SECRET_KEY");
        String swfAccessId = "AKIAI27LYLNPH6GKZ4GQ";
        String swfSecretKey = "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";
        AWSCredentials awsCredentials = new BasicAWSCredentials(swfAccessId, swfSecretKey);

        AmazonSimpleWorkflow service = new AmazonSimpleWorkflowClient(awsCredentials, config);
        service.setEndpoint("https://swf.eu-west-1.amazonaws.com");

        String domain = "humanTaskDomain";

      //  GreeterWorkflowClientExternalFactory factory = new GreeterWorkflowClientExternalFactoryImpl(service, domain);
      //  GreeterWorkflowClientExternal greeter = factory.getClient("someID");
      //  greeter.greet();

        HumanTaskWorkflowClientFactory factory = new HumanTaskWorkflowClientFactoryImpl();
        HumanTaskWorkflowClient workflowClient = factory.getClient();
        Promise<Void> done = workflowClient.startWorkflow();

        //HumanTaskWorkflowClientExternalFactoryImpl factory =  new HumanTaskWorkflowClientExternalFactoryImpl(service, domain);
        //HumanTaskWorkflowClientExternal workflowClient = factory.getClient();
        //Promise<Void> done = workflowClient.startWorkflow();
    }
}