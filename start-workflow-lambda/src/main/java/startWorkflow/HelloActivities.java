package startWorkflow;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;
import com.amazonaws.services.simpleworkflow.flow.core.Promise;

import java.util.List;

@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 300, defaultTaskStartToCloseTimeoutSeconds = 120)
@Activities(version = "3.0")
public interface HelloActivities {
    String mailImageReview(String input) throws InterruptedException;
    String handleReviewResponse(String imageUrl);


//    public Float calculateSum(List<HelloOrder> orderList) ;
//
//    public void printResult(Float sum);
}