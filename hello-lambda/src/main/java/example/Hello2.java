package example;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;

public class Hello2 {
    public String myHandler2(int myCount, Context context) {
        LambdaLogger logger = context.getLogger();
        logger.log("myHandler2 received : " + myCount);
        return String.valueOf(myCount);
    }
}