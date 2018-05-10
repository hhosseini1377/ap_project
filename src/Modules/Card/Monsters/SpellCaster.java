package Modules.Card.Monsters;

import Modules.Card.Card;

import java.util.ArrayList;

public abstract class SpellCaster extends Monster {
    protected boolean canCast = true;

    public boolean CanCast() {
        return canCast;
    }

    public void setCanCast(boolean canCast) {
        this.canCast = canCast;
    }

    public void castSpell(ArrayList<Monster> enemyCards, ArrayList<Monster> friendlyCards){

    }
}
