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
    public void castSpell(){}
}
