package p2;

import java.util.ArrayList;

public class ItemList {
    private ArrayList<ConsumableItem> list;

    public ItemList() {
        list = new ArrayList<>();
    }

    public void addItem(ConsumableItem item) {
        list.add(item);
    }

    public void printAllItems() {
        for(int i = 0; i < list.size(); i++) {
            list.get(i).print();
        }
    }
}
