package p2;

public class Demo {
    public static void main(String[] args) {
        Student s = new Student("AAA", 2.5);
        GpaObserver o1 = new GpaObserver(s);
        s.setGpa(1.0);
        GpaObserver o2 = new GpaObserver(s);
        s.setGpa(2.0);
        s.deleteObserver(o2);
        s.setGpa(0.0);
    }
}
