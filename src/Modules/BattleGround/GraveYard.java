package Modules.BattleGround;

import Modules.Card.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class GraveYard {
    private ArrayList<Card> destroyedCards = new ArrayList<>();
    private HashMap<String, Card> cardMap = new HashMap<>();
    private HashMap<String, Integer> numberOfCards = new HashMap<>();

    public ArrayList<Card> getDestroyedCards() {
        return destroyedCards;
    }

    public HashMap<String, Card> getCardMap() {
        return cardMap;
    }

    public Card getCard(String name){
        try {
            return cardMap.get(name);
        }catch (Exception e){
            System.out.println("card not available");
            return null;
        }
    }

    public void add(Card card){
        if (cardMap.containsKey(card.getName())) {
            destroyedCards.add(card);
            cardMap.put(card.getName(), card);
            numberOfCards.replace(card.getName(), numberOfCards.get(card.getName()) + 1);
        }else {
            destroyedCards.add(card);
            numberOfCards.replace(card.getName(), 1);
        }
    }

    public void remove(String name){
        try{
            destroyedCards.remove(cardMap.get(name));
            if (numberOfCards.get(name) == 1){
                numberOfCards.remove(name);
                cardMap.remove(name);
            }
        }catch (Exception e){
            System.out.println("card name not right!\ncheck and try again");
        }
    }
}
