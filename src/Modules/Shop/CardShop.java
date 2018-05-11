package Modules.Shop;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Spell.Spell;

import java.util.ArrayList;
import java.util.HashMap;

public class CardShop {
    private ArrayList<Card> availableCards;
    private HashMap<String,Card> cardsHashMap;
    private HashMap<String, Integer> numberOfCards;

    public CardShop() {
        availableCards = new ArrayList<>();
        cardsHashMap = new HashMap<>();
    }

    public int getNumberOfCard(String name) {
        return numberOfCards.get(name);
    }

    public ArrayList<Card> getCards() {
        return availableCards;
    }

    public void addCard(Card card){
        availableCards.add(card);
        cardsHashMap.put(card.getName(),card);
    }

    public void removeCard(String CardName){
        availableCards.remove(cardsHashMap.get(CardName));
        cardsHashMap.remove(CardName);
    }

    public int getGillCost(String CardName){
        return cardsHashMap.get(CardName).getGillCost();
    } // not necessary method

    public Card getCard(String CardName){
        return cardsHashMap.get(CardName);
    }
}
