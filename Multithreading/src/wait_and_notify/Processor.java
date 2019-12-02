package wait_and_notify;

import java.util.Scanner;

public class Processor {

    Object lock = new Object();

    public void produce() throws InterruptedException {
        synchronized (lock) {
            System.out.println("Producer is producing...");
            lock.wait();
            System.out.println("Producer RESUMED producing...");
        }
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(1000);
        synchronized (lock) {
            System.out.println("Waiting for return key...");
            scanner.hasNextLine();
            System.out.println("Return key pressed...");
            lock.notify();
            Thread.sleep(2000);
        }
    }
}
