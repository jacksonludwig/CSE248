package p2;

public class Ticket extends ConsumableItem {
    private String name;
    private String date;

    public Ticket(double price, String name, String date) {
        super(price);
        this.name = name;
        this.date = date;
    }

    @Override
    public void print() {
        System.out.println(name + "\t" + date + "\t" + getPrice());
    }
}
