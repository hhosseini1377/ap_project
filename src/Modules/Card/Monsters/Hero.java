package Modules.Card.Monsters;

import Modules.Card.Card;

import java.util.ArrayList;

public abstract class Hero extends Monster{
    public void castSpell(ArrayList<Card> cards){

    }
    public abstract void will(ArrayList<Card> cards);
    public abstract void battleCry(ArrayList<Card> cards);
}
