package threadedVersion;

public class OrderHandler extends Thread {
    private OrderQueue queue;

    public OrderHandler(OrderQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Order order;
        while (true) {
            order = queue.pullOrder();
            System.out.println("\t" + order + " processed by " + this.getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
