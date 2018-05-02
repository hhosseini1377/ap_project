package Modules.ItemAndAmulet;

public abstract class Amulet {
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

    public abstract void castSpell();
}
