/*
 * This code was generated by AWS Flow Framework Annotation Processor.
 * Refer to Amazon Simple Workflow Service documentation at http://aws.amazon.com/documentation/swf 
 *
 * Any changes made directly to this file will be lost when 
 * the code is regenerated.
 */
 package flow.swf.greeter;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;
import com.amazonaws.services.simpleworkflow.flow.ActivitiesClient;
import com.amazonaws.services.simpleworkflow.flow.ActivitySchedulingOptions;

/**
 * Generated from {@link flow.swf.greeter.GreeterActivities}. 
 * Used to invoke activities asynchronously from workflow code.
 */
public interface GreeterActivitiesClient extends ActivitiesClient
{

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#getName}
     */
    Promise<String> getName();

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#getName}
     */
    Promise<String> getName(Promise<?>... waitFor);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#getName}
     */
    Promise<String> getName(ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#getGreeting}
     */
    Promise<String> getGreeting(String name);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#getGreeting}
     */
    Promise<String> getGreeting(String name, Promise<?>... waitFor);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#getGreeting}
     */
    Promise<String> getGreeting(String name, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#getGreeting}
     */
    Promise<String> getGreeting(Promise<String> name);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#getGreeting}
     */
    Promise<String> getGreeting(Promise<String> name, Promise<?>... waitFor);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#getGreeting}
     */
    Promise<String> getGreeting(Promise<String> name, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#say}
     */
    Promise<Void> say(String what);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#say}
     */
    Promise<Void> say(String what, Promise<?>... waitFor);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#say}
     */
    Promise<Void> say(String what, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#say}
     */
    Promise<Void> say(Promise<String> what);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#say}
     */
    Promise<Void> say(Promise<String> what, Promise<?>... waitFor);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#say}
     */
    Promise<Void> say(Promise<String> what, ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#humanActionRequired}
     */
    Promise<String> humanActionRequired();

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#humanActionRequired}
     */
    Promise<String> humanActionRequired(Promise<?>... waitFor);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#humanActionRequired}
     */
    Promise<String> humanActionRequired(ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#mailTask}
     */
    Promise<String> mailTask();

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#mailTask}
     */
    Promise<String> mailTask(Promise<?>... waitFor);

    /**
     * Generated from {@link flow.swf.greeter.GreeterActivities#mailTask}
     */
    Promise<String> mailTask(ActivitySchedulingOptions optionsOverride, Promise<?>... waitFor);

}