package Modules.BattleGround;

import Modules.Card.Card;

import java.util.ArrayList;
import java.util.HashMap;

public class Hand {
    private ArrayList<Card> cards;
    private HashMap<String,Integer> numberOfCards;

    public void remove(Card card){
        cards.remove(card);
        if(numberOfCards.get(card.getName()) == 1)
            numberOfCards.remove(card.getName());
        else
            numberOfCards.remove(card.getName(),numberOfCards.get(card.getName())-1);
    }

    public void add(Card card){
        cards.add(card);
        numberOfCards.replace(card.getName(),numberOfCards.get(card.getName())+1);
    }

    public int getNumberOfCard(String CardName){
        return numberOfCards.get(CardName);
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards){
        this.cards = cards;
    }

}
