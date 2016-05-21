package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class Hello {
    public String myHandler(int input, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("context function name :" + context.getFunctionName());
        logger.log("function version :" + context.getFunctionVersion());
        System.out.println("context function name :" + context.getFunctionName());
        System.out.println("function version :" + context.getFunctionVersion());

        logger.log("myHandler received : " + input);
        return String.valueOf(input);
    }
}