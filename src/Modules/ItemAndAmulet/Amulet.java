package Modules.ItemAndAmulet;

import Modules.Warrior.Warrior;

public abstract class Amulet implements Cloneable {
    protected int gillCost;
    protected String name;

    public int getGillCost() {
        return gillCost;
    }

    public void setGillCost(int gillCost) {
        this.gillCost = gillCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean equals(Amulet other){
        return other.getName().equals(this.getName());
    }

    public String detail(){
        return "nothing special";
    }

    public void castSpell(Warrior lord){

    }

    public int hashCode(){
        return super.hashCode();
    }

    public String toString(){
        return "Name: " + name + "\ndetail: " + detail();
    }

    @Override
    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }
}
