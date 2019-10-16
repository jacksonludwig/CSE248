package p1;

public class Faculty implements Observer {
    private static int idTracker = 0;
    private int id;

    public Faculty(Student student) {
        id = ++idTracker;
        System.out.println("New Observer registered: " + id);
        student.register(this);
    }

    @Override
    public void update(String name, double gpa) {
        System.out.println(name + ": " + gpa);
    }
}
