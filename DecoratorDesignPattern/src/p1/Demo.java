package p1;

public class Demo {
    public static void main(String[] args) {
        Component c1 = new Component();
        c1.doJob();

        ConcreteDecorator cD1 = new ConcreteDecorator();
        cD1.setComponent(c1);
        cD1.doJob();
    }

}
