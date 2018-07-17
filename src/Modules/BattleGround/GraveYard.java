package Modules.BattleGround;

import Modules.Card.Card;
import View.BattleGroundView.GraveYardView;
import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.HashMap;

public class GraveYard {
    private GraveYardView view = new GraveYardView();
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
            cardMap.put(card.getName(), card);
            numberOfCards.replace(card.getName(), numberOfCards.get(card.getName()) + 1);
        }else {
            numberOfCards.replace(card.getName(), 1);
        }
        destroyedCards.add(card);
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

    public void setGraveyardView(ImageView view){
        this.view.setGraveyardView(view);
    }

    public boolean hasCard(String name){
        return this.cardMap.containsKey(name);
    }
}
