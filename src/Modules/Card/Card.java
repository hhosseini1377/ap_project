package Modules.Card;

public class Card {
    protected String name;
    protected int manaCost;
    protected int gillCost;

    public Card() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public void setGillCost(int gillCost) {
        this.gillCost = gillCost;
    }

    public String getName() {
        return name;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getGillCost() {
        return gillCost;
    }
}
