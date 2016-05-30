package example.flow.hello;

public class HelloFlowActivitiesImpl implements HelloFlowActivities{
    @Override
    public String sayHello(String input) {
        System.out.println("the activity got input = " + input);
        return "Hello, " + input + "!";
    }
}

