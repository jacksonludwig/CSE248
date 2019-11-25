package synchronized_code_block;

public class Demo {
    public static void main(String[] args) {
        Worker worker1 = new Worker();
        worker1.threadWork();
    }
}
