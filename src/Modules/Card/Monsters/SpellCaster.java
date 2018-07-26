package Modules.Card.Monsters;

import Modules.Card.Card;
import Modules.Warrior.Warrior;

public abstract class SpellCaster extends Monster {
    protected boolean canCast = true;
    protected String spellName;

    public boolean CanCast() {
        return canCast;
    }

    public void setCanCast(boolean canCast) {
        this.canCast = canCast;
    }

    public void castSpell(Card card){}

    public void castSpell(Warrior enemy, Warrior friend){
    }

    protected String spellDetail (){
        return "spell details";
    }

    @Override
    public String toString() {
        return " Name: " + name + "\n HP: " + HP + "\n AP: " + AP +
                "\n MP cost: " + manaPoint +
                "\n Is Nimble: " + isNimble + "\n Is Defensive: " + !offenseType +
                "\n Monster Kind: " + monsterKind + "\n Tribe: " + monsterTribe +
                "\n Spell detail: " + spellName + ", " + spellDetail();
    }
}
