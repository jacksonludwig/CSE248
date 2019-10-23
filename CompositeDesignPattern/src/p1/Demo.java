package p1;

public class Demo {
    public static void main(String[] args) {
        Ticket t1 = new Ticket("Bill", "Left 5", "10/23", 50.00);
        Wine w1 = new Wine("Bill's Wine", "2009", 11, 29.99);
        ItemList list = new ItemList();
        list.add(t1);
        list.add(w1);
        list.printAllItems();
    }
}
