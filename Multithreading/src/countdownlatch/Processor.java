package countdownlatch;

import java.util.concurrent.CountDownLatch;

public class Processor implements Runnable {

    private CountDownLatch latch;

    public Processor(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        System.out.println("Worker thread: " + Thread.currentThread().getName());
        System.out.println("Started.");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Completed.");

        latch.countDown();
    }
}
