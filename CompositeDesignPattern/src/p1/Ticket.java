package p1;

public class Ticket implements InterfaceItem {

    private String name;
    private String location;
    private String date;
    private double price;

    public Ticket(String name, String location, String date, double price) {
        this.name = name;
        this.location = location;
        this.date = date;
        this.price = price;
    }

    @Override
    public void print() {
        System.out.println(name + "\t" + location + "\t" + date + "\t" + price);
    }
}
