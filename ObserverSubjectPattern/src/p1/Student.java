package p1;

import java.util.ArrayList;

public class Student implements Observable {
    private ArrayList<Observer> observerList;
    String name;
    double gpa;

    public Student(String name, double gpa) {
        observerList = new ArrayList<>();
        this.name = name;
        this.gpa = gpa;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notifyObservers();
    }

    public double getGpa() {
        return gpa;
    }

    public void setGpa(double gpa) {
        this.gpa = gpa;
        notifyObservers();
    }

    @Override
    public void register(Observer o) {
        observerList.add(o);
    }

    @Override
    public void unregister(Observer o) {
        int index = observerList.indexOf(o);
        observerList.remove(index);
        System.out.println("Observer" + (index + 1) + " was removed from the list");
    }

    @Override
    public void notifyObservers() {
        for(Observer o : observerList) {
            o.update(name, gpa);
        }
    }
}
