package Modules.ItemAndAmulet;

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

    public abstract void castSpell();

    public String toString(){
        return name;
    }

    public boolean equals(Item other){
        return other.getName().equals(this.getName());
    }
}
