package Modules.User;

import Modules.BattleGround.Deck;
import Modules.User.Inventory.AmuletInventory;
import Modules.User.Inventory.CardInventory;
import Modules.User.Inventory.ItemInventory;
import Modules.Warrior.BackPack;

import java.io.Serializable;

public class User implements Serializable {
    private final CardInventory cardInventory;
    private final ItemInventory itemInventory;
    private final AmuletInventory amuletInventory;
    private int gills;
    private int level;
    private String name;
    private Deck deck;
    private BackPack backPack;

    public void startViews(){
        deck.startViews();
        backPack.startViews();
    }

    public User(CardInventory cardInventory, ItemInventory itemInventory, AmuletInventory amuletInventory, Deck deck, int gills, int level, String name,BackPack backPack) {
        this.backPack = backPack;
        this.deck = deck;
        this.cardInventory = cardInventory;
        this.gills = gills;
        this.level = level;
        this.name = name;
        this.itemInventory = itemInventory;
        this.amuletInventory = amuletInventory;
    }

    public void setBackPack (BackPack backPack) {
        this.backPack = backPack;
    }

    public ItemInventory getItemInventory() {
        return itemInventory;
    }

    public AmuletInventory getAmuletInventory() {
        return amuletInventory;
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

    public BackPack getBackPack() {
        return backPack;
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
