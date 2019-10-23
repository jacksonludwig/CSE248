package p1;

public class Wine implements InterfaceItem {

    private String producer;
    private String vintage;
    private int abv;
    private double price;

    public Wine(String producer, String vintage, int abv, double price) {
        this.producer = producer;
        this.vintage = vintage;
        this.abv = abv;
        this.price = price;
    }

    @Override
    public void print() {
        System.out.println(producer + "\t" + vintage + "\t" + abv + "\t" + price);
    }
}
