package startWorkflow;


import java.util.List;

public class HelloActivitiesImpl implements HelloActivities{
    @Override
    public Float calculateSum(List<HelloOrder> orderList) {
        float sum = 0;
        for (HelloOrder order: orderList) {
            System.out.println("orderPrice = " + order.getPrice() + "   orderAmount = " + order.getAmout());
            sum += new Float(order.getAmout()) * order.getPrice();
        }
        return sum;
    }

    @Override
    public void printResult(Float sum) {
        System.out.println("sum = " + sum);
    }
}
