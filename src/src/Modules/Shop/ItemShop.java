package Modules.Shop;

import Modules.ItemAndAmulet.Item;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemShop {
    private ArrayList<Item> items;
    private HashMap<Item,Integer> ItemsHashMap;


    public void RemoveItem(Item item){
        for (Item Items:items) {
            if(items.equals(item)) {
                items.remove(item);
                break;
            }
        }
    }

    public void addItem(Item item){
        items.add(item);
        ItemsHashMap.put(item,item.getgill());
    }

}
