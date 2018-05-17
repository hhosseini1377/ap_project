package Modules.Shop;

import Modules.Card.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class CardShop {
    private ArrayList<Card> availableCards = new ArrayList<>();
    private HashMap<String,Card> cardsHashMap = new HashMap<>();
    private HashMap<String, Integer> numberOfCards = new HashMap<>();

    public int getNumberOfCard(String name) {
        return numberOfCards.get(name);
    }

    public ArrayList<Card> getCards() {
        return availableCards;
    }

    public void addCard(Card card){
        availableCards.add(card);
        try {
            cardsHashMap.put(card.getName(), card);
        }catch (NullPointerException e){
            System.out.println("card is null");
        }
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
