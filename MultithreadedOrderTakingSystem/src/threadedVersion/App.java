package threadedVersion;

public class App {
    public static void main(String[] args) {
        final int TAKER_COUNT = 3; // # of taker threads working simultaneously
        final int ORDER_COUNT = 3; // # of orders per worker thread
        final int HANDLER_COUNT = 2; // # of OrderHandler threads

        OrderQueue queue = new OrderQueue();

        System.out.println("Starting " + TAKER_COUNT + " order takers, each " +
                "producing " + ORDER_COUNT + " orders.");
        System.out.println("Starting " + HANDLER_COUNT + " order handlers.\n");

        String s = "OrderTaker threads      OrderHandler threads    \n " +
                "--------------------------------------------";
        System.out.println(s);

        for (int i = 0; i < TAKER_COUNT; i++) {
            OrderTaker orderTaker = new OrderTaker(ORDER_COUNT, queue);
            orderTaker.start();
        }

        for (int i = 0; i < HANDLER_COUNT; i++) {
            OrderHandler orderHandler = new OrderHandler(queue);
            orderHandler.start();
        }
    }
}
