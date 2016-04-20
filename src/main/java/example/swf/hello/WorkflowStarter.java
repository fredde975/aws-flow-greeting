package example.swf.hello;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.model.*;

public class WorkflowStarter {
    private static AmazonSimpleWorkflowClient swf = new AmazonSimpleWorkflowClient();
    public static final String WORKFLOW_EXECUTION = "HelloWorldWorkflowExecution";

    public static void main(String[] args) {
        String workflow_input = "Amazon SWF";
        if (args.length > 0) {
            workflow_input = args[0];
        }
        System.out.println("Starting the workflow execution '" + WORKFLOW_EXECUTION +
                "' with input '" + workflow_input + "'.");
        WorkflowType wf_type = new WorkflowType()
                .withName(HelloTypes.WORKFLOW)
                .withVersion(HelloTypes.WORKFLOW_VERSION);
        Run run = swf.startWorkflowExecution(new StartWorkflowExecutionRequest()
                .withDomain(HelloTypes.DOMAIN)
                .withWorkflowType(wf_type)
                .withWorkflowId(WORKFLOW_EXECUTION)
                .withInput(workflow_input)
                .withExecutionStartToCloseTimeout("90"));
        System.out.println("Workflow execution started with the run id '" +
                run.getRunId() + "'.");
    }
}
