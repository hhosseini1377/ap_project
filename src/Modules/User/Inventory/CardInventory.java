package Modules.User.Inventory;

import Modules.BattleGround.Deck;
import Modules.Card.Card;
import Modules.ItemAndAmulet.Amulet;
import Modules.ItemAndAmulet.Item;
import Modules.Warrior.BackPack;

import java.util.ArrayList;
import java.util.HashMap;

public class CardInventory{
    private ArrayList<Card> cards;
    private Deck deck;
    private HashMap<String, Card> cardMap;
    private HashMap<String, Integer> numberOfCards;

    public CardInventory(Deck deck) {
        this.cards = new ArrayList<>();
        this.cardMap = new HashMap<>();
        this.deck = deck;
        numberOfCards = new HashMap<>();
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
        if (numberOfCards.containsKey(card.getName()))
            numberOfCards.replace(card.getName(), numberOfCards.get(card.getName()) + 1);
        else
            numberOfCards.put(card.getName(), 1);
        this.cards.add(card);
        this.cardMap.put(card.getName(), card);
    }

    public int getNumberOfCards(Card card){
        if (numberOfCards.containsKey(card.getName()))
            return numberOfCards.get(card.getName());
        return 0;
    }
    public void equip(String name){
        deck.add(cardMap.get(name));
    }

    public void disequip(String name){
        deck.remove(cardMap.get(name));
    }

    public void remove(String name){
        cards.remove(cardMap.get(name));
        numberOfCards.replace(cardMap.get(name).getName(), numberOfCards.get(cardMap.get(name).getName()) - 1);
        cardMap.remove(name);
    }
}
