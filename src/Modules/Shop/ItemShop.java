package Modules.Shop;

import Modules.ItemAndAmulet.Item;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemShop {
    private ArrayList<Item> items;
    private HashMap<String, Item> itemsHashMap;

    public ArrayList<Item> getItems() {
        return items;
    }

    public ItemShop() {
        itemsHashMap = new HashMap<>();
        items = new ArrayList<>();
    }

//    public void removeItem(String itemName){
//
//    }

    public void addItem(Item item){
//        items.add(item);
        if (!itemsHashMap.containsKey(item.getName())) {
            itemsHashMap.put(item.getName(), item);
            items.add(item);
        }
    }

    public int gillCost(String itemName){
        return itemsHashMap.get(itemName).getGillCost();
    } // not necessary method

    public Item getItem(String name){
        return itemsHashMap.get(name);
    }
}
