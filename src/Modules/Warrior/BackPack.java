package Modules.Warrior;

import Modules.ItemAndAmulet.Amulet;
import Modules.ItemAndAmulet.Item;

import java.util.ArrayList;

public class BackPack {
    private ArrayList<Item> items;
    private ArrayList<Amulet> amulets;

    public BackPack() {
        items = new ArrayList<>();
        amulets = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Amulet> getAmulets() {
        return amulets;
    }

    public void add(Item item){
        items.add(item);
    }

    public void add(Amulet amulet){
        amulets.add(amulet);
    }

    public void remove(Item item){
        items.remove(item);
    }

    public void remove(Amulet amulet){
        amulets.remove(amulet);
    }

    public void useAmulet(){

    }

    public void useItem(){

    }
}
