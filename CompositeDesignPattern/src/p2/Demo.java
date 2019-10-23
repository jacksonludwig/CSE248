package p2;

public class Demo {
    public static void main(String[] args) {
        Ticket t1 = new Ticket(50, "ABC", "ASD");
        Wine w1 = new Wine(40, "producer", "2009", 20);
        ItemList list = new ItemList();
        list.addItem(t1);
        list.addItem(w1);
        list.printAllItems();
    }
}
