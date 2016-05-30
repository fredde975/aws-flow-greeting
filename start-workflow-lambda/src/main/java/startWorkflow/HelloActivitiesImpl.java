package startWorkflow;


import com.amazonaws.services.simpleworkflow.flow.ActivityExecutionContext;
import com.amazonaws.services.simpleworkflow.flow.ActivityExecutionContextProvider;
import com.amazonaws.services.simpleworkflow.flow.ActivityExecutionContextProviderImpl;
import com.amazonaws.services.simpleworkflow.flow.annotations.ManualActivityCompletion;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;

import java.util.List;

public class HelloActivitiesImpl implements HelloActivities{
    ActivityExecutionContextProvider contextProvider = new ActivityExecutionContextProviderImpl();


    @ManualActivityCompletion
    @Override
    public String mailImageReview(String input) throws InterruptedException {
        ActivityExecutionContext executionContext = contextProvider.getActivityExecutionContext();
        String taskToken = executionContext.getTaskToken();
        AWSUtils.sendEmail("fredrik.thernelius@gmail.com", "Kolla på bilden (" + input + ") och stäng genom att skicka \"OK\" eller \"NOK\" tillsammans med token: \n" + taskToken);
        return "This will not be returned to the caller";
    }

    @Override
    public String handleReviewResponse(String imageUrl) {
        System.out.println("Review response url: " + imageUrl);
        return null;
    }

    //    @Override
//    public Float calculateSum(List<HelloOrder> orderList) {
//        float sum = 0;
//        for (HelloOrder order: orderList) {
//            System.out.println("orderPrice = " + order.getPrice() + "   orderAmount = " + order.getAmout());
//            sum += new Float(order.getAmout()) * order.getPrice();
//        }
//        return sum;
//    }
//
//    @Override
//    public void printResult(Float sum) {
//        System.out.println("sum = " + sum);
//    }
}
