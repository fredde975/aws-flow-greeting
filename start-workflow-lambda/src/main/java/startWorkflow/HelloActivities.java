package startWorkflow;

import com.amazonaws.services.simpleworkflow.flow.annotations.Activities;
import com.amazonaws.services.simpleworkflow.flow.annotations.ActivityRegistrationOptions;

import java.util.List;

@ActivityRegistrationOptions(defaultTaskScheduleToStartTimeoutSeconds = 300,
        defaultTaskStartToCloseTimeoutSeconds = 100)
@Activities(version = "1.0")
public interface HelloActivities {
    public Float calculateSum(List<HelloOrder> orderList) ;

    public void printResult(Float sum);
}