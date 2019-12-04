package nonThreadedVersion;

public class OrderTaker {
    public OrderTaker(OrderQueue queue, Order order) {
        queue.pushOrder(order);
    }
}
