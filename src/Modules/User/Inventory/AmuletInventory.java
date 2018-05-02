package Modules.User.Inventory;

import Modules.ItemAndAmulet.Amulet;
import Modules.ItemAndAmulet.Item;
import Modules.Warrior.BackPack;

import java.util.ArrayList;
import java.util.HashMap;

public class AmuletInventory {
    private BackPack backPack;
    private HashMap<String, Amulet> amuletMap;
    private ArrayList<Amulet> amulets;

    public AmuletInventory(BackPack backPack) {
        this.backPack = backPack;
        amuletMap = new HashMap<>();
        amulets = new ArrayList<>();
    }

    public ArrayList<Amulet> getAmulets() {
        return amulets;
    }

    public BackPack getBackPack() {
        return backPack;
    }

    public void setBackPack(BackPack backPack) {
        this.backPack = backPack;
    }

    public void add(Amulet amulet){
        this.amulets.add(amulet);
        this.amuletMap.put(amulet.getName(), amulet);
    }


    public void equip(String name){
        backPack.add(amuletMap.get(name));
    }

    public void disequip(String name){
        backPack.remove(amuletMap.get(name));
    }

    public void remove(String name){
        backPack.remove(amuletMap.get(name));
        amuletMap.remove(name);
    }
    
}
