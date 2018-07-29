package Modules.User.Inventory;

import Modules.BattleGround.Deck;
import Modules.Card.Card;
import View.InventoryView.CardInventoryView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class CardInventory{
    private transient CardInventoryView view;
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

    public void start() throws IOException {
        view.viewInventory();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public Card getCard(String name){
        try {
            return cardMap.get(name);
        }catch (NullPointerException e){
        System.out.println("there is no such card named " + name + " in inventory...");
        }
        return null;
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
        else {
            numberOfCards.put(card.getName(), 1);
            this.cards.add(card);
            this.cardMap.put(card.getName(), card);
        }
    }

    public int getNumberOfCards(Card card){
        if (numberOfCards.containsKey(card.getName()))
            return numberOfCards.get(card.getName());
        return 0;
    }

    public HashMap<String, Integer> getNumberOfCards() {
        return numberOfCards;
    }

    public int getNumberOfCards(String cardName){
        if (numberOfCards.containsKey(cardName))
            return numberOfCards.get(cardName);
        return 0;
    }

    public void equip(String name){
        deck.add(cardMap.get(name), 1);
    }

    public void disequip(String name){
        deck.remove(cardMap.get(name));
    }

    public void remove(String name){
        if (numberOfCards.get(name) == 1){
            cards.remove(cardMap.get(name));
            cardMap.remove(name);
            numberOfCards.remove(name);
        }else
            numberOfCards.replace(cardMap.get(name).getName(), numberOfCards.get(cardMap.get(name).getName()) - 1);
    }
}
