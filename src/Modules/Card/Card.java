package Modules.Card;

import Modules.Warrior.Warrior;

public class Card implements Cloneable{
    protected String name;
    protected int gillCost;
    protected int manaPoint;
    protected Warrior enemy;
    protected Warrior friend;

    public Warrior getEnemy() {
        return enemy;
    }

    public void setEnemy(Warrior enemy) {
        this.enemy = enemy;
    }

    public Warrior getFriend() {
        return friend;
    }

    public void setFriend(Warrior friend) {
        this.friend = friend;
    }

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
        return new String[]{""};
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
        return other.hashCode() == this.hashCode();
    }

    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }
}
