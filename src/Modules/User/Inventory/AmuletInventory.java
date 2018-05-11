package Modules.User.Inventory;

import Modules.ItemAndAmulet.Amulet;
import Modules.ItemAndAmulet.Item;
import Modules.Warrior.BackPack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AmuletInventory {
    private BackPack backPack;
    private HashMap<String, Amulet> amuletMap;
    private HashMap<String, Integer> numberOfAmulet;
    private ArrayList<Amulet> amulets;

    public AmuletInventory(BackPack backPack) {
        this.backPack = backPack;
        amuletMap = new HashMap<>();
        amulets = new ArrayList<>();
//        numberOfAmulet = new HashMap<>();
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
        if (!amulets.contains(amulet)) {
            this.amulets.add(amulet);
            this.amuletMap.put(amulet.getName(), amulet);
        }
    }

    public boolean hasAmulet(String amuletName){
        return amuletMap.containsKey(amuletName);
    }

    public void equip(String name){
        backPack.add(amuletMap.get(name));
    }

    public void disequip(String name){
        backPack.remove();
    }

    public void remove(String name){
//        numberOfAmulet.replace(amuletMap.get(name).getName(), numberOfAmulet.get(amuletMap.get(name).getName()) + 1);
        amulets.remove(amuletMap.get(name));
        amuletMap.remove(name);
    }

    public HashMap<String, Integer> getNumberOfAmulet() {
        return numberOfAmulet;
    }
}
