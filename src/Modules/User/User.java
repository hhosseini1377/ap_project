package Modules.User;

import Modules.BattleGround.Deck;
import Modules.User.Inventory.CardInventory;

public class User {
    private CardInventory cardInventory;
    private int gills;
    private int level;
    private String name;
    private Deck deck;

    public User(CardInventory cardInventory, Deck deck, int gills, int level, String name) {
        this.deck = deck;
        this.cardInventory = cardInventory;
        this.gills = gills;
        this.level = level;
        this.name = name;
    }

    public CardInventory getCardInventory() {
        return cardInventory;
    }

    public int getGills() {
        return gills;
    }

    public void setGills(int gills) {
        this.gills = gills;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public boolean canBuy(int price){
        return gills >= price;
    }

    public void buy(int price){
        if (canBuy(price))
            this.gills -= price;
    }

    public void sell(int price){
        this.gills += price/2;
    }
}
