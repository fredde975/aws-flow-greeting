package flow.swf.greeter;

import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.services.simpleworkflow.flow.ActivityExecutionContext;
import com.amazonaws.services.simpleworkflow.flow.ActivityExecutionContextProvider;
import com.amazonaws.services.simpleworkflow.flow.ActivityExecutionContextProviderImpl;
import com.amazonaws.services.simpleworkflow.flow.annotations.ManualActivityCompletion;

public class GreeterActivitiesImpl implements GreeterActivities {
    ActivityExecutionContextProvider contextProvider = new ActivityExecutionContextProviderImpl();


    @Override
    public String getName() {
        return "World";
    }

    @Override
    public String getGreeting(String name) {
        return "Hello " + name;
    }

    @Override
    public void say(String what) {
        System.out.println(what);
    }

    @Override
    @ManualActivityCompletion
    public String humanActionRequired() {
        ActivityExecutionContext executionContext = contextProvider.getActivityExecutionContext();
        String taskToken = executionContext.getTaskToken();
        System.out.println("Task received, completion token: " + taskToken);
        return null;

    }

    @ManualActivityCompletion
    @Override
    public String mailTask() throws InterruptedException {
        ActivityExecutionContext executionContext = contextProvider.getActivityExecutionContext();
        String taskToken = executionContext.getTaskToken();
        common.AWSUtils.sendEmail("fredrik.thernelius@gmail.com", "Please provide a name for the greeting message and close task with token: \n" + taskToken);
        return "This will not be returned to the caller";
    }

}



