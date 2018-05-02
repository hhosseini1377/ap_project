package Modules.BattleGround;

import Modules.Card.Card;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    private int maxNumber = 30;
    private int minNumber = 25;
    private ArrayList<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
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
        if (this.hasPlace())
            cards.add(card);
    }

    public void remove(Card card){
        cards.remove(card);
    }

    public Card takeCard(){
        int random = (int)(Math.random() * (cards.size() - 1));
        Card card = cards.get(random);
        cards.remove(random);
        return card;
    }
}
