package Modules.BattleGround;

import Modules.Card.Card;
import View.BattleGroundView.DeckView;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class Deck implements Cloneable{
    private DeckView deckView = new DeckView();
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

    public boolean hasPlace(int number){
        return (cards.size() <= 30 - number);
    }

    public boolean isAcceptable(){
        return (cards.size() >= 25 && cards.size() <= 30);
    }

    public void add(Card card, int cardNum){
        if (this.hasPlace(cardNum)) {
            if (numberOfCards.containsKey(card.getName()))
                numberOfCards.replace(card.getName(), numberOfCards.get(card.getName()) + cardNum);
            else {
                numberOfCards.put(card.getName(), cardNum);
            }
            for (int i = 0; i < cardNum; i++)
                cards.add(card);
        }else {
            System.out.println("deck is full");
        }
    }

    public int getNumberOfCards(Card card){
        if (numberOfCards.containsKey(card.getName()))
            return numberOfCards.get(card.getName());
        return 0;
    }

    public int getNumberOfCards(String cardName){
        if (numberOfCards.containsKey(cardName))
            return numberOfCards.get(cardName);
        return 0;
    }

    public void remove(Card card){
        try {
            if (numberOfCards.get(card.getName()) - 1 != 0) {
                numberOfCards.replace(card.getName(), numberOfCards.get(card.getName()) - 1);
            } else {
                numberOfCards.remove(card.getName());
            }
        }catch (NullPointerException e){
            System.out.println("card is not available in hashmap");
        }
        cards.remove(card);
    }

    public Card takeCard(){
        int random = (int)(Math.random() * (cards.size()));
        try {
            Card card = cards.get(random);
            this.remove(card);
            return card;
        }catch (Exception e){
            System.out.println("no more cards on deck");
        }
        return null;
    }

    public void setDeckView(ImageView view){
        deckView.setDeckImage(view);
    }

    public boolean contains(String Name){
        return numberOfCards.containsKey(Name);
    }

    @Override
    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }
}
