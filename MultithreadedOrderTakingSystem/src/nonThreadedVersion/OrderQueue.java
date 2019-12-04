package nonThreadedVersion;

import java.util.LinkedList;

public class OrderQueue {
    private LinkedList<Order> orderQueue = new LinkedList<>();

    public void pushOrder(Order order) {
        orderQueue.addLast(order);
    }

    public Order pullOrder() {
        return orderQueue.removeFirst();
    }
}
