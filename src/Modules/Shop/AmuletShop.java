package Modules.Shop;

import Modules.ItemAndAmulet.Amulet;

import java.util.ArrayList;
import java.util.HashMap;

public class AmuletShop {
    private ArrayList<Amulet> amulets;
    private HashMap<String, Amulet> amuletHashMap;

    public AmuletShop() {
        amuletHashMap = new HashMap<>();
        amulets = new ArrayList<>();
    }

    public ArrayList<Amulet> getAmulets() {
        return amulets;
    }

    public void addAmulet(Amulet amulet){
        if (amulets.contains(amulet)){
            amulets.add(amulet);
            amuletHashMap.put(amulet.getName(), amulet);
        }
    }

    public Amulet getAmulet(String AmuletName){
        return amuletHashMap.get(AmuletName);
    }

    public void removeAmulet(String name){
        amulets.remove(amuletHashMap.get(name));
        amuletHashMap.remove(name);
    }
}
