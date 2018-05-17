package Modules.Card.Monsters;

import Modules.Card.Card;
import Modules.Warrior.Warrior;

import java.util.ArrayList;
import java.util.Scanner;

public abstract class SpellCaster extends Monster {
    protected boolean canCast = true;
    private String spellName;

    public boolean CanCast() {
        return canCast;
    }

    public void setCanCast(boolean canCast) {
        this.canCast = canCast;
    }

    public void castSpell(Card card){}

    public void castSpell(Warrior enemy, Warrior friend){
    }

    public String spellDetail(){
        return "spell details";
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nHP: " + HP + "\nAP: " + AP +
                "\nMP cost: " + manaPoint +
                "\nIs Nimble: " + isNimble + "\nIs Defensive: " + !offenseType +
                "\nMonster Kind: " + monsterKind + "\nTribe: " + monsterTribe +
                "\nSpell detail: " + spellName + ", " + spellDetail();
    }
}
