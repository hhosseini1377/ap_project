package Modules.Shop;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Spell.Spell;

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
        if (numberOfCards.containsKey(card.getName())) {
            numberOfCards.replace(card.getName(), numberOfCards.get(card.getName()) + 1);
        }else{
            cardsHashMap.put(card.getName(), card);
            numberOfCards.put(card.getName(), 1);
        }
        availableCards.add(card);
    }

    public void removeCard(String CardName){
        if(numberOfCards.get(CardName) == 1) {
            cardsHashMap.remove(CardName);
            numberOfCards.remove(CardName);
        }
        else{
            numberOfCards.replace(CardName,numberOfCards.get(CardName)-1);
        }
        availableCards.remove(cardsHashMap.get(CardName));
    }

    public int getGillCost(String CardName){
        return cardsHashMap.get(CardName).getGillCost();
    } // not necessary method

    public Card getCard(String CardName){
        return cardsHashMap.get(CardName);
    }
}
