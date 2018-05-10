package Modules.Shop;

import Modules.ItemAndAmulet.Amulet;

import java.util.ArrayList;
import java.util.HashMap;

public class AmuletShop {
    private ArrayList<Amulet> amulets;  //TODO is it really needed?
    private HashMap<String, Amulet> amuletHashMap;

    public AmuletShop() {
        amuletHashMap = new HashMap<>();
        amulets = new ArrayList<>();
    }

    public void addAmulet(Amulet amulet){

    }

    public ArrayList<Amulet> GetAmulets(){
        return amulets;
    }

    public Amulet getAmulet(String AmuletName){
        return amuletHashMap.get(AmuletName);
    }

    public void removeAmulet(String name){
        amulets.remove(amuletHashMap.get(name));
        amuletHashMap.remove(name);
    }
}
