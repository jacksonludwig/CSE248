package threadedVersion;

public class OrderTaker extends Thread {
    private static int orderNumber = 0;

    private int count = 0;
    private int maxOrders;
    private OrderQueue orderQueue;

    public OrderTaker(int orderCount, OrderQueue queue) {
        this.maxOrders = orderCount;
        this.orderQueue = queue;
    }

    @Override
    public void run() {
        Order order;
        while (count < maxOrders) {
            order = new Order(getOrderNumber());
            orderQueue.pushOrder(order);
            System.out.println("Created By: " + this.getName());
            count++;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static synchronized int getOrderNumber() {
        return orderNumber++;
    }
}
