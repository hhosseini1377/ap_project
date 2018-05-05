package Modules.Shop;

import Modules.ItemAndAmulet.Amulet;

import java.util.ArrayList;
import java.util.HashMap;

public class AmuletShop {
    private ArrayList<Amulet> amulets;
    private HashMap<String,Amulet> AmuletsHashMap;

    public void AddAmulet(Amulet amulet){
        amulets.add(amulet);
        AmuletsHashMap.put(amulet.getName(),amulet);
    }

    public void RemoveAmulet(String amuletName){
        amulets.remove(AmuletsHashMap.get(amuletName));
        AmuletsHashMap.remove(amuletName);
    }

    public int GetGillCost(String AmuletName){
        return AmuletsHashMap.get(AmuletName).getGillCost();
    }

    public ArrayList<Amulet> GetAmulets(){
        return amulets;
    }

    public Amulet getAmulet(String AmuletName){
        return AmuletsHashMap.get(AmuletName);
    }



}
