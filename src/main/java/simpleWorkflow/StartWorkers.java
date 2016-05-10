package simpleWorkflow;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.flow.ActivityWorker;
import com.amazonaws.services.simpleworkflow.flow.WorkflowWorker;
import flow.swf.greeter.GreeterActivitiesImpl;
import flow.swf.greeter.GreeterWorkflowImpl;

public class StartWorkers {
    public static void main(String[] args) throws Exception {
        ClientConfiguration config = new ClientConfiguration().withSocketTimeout(70*1000);

//        String swfAccessId = System.getenv("AWS_ACCESS_KEY_ID");
        String swfAccessId = "AKIAI27LYLNPH6GKZ4GQ";
        String swfSecretKey = "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";
//        String swfSecretKey = System.getenv("AWS_SECRET_KEY");


        AWSCredentials awsCredentials = new BasicAWSCredentials(swfAccessId, swfSecretKey);

        AmazonSimpleWorkflow service = new AmazonSimpleWorkflowClient(awsCredentials, config);
        service.setEndpoint("https://swf.eu-west-1.amazonaws.com");

        String domain = "orderWorkflow";
        String taskListToPoll = "orderList";

        ActivityWorker aw = new ActivityWorker(service, domain, taskListToPoll);
        aw.addActivitiesImplementation(new OrderActivitiesImpl());
        aw.start();

        WorkflowWorker wfw = new WorkflowWorker(service, domain, taskListToPoll);
        wfw.addWorkflowImplementationType(OrderWorkflowImpl.class);
        wfw.start();
    }
}
