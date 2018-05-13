package Modules.Card.Monsters;

import Modules.Card.Card;
import Modules.Warrior.Warrior;

import java.util.ArrayList;

public abstract class Hero extends Monster {
    protected boolean canCast = true;

    public boolean CanCast() {
        return canCast;
    }

    public void setCanCast(boolean canCast) {
        this.canCast = canCast;
    }

    public void castSpell(Warrior enemy, Warrior friend){

    }

    public abstract void will(ArrayList<Card> cards);

    public abstract void battleCry(ArrayList<Card> cards);

    public String spellDetail(){
        return "spell details";
    }

    public String willDetail(){
        return "will detail";
    }

    public String battleCryDetail(){
        return "battle cry detail";
    }
}
