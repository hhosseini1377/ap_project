package Modules.BattleGround;

import Modules.Card.Card;
import View.BattleGroundView.HandView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class Hand implements Serializable{
	private boolean isEnemy = false;
	private transient HandView handView = new HandView();
	private ArrayList<Card> cards = new ArrayList<>();
	private HashMap<String,Integer> numberOfCards = new HashMap<>();
	private HashMap<String, Card> cardHashMap = new HashMap<>();

	public void startViews(){
		handView = new HandView();
		for (Card card:cards){
			card.startViews();
		}
	}

	public void add(String cardName){
		add(cardHashMap.get(cardName));
	}

	public void setEnemy (boolean enemy) {
		isEnemy = enemy;
	}

	public void remove(Card card){
		cards.remove(card);
		if(numberOfCards.get(card.getName()) == 1) {
			numberOfCards.remove(card.getName());
			cardHashMap.remove(card.getName());
		}
		else
			numberOfCards.remove(card.getName(),numberOfCards.get(card.getName())-1);
		handView.removeFromHand(card, isEnemy);
	}

	public void add(Card card){
		if (card == null)
			return;
		cards.add(card);
		try {
			if (!cardHashMap.containsKey(card.getName()))
				cardHashMap.put(card.getName(), card);
			if (numberOfCards.containsKey(card.getName()))
				numberOfCards.replace(card.getName(), numberOfCards.get(card.getName()) + 1);
			else
				numberOfCards.put(card.getName(), 1);
			handView.addToHand(card, isEnemy);
		}catch (NullPointerException e){
			System.out.println("no available cards");
		}
	}

	public void setHandView(HBox view){
		handView.setHand(view);
	}

	public boolean hasCard(String name){
        return this.cardHashMap.containsKey(name);
    }

	public Card getCard(int index){
		return cards.get(index);
	}

	public Card getCard(String name){
		return cardHashMap.get(name);
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
