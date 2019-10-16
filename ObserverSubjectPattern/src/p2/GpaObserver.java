package p2;

import java.util.Observable;
import java.util.Observer;

public class GpaObserver implements Observer {
    private static int idTracker = 0;
    private int id;

    private Student student;

    public GpaObserver(Student student) {
        this.student = student;
        this.id = ++idTracker;

        System.out.println("New Observer: " + id);
        this.student.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object o) {
        System.out.println(((Student) observable).getName() + ": " + ((Student) observable).getGpa());
        System.out.println(o);
    }
}
