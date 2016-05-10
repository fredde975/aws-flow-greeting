package simpleWorkflow;

import com.amazonaws.services.simpleworkflow.flow.core.Promise;

import java.util.List;

public class OrderWorkflowImpl implements OrderWorkflow{
    OrderActivitiesClient operations = new OrderActivitiesClientImpl();

    @Override
    public void processOrder(List<Order> orderList) {
        Promise<Float> sum = operations.calculateSum(orderList);
        operations.printResult(sum);
    }
}
