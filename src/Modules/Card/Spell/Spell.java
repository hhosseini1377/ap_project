package Modules.Card.Spell;

import Modules.Card.Card;
import Modules.Warrior.Warrior;

public class Spell extends Card {
    protected boolean canCast = true;
    protected SpellType spellType;
    protected String spellDetail;

    public String spellDetail(){
        return spellDetail;
    }

    public void castSpell(Warrior enemy, Warrior friend){
    }

    public void castSpell(Card card){
    }

    public String toString(){
        return "Name: " + this.name + "\nMP cost: " + this.manaPoint +
                "\nCard Type: " + this.spellType + "\n" + this.spellDetail +
                "Card Story: ";//TODO card story
    }
}
