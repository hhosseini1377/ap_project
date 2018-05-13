package Modules.Card.Monsters;

import Modules.Card.Card;
import Modules.Warrior.Warrior;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class SpellCaster extends Monster {
    protected boolean canCast = true;

    public boolean CanCast() {
        return canCast;
    }

    public void setCanCast(boolean canCast) {
        this.canCast = canCast;
    }

    protected void cast(Card card){}

    public void castSpell(Warrior enemy, Warrior friend){
    }

    public String spellDetail(){
        return "spell details";
    }
}
