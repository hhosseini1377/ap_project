package Modules.User.Inventory;

import Modules.BattleGround.Deck;
import Modules.Card.Card;
import Modules.ItemAndAmulet.Amulet;
import Modules.ItemAndAmulet.Item;
import Modules.Warrior.BackPack;

import java.util.ArrayList;
import java.util.HashMap;

public class CardInventory {
    private ArrayList<Card> cards;
    private Deck deck;
    private HashMap<String, Card> cardMap;

    public CardInventory(Deck deck) {
        this.cards = new ArrayList<>();
        this.cardMap = new HashMap<>();
        this.deck = deck;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }


    public Deck getDeck() {
        return deck;
    }

    public void setDeck(Deck deck) {
        this.deck = deck;
    }

    public void add(Card card){
        this.cards.add(card);
        this.cardMap.put(card.getName(), card);
    }

    public void equip(String name){
        deck.add(cardMap.get(name));
    }

    public void disequip(String name){
        deck.remove(cardMap.get(name));
    }

    public void remove(String name){
        cards.remove(cardMap.get(name));
        cardMap.remove(name);
    }
}
