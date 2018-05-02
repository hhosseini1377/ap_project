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

    public ItemInventory(BackPack backPack) {
        this.backPack = backPack;
        items = new ArrayList<>();
        itemMap = new HashMap<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public BackPack getBackPack() {
        return backPack;
    }

    public void setBackPack(BackPack backPack) {
        this.backPack = backPack;
    }


    public void add(Item item){
        this.items.add(item);
        this.itemMap.put(item.getName(), item);
    }

    public void equip(String name){
        backPack.add(itemMap.get(name));
    }

    public void disequip(String name){
        backPack.remove(itemMap.get(name));
    }

    public void remove(String name){
        backPack.remove(itemMap.get(name));
        itemMap.remove(name);
    }
}
