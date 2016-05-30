package startWorkflow;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.model.WorkflowExecution;
import common.ConfigHelper;


import java.io.IOException;

public class StartWorkflow {
    private String swfAccessId = "AKIAI27LYLNPH6GKZ4GQ";
    private String swfSecretKey = "oA7JARv0qct/ITcaa5RVGNwvZS12cXKOHUg9AQGv";
    private String swfEndpointName = "https://swf.eu-west-1.amazonaws.com";
    private String domain = "Samples2";
    private String defaultLambdaRoleArn = "arn:aws:iam::487526570401:role/swf-lambda";


    public String myHandler(int myCount, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("received : " + myCount);
        return String.valueOf(myCount);
    }

    public void startWorkflowHandler(S3EventNotification notification, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("Got an s3 event!");
        try {
            for (S3EventNotification.S3EventNotificationRecord record : notification.getRecords()) {
                System.out.println(record.getEventSource());
                System.out.println(record.getEventName());
                System.out.println(record.getS3().getBucket().getName());
                System.out.println(record.getS3().getObject().getKey());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        //logger.log("received : " + s3event.name());
        //logger.log("context : " + context.toString());
        ConfigHelper configHelper = null;
        try {
            configHelper = ConfigHelper.createConfig();
        } catch (IOException e) {
        }

        AmazonSimpleWorkflow swfService = configHelper.createSWFClient();
        String domain = configHelper.getDomain();
        String defaultLambdaRoleArn = configHelper.getSwfLambdaRoleArn();

        MyLambdaWorkflowClientExternalFactory clientFactory = new MyLambdaWorkflowClientExternalFactoryImpl(swfService, domain);
        MyLambdaWorkflowClientExternal workflow = clientFactory.getClient();

        // give the ARN of an IAM role that allows SWF to invoke lambda functions on your behalf
        StartWorkflowOptions options = new StartWorkflowOptions().withLambdaRole(defaultLambdaRoleArn);

        // Start Workflow Execution
        workflow.myWorld("User", options);

        // WorkflowExecution is available after workflow creation
        WorkflowExecution workflowExecution = workflow.getWorkflowExecution();
        logger.log("Started helloLambda workflow with workflowId=\"" + workflowExecution.getWorkflowId()
                + "\" and runId=\"" + workflowExecution.getRunId() + "\"");
    }

    public void startWorkflowSimpleHandler(S3EventNotification notification, final Context context){
        LambdaLogger logger = context.getLogger();
        logger.log("Got an s3 event!");
        String imageUrl = null;
        try {
            for (S3EventNotification.S3EventNotificationRecord record : notification.getRecords()) {
                System.out.println(record.getEventSource());
                System.out.println(record.getEventName());
                System.out.println(record.getAwsRegion());
                System.out.println(record.getS3().getBucket().getName());
                System.out.println(record.getS3().getObject().getKey());
                imageUrl = "https://s3-eu-west-1.amazonaws.com" + "/" + record.getS3().getBucket().getName() + "/" + record.getS3().getObject().getKey();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        ClientConfiguration config = new ClientConfiguration().withSocketTimeout(70*1000);

        //create swf-service
        AWSCredentials awsCredentials = new BasicAWSCredentials(swfAccessId, swfSecretKey);
        AmazonSimpleWorkflow swfService = new AmazonSimpleWorkflowClient(awsCredentials, config);
        swfService.setEndpoint(swfEndpointName);

        MyLambdaWorkflowClientExternalFactory clientFactory = new MyLambdaWorkflowClientExternalFactoryImpl(swfService, domain);
        MyLambdaWorkflowClientExternal workflow = clientFactory.getClient();

        // give the ARN of an IAM role that allows SWF to invoke lambda functions on your behalf
        StartWorkflowOptions options = new StartWorkflowOptions().withLambdaRole(defaultLambdaRoleArn);

        // Start Workflow Execution
        //workflow.myWorld("User", options);
        workflow.myWorld(imageUrl, options);

        // WorkflowExecution is available after workflow creation
        WorkflowExecution workflowExecution = workflow.getWorkflowExecution();
        logger.log("Started myLambda workflow with workflowId=\"" + workflowExecution.getWorkflowId()
                + "\" and runId=\"" + workflowExecution.getRunId() + "\"");
    }
}