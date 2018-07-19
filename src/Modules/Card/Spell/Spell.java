package Modules.Card.Spell;

import Modules.Card.Card;
import Modules.Warrior.Warrior;

public class Spell extends Card {
    protected boolean canCast = true;
    protected SpellType spellType;
    protected String spellDetail;

    public boolean isCanCast () {
        return canCast;
    }

    public SpellType getSpellType () {
        return spellType;
    }

    public String spellDetail(){
        return spellDetail;
    }

    public void castSpell(){
    }

    public void castSpell(Card card){
    }

    public void reverseSpell(){

    }

    @Override
    public String detail () {
        return "  MP cost: " + this.manaPoint +
                "\n  Card Type: " + this.spellType;
    }

    public String toString(){
        return "Name: " + this.name + "\nMP cost: " + this.manaPoint +
                "\nCard Type: " + this.spellType + "\nCost: " + gillCost + "\nDetails: " + spellDetail;
    }
}
