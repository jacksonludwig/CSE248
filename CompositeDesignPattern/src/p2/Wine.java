package p2;

public class Wine extends ConsumableItem {

    private String producer;
    private String vintage;
    private int abv;

    public Wine(double price, String producer, String vintage, int abv) {
        super(price);
        this.producer = producer;
        this.vintage = vintage;
        this.abv = abv;
    }

    @Override
    public void print() {
        System.out.println(producer + "\t" + vintage + "\t" + abv + "\t" + getPrice());
    }
}
