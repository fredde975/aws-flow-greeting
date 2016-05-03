package distributed.swf;

import com.amazonaws.services.simpleworkflow.flow.annotations.Asynchronous;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;

public class GreeterWorkflowImpl implements GreeterWorkflow {
    private GreeterActivitiesClient operations = new GreeterActivitiesClientImpl();

    @Override
    public void greet() {
        Promise<String> name = operations.getName();
        Promise<String> greeting = getGreeting(name);
        operations.say(greeting);
    }

    @Asynchronous
    //private Promise<String> getGreeting(Promise<String> name) {
    Promise<String> getGreeting(Promise<String> name) {
        System.out.println("1. getGreeting");
        String returnString = "Hello " + name.get() + "!";
        System.out.println("2. getGreeting");
        return Promise.asPromise(returnString);
    }
}