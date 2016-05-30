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
        sendEmail("fredrik.thernelius@gmail.com", "Please provide a name for the greeting message and close task with token: " + taskToken);
        return "This will not be returned to the caller";
    }

    private void sendEmail(String toAddress, String content) {
        try {

            String fromAddr = toAddress;

            // Construct an object to contain the recipient address.
            Destination destination = new Destination().withToAddresses(new String[]{toAddress});

            // Create the subject and body of the message.
            Content subject = new Content().withData("Dags att jobba");
            Content textBody = new Content().withData(content);
            Body body = new Body().withText(textBody);

            // Create a message with the specified subject and body.
            Message message = new Message().withSubject(subject).withBody(body);

            // Assemble the email.
            SendEmailRequest request = new SendEmailRequest().withSource(fromAddr).withDestination(destination).withMessage(message);

            System.out.println("Attempting to send an email through Amazon SES by using the AWS SDK for Java...");

            // Instantiate an Amazon SES client, which will make the service call. The service call requires your AWS credentials.
            // Because we're not providing an argument when instantiating the client, the SDK will attempt to find your AWS credentials
            // using the default credential provider chain. The first place the chain looks for the credentials is in environment variables
            // AWS_ACCESS_KEY_ID and AWS_SECRET_KEY.
            // For more information, see http://docs.aws.amazon.com/AWSSdkDocsJava/latest/DeveloperGuide/credentials.html
            AmazonSimpleEmailServiceClient client = new AmazonSimpleEmailServiceClient();

            // Choose the AWS region of the Amazon SES endpoint you want to connect to. Note that your sandbox
            // status, sending limits, and Amazon SES identity-related settings are specific to a given AWS
            // region, so be sure to select an AWS region in which you set up Amazon SES. Here, we are using
            // the US West (Oregon) region. Examples of other regions that Amazon SES supports are US_EAST_1
            // and EU_WEST_1. For a complete list, see http://docs.aws.amazon.com/ses/latest/DeveloperGuide/regions.html
            Region REGION = Region.getRegion(Regions.EU_WEST_1);
            client.setRegion(REGION);

            // Send the email.
            client.sendEmail(request);
            System.out.println("Email sent!");
        } catch (Exception ex)

        {
            System.out.println("The email was not sent.");
            System.out.println("Error message: " + ex.getMessage());
        }
    }
}



