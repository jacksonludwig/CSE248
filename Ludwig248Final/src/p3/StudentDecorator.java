package p3;

public class StudentDecorator extends Decorator {
    public void doMoreWork() {
        super.doHomework();
        // new things below
        System.out.println("I am also working at MacDonald's.");
    }
}
