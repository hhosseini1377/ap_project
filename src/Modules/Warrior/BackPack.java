package Modules.Warrior;

import Modules.ItemAndAmulet.Amulet;
import Modules.ItemAndAmulet.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class BackPack {
    private ArrayList<Item> items = new ArrayList<>();
    private Amulet amulet ;
    private HashMap<String, Integer> numberOfItems = new HashMap<>();

    public BackPack() {
        items = new ArrayList<>();
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public Amulet getAmulet() {
        return amulet;
    }

    public void add(Item item, int itemNumber){
        if (numberOfItems.containsKey(item.getName()))
            numberOfItems.replace(item.getName(), numberOfItems.get(item.getName() + itemNumber));
        else {
            numberOfItems.put(item.getName(), itemNumber);
            items.add(item);
        }
    }

    public boolean isAmuletEquipped(){
        if(amulet.equals(null))
            return false;
        else
            return true;
    }

    public void add(Amulet amulet){
        if (this.amulet != null)
            this.amulet = amulet;
        else
            System.out.println("there is already an amulet equiped");
    }

    public void remove(Item item){
        if (numberOfItems.get(item.getName()) - 1 == 0) {
            items.remove(item);
            numberOfItems.remove(item.getName());
        }
        else
            numberOfItems.replace(item.getName(), numberOfItems.get(item.getName()) - 1);
    }

    public void remove(){
        amulet = null;
    }

    public void useAmulet(){

    }

    public void useItem(){

    }

    public String toString(){
        return "";
    }

    public boolean ContainsAmulet(String AmuletName){
        return amulet.getName().equals(AmuletName);
    }

    public boolean ContainsItem(String ItemName){
        return numberOfItems.containsKey(ItemName);
    }

    public int getNumberOfItems(String itemName){
        return numberOfItems.get(itemName);
    }
}


//TODO remove( amulet) -> changed to remove()