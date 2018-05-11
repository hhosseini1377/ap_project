package Modules.User.Inventory;

import Modules.ItemAndAmulet.Amulet;
import Modules.ItemAndAmulet.Item;
import Modules.Warrior.BackPack;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemInventory {
    private ArrayList<Item> items;
    private BackPack backPack;
    private HashMap<String, Item> itemMap;
    private HashMap<String, Integer> numberOfItem;

    public ItemInventory(BackPack backPack) {
        this.backPack = backPack;
        items = new ArrayList<>();
        itemMap = new HashMap<>();
        numberOfItem = new HashMap<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Item getItem(String itemName){
        return itemMap.get(itemName);
    }

    public BackPack getBackPack() {
        return backPack;
    }

    public void setBackPack(BackPack backPack) {
        this.backPack = backPack;
    }


    public void add(Item item){
        if (numberOfItem.containsKey(item.getName()))
            numberOfItem.replace(item.getName(), numberOfItem.get(item.getName()) + 1);
        else
            numberOfItem.put(item.getName(), 1);
        this.items.add(item);
        this.itemMap.put(item.getName(), item);
    }

    public int getNumberOfItem(Item item){
        if (numberOfItem.containsKey(item.getName()))
            return numberOfItem.get(item.getName());
        return 0;
    }

    public int getNumberOfItem(String itemName){
        if(numberOfItem.containsKey(itemName))
            return numberOfItem.get(itemName);
        return 0;
    }

    public HashMap<String, Integer> getNumberOfItem() {
        return numberOfItem;
    }

    public void equip(String name){
        backPack.add(itemMap.get(name), 1);
    }

    public void disequip(String name){
        backPack.remove(itemMap.get(name));
    }

    public void remove(String name){
        numberOfItem.replace(itemMap.get(name).getName(), numberOfItem.get(itemMap.get(name).getName()) + 1);
        itemMap.remove(name);
    }
}
