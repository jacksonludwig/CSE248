package synchronization_continued;

import java.util.Scanner;

public class Processor extends Thread {
    private volatile boolean flag = true;

    @Override
    public void run() {
        while (flag) {
            System.out.println("Hi");
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void shutdown() {
        flag = false;
    }

    public static void main(String[] args) {
        Processor processor1 = new Processor();
        processor1.start();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Press enter to pause");
        scanner.hasNextLine();
        processor1.shutdown();
    }
}
