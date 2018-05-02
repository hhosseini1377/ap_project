package Modules.Shop;

import Modules.ItemAndAmulet.Item;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemShop {
    private ArrayList<Item> items;
    private HashMap<String, Item> itemsHashMap;


    public void RemoveItem(String itemName){
        items.remove(itemsHashMap.get(itemName));
        itemsHashMap.remove(itemName);
    }

    public void addItem(Item item){
        items.add(item);
        itemsHashMap.put(item.getName(), item);
    }

    public int gillCost(String itemName){
        return itemsHashMap.get(itemName).getGillCost();
    }
}
