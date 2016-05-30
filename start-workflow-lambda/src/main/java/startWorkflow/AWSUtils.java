package startWorkflow;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClient;
import com.amazonaws.services.simpleemail.model.*;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflow;
import com.amazonaws.services.simpleworkflow.AmazonSimpleWorkflowClient;

public class AWSUtils {

    public static AmazonSimpleWorkflow createSWFClient() {
        //env. variables set by IntelliJ
        String swfAccessId = System.getenv("AWS_ACCESS_KEY_ID");
        String swfSecretKey = System.getenv("AWS_SECRET_KEY");
        AWSCredentials awsCredentials = new BasicAWSCredentials(swfAccessId, swfSecretKey);
        ClientConfiguration config = new ClientConfiguration().withSocketTimeout(70*1000);

        AmazonSimpleWorkflow client = new AmazonSimpleWorkflowClient(awsCredentials, config);
        client.setEndpoint("https://swf.eu-west-1.amazonaws.com");
        return client;
    }


    public static void sendEmail(String toAddress, String content) {
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
