package Modules.BattleGround;

import Modules.Card.Card;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Deck {
    private int maxNumber = 30;
    private int minNumber = 25;
    private ArrayList<Card> cards;
    private HashMap<String, Integer> numberOfCards;

    public Deck() {
        cards = new ArrayList<>();
        numberOfCards = new HashMap<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean hasPlace(){
        return (cards.size() < 30);
    }

    public boolean isAcceptable(){
        return (cards.size() >= 25 && cards.size() <= 30);
    }

    public void add(Card card){
        if (this.hasPlace()) {
            if (numberOfCards.containsKey(card.getName()))
                numberOfCards.replace(card.getName(), numberOfCards.get(card.getName()) + 1);
            else
                numberOfCards.put(card.getName(), 1);
            cards.add(card);
        }else {
            System.out.println("deck is fulled");
        }
    }

    public int getNumberOfCards(Card card){
        if (numberOfCards.containsKey(card.getName()))
            return numberOfCards.get(card.getName());
        return 0;
    }

    public void remove(Card card){
        cards.remove(card);
        numberOfCards.replace(card.getName(), numberOfCards.get(card.getName()) - 1);
    }

    public Card takeCard(){
        int random = (int)(Math.random() * (cards.size() - 1));
        Card card = cards.get(random);
        cards.remove(random);
        numberOfCards.replace(card.getName(), numberOfCards.get(card.getName()) - 1);
        return card;
    }
}
