package example.flow.hello;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;

public class HelloFlowWorkflowImpl implements HelloFlowWorkflow{
    private HelloFlowActivitiesClient client = new HelloFlowActivitiesClientImpl();

    @Override
    public void sayHelloFlow(String input) {
        System.out.println("the workflow worker got input = " + input);
        Promise<String> result = client.sayHello(input);
        while (!result.isReady()) {
            System.out.println("Result : " + result);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
