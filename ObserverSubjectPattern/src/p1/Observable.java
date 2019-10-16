package p1;

public interface Observable {
    void register(Observer o);
    void unregister(Observer o);
    void notifyObservers();
}
