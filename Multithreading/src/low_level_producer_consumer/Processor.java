package low_level_producer_consumer;

import java.util.LinkedList;
import java.util.Random;

public class Processor {
    private LinkedList<Integer> linkedList = new LinkedList<>();
    private final int LIMIT = 10;
    private Object lock = new Object();

    public void produce() throws InterruptedException {
        int value = 0;
        while (true) {
            synchronized (lock) {
                while (linkedList.size() == LIMIT) {
                    lock.wait();
                }
                linkedList.add(value++);
                System.out.println("Value added, new size: " + linkedList.size());
                lock.notify();
            }
        }
    }

    public void consume() throws InterruptedException {
        Random random = new Random();
        while (true) {
            synchronized (lock) {
                while (linkedList.size() == 0) {
                    lock.wait();
                }
                System.out.print("List size is: " + linkedList.size() + "; ");
                int value = linkedList.removeFirst();
                System.out.println("value is " + value);
                lock.notify();
                Thread.sleep(random.nextInt(1000));
            }
        }
    }
}
