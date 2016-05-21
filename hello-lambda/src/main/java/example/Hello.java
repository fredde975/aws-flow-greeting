package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class Hello {
    public String myHandler(String input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("myHandler received : " + input);
        return input;
    }
}