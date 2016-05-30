package flow.swf.greeter;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;

@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 300,
        defaultTaskStartToCloseTimeoutSeconds = 120)
@Activities(version="4.0")
public interface GreeterActivities {
    String getName();
    String getGreeting(String name);
    void say(String what);
    String humanActionRequired();
    String mailTask() throws InterruptedException;
}

