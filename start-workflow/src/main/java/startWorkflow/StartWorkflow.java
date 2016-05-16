package startWorkflow;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.s3.event.S3EventNotification;
import com.amazonaws.services.s3.model.S3Event;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.flow.StartWorkflowOptions;
import com.amazonaws.services.simpleworkflow.model.WorkflowExecution;
import common.ConfigHelper;
import hellolambda.HelloLambdaWorkflowClientExternal;
import hellolambda.HelloLambdaWorkflowClientExternalFactory;
import hellolambda.HelloLambdaWorkflowClientExternalFactoryImpl;

import java.io.IOException;

public class StartWorkflow {
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

        HelloLambdaWorkflowClientExternalFactory clientFactory = new HelloLambdaWorkflowClientExternalFactoryImpl(swfService,
                domain);
        HelloLambdaWorkflowClientExternal workflow = clientFactory.getClient();

        // give the ARN of an IAM role that allows SWF to invoke lambda functions on your behalf
        StartWorkflowOptions options = new StartWorkflowOptions().withLambdaRole(defaultLambdaRoleArn);

        // Start Workflow Execution
        workflow.helloWorld("User", options);

        // WorkflowExecution is available after workflow creation
        WorkflowExecution workflowExecution = workflow.getWorkflowExecution();
        logger.log("Started helloLambda workflow with workflowId=\"" + workflowExecution.getWorkflowId()
                + "\" and runId=\"" + workflowExecution.getRunId() + "\"");
    }
}