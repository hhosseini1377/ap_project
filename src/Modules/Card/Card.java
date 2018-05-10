package Modules.Card;

import com.sun.org.apache.bcel.internal.classfile.Code;

public class Card {
    protected String name;
    protected int gillCost;
    protected int manaPoint;

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

    public int getManaPoint() {
        return manaPoint;
    }

    public void setManaPoint(int manaPoint) {
        this.manaPoint = manaPoint;
    }

    public String toString(){
        return name;
    }

    public String[] detail(){
        String[] details = {""};
        return details;
    }

    private String spellDetail(){
        return "nothing special";
    }

    private String willDetail(){
        return "nothing special";
    }

    private String battleCryDetail(){
        return "nothing special";
    }

    public boolean equals(Card other){
        return other.getName().equals(this.getName());
    }

    public int hashCode(){
        return super.hashCode();
    }
}
