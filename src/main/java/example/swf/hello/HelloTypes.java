package example.swf.hello;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;
import com.amazonaws.services.simpleworkflow.model.*;

public class HelloTypes {
    public static final String DOMAIN = "HelloDomain2";
    public static final String TASKLIST = "HelloTasklist";
    public static final String WORKFLOW = "HelloWorkflow";
    public static final String WORKFLOW_VERSION = "1.0";
    public static final String ACTIVITY = "HelloActivity";
    public static final String ACTIVITY_VERSION = "1.0";


    private static AmazonSimpleWorkflowClient swf = new AmazonSimpleWorkflowClient();

    public static void main(String[] args) {
        registerDomain();
        registerWorkflowType();
        registerActivityType();
    }

    public static void registerDomain() {
        try {
            System.out.println("** Registering the domain '" + DOMAIN + "'.");
            swf.registerDomain(new RegisterDomainRequest()
                    .withName(DOMAIN)
                    .withWorkflowExecutionRetentionPeriodInDays("1"));
        } catch (DomainAlreadyExistsException e) {
            System.out.println("** Domain already exists!");
        }
    }

    public static void registerActivityType() {
        try {
            System.out.println("** Registering the activity type '" + ACTIVITY + "-" + ACTIVITY_VERSION + "'.");
            swf.registerActivityType(new RegisterActivityTypeRequest()
                    .withDomain(DOMAIN)
                    .withName(ACTIVITY)
                    .withVersion(ACTIVITY_VERSION)
                    .withDefaultTaskList(new TaskList().withName(TASKLIST))
                    .withDefaultTaskScheduleToStartTimeout("30")
                    .withDefaultTaskStartToCloseTimeout("600")
                    .withDefaultTaskScheduleToCloseTimeout("630")
                    .withDefaultTaskHeartbeatTimeout("10"));
        } catch (TypeAlreadyExistsException e) {
            System.out.println("** Activity type already exists!");
        }
    }

    public static void registerWorkflowType() {
        try {
            System.out.println("** Registering the workflow type '" + WORKFLOW + "-" + WORKFLOW_VERSION + "'.");
            swf.registerWorkflowType(new RegisterWorkflowTypeRequest()
                    .withDomain(DOMAIN)
                    .withName(WORKFLOW)
                    .withVersion(WORKFLOW_VERSION)
                    .withDefaultChildPolicy(ChildPolicy.TERMINATE)
                    .withDefaultTaskList(new TaskList().withName(TASKLIST))
                    .withDefaultTaskStartToCloseTimeout("30"));
        }
        catch (TypeAlreadyExistsException e) {
            System.out.println("** Workflow type already exists!");
        }
    }

}
