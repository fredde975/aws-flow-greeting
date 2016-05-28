package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class Hello3 {
    public String myHandler3(String input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("myHandler3 received : " + input);
        return input;
    }
}