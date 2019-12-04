package nonThreadedVersion;

public class App {
    public static void main(String[] args) {
        OrderQueue orderQueue = new OrderQueue();

        Order order1 = new Order();
        OrderTaker orderTaker1 = new OrderTaker(orderQueue, order1);
        OrderHandler orderHandler1 = new OrderHandler(orderQueue);
        System.out.println("order handled: " + orderHandler1.getOrder());

        Order order2 = new Order();
        OrderTaker orderTaker2 = new OrderTaker(orderQueue, order2);
        OrderHandler orderHandler2 = new OrderHandler(orderQueue);
        System.out.println("order handled: " + orderHandler2.getOrder());
    }
}
