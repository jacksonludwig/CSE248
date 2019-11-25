package synchronized_code_block;

import java.util.ArrayList;
import java.util.Random;

public class Worker {
    private Random random = new Random();
    private Object lock1 = new Object();
    private Object lock2 = new Object();

    private ArrayList<Integer> list1 = new ArrayList<>();
    private ArrayList<Integer> list2 = new ArrayList<>();

    public void fillList1() {
        synchronized (lock1) {
            list1.add(random.nextInt(100));
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void fillList2() {
        synchronized (lock2) {
            list2.add(random.nextInt(100));
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void process() {
        for (int i = 0; i < 1000; i++) {
            fillList1();
            fillList2();
        }
        System.out.println(list1);
        System.out.println(list2);
    }

    public void threadWork() {
        Thread t1 = new Thread(() -> {
            process();
        });

        Thread t2 = new Thread(() -> {
            process();
        });

        long startTime = System.currentTimeMillis();
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("Start: " + startTime + "\nEnd: " + endTime + "\nTime taken: " + (endTime - startTime));
        System.out.println("List 1: " + list1.size());
        System.out.println("List 2: " + list2.size());
    }
}
