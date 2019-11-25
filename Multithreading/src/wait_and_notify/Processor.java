package wait_and_notify;

import java.util.Scanner;

public class Processor {

    public void produce() throws InterruptedException {
        System.out.println("Producer is producing...");
        wait();
        System.out.println("Producer RESUMED producing...");
    }

    public void consume() throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        Thread.sleep(1000);
        synchronized (this) {
            System.out.println("Waiting for return key...");
            scanner.hasNextLine();
            System.out.println("Return key pressed...");
            notify();
            Thread.sleep(2000);
        }
    }
}
