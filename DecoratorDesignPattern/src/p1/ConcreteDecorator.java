package p1;

public class ConcreteDecorator extends Decorator {
    public void doJob() {
        super.doJob();
        // new things below
        System.out.println("Job done by concrete decorator class");
    }
}
