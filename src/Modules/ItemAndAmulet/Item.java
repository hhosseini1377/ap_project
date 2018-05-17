package Modules.ItemAndAmulet;

import Modules.Warrior.Warrior;

public abstract class Item {
    protected String name;
    protected int gillCost;
    protected boolean isUsed = false;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGillCost() {
        return gillCost;
    }

    public void setGillCost(int gillCost) {
        this.gillCost = gillCost;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean used) {
        isUsed = used;
    }

    public abstract void castSpell(Warrior player);

    public String toString(){
        return "Name: " + name + "\ndetail: " + spelldetail();
    }

    public boolean equals(Item other){
        return other.getName().equals(this.getName());
    }

    public String spelldetail(){
        return "nothing special";
    }

    public int hashCode(){
        return super.hashCode();
    }
}
