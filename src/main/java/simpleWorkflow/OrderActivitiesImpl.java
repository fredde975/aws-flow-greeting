package simpleWorkflow;


import java.util.List;

public class OrderActivitiesImpl implements OrderActivities{
    @Override
    public Float calculateSum(List<Order> orderList) {
        float sum = 0;
        for (Order order: orderList) {
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
