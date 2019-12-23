package p1;

import java.util.ArrayList;

public class Demo {
    private static ArrayList<String> data = new ArrayList<>();

    public static void main(String[] args) {
        Thread t1 = new Thread(() -> {
            addNumbers();
        });

        Thread t2 = new Thread(() -> {
            addUpper();
        });

        Thread t3 = new Thread(() -> {
            addLower();
        });

        t1.start();
        t2.start();
        t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(data);
    }

    private static synchronized void addNumbers() {
        for (int i = 1; i <= 50; i++) {
            data.add(String.valueOf(i));
        }
    }

    private static synchronized void addUpper() {
        for (int i = 65; i <= 90; i++) {
            data.add(String.valueOf((char) i));
        }
    }

    private static synchronized void addLower() {
        for (int i = 97; i <= 122; i++) {
            data.add(String.valueOf((char) i));
        }
    }
}
