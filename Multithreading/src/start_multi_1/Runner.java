package start_multi_1;

public class Runner extends Thread {
    public void run() {
        for(int i = 0; i < 50; i++) {
            System.out.println("Hello world! " + i);
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
