package Modules.Shop;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;

import java.util.ArrayList;
import java.util.HashMap;

public class CardShop {
    private ArrayList<Card> availableCards;
    private HashMap<String,Card> CardsHashMap;

    public ArrayList<Card> getCards() {
        return availableCards;
    }

    public void AddCard(Card card){
        availableCards.add(card);
        CardsHashMap.put(card.getName(),card);
    }

    public void RemoveCard(String CardName){
        availableCards.remove(CardsHashMap.get(CardName));
        CardsHashMap.remove(CardName);
    }

    public int GetGillCost(String CardName){
        return CardsHashMap.get(CardName).getGillCost();
    }

    public Card GetCard(String CardName){
        return CardsHashMap.get(CardName);
    }
}
