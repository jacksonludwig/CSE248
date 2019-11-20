package start_multi_1;

public class Demo {
    public static void main(String[] args) {
        Runner runner0 = new Runner();
        Runner runner1 = new Runner();
        runner0.start();
        runner1.start();
        System.out.println("done");
    }
}
