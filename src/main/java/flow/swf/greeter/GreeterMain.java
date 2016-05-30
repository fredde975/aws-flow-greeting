package flow.swf.greeter;

import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import java.util.UUID;

public class GreeterMain {

    public static void main(String[] args) throws Exception {
        AmazonSimpleWorkflow service = Common.createSWFClient();

        String domain = "helloWorldWalkthrough";

        GreeterWorkflowClientExternalFactory factory = new GreeterWorkflowClientExternalFactoryImpl(service, domain);
        GreeterWorkflowClientExternal greeter = factory.getClient("SomeId");
        greeter.greet();
    }
}