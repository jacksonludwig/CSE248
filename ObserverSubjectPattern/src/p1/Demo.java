package p1;

public class Demo {
    public static void main(String[] args) {
        Student s = new Student("A", 2.5);
        Faculty o1 = new Faculty(s);
        Faculty o2 = new Faculty(s);
        s.unregister(o2);
        s.setGpa(2.0);

    }
}
