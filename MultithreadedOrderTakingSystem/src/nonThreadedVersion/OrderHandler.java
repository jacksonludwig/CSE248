package nonThreadedVersion;

public class OrderHandler {
    private Order order;

    public OrderHandler(OrderQueue queue) {
        this.order = queue.pullOrder();
    }

    public Order getOrder() {
        return order;
    }
}
