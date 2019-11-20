package start_multi_2;

public class Demo {
    public static void main(String[] args) {
        Thread t1 = new Thread(new Runner());
        t1.start();

        Thread t2 = new Thread(new Runner());
        t2.start();

        System.out.println("done");
    }
}
