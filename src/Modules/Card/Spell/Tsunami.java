package Modules.Card.Spell;

import Modules.Warrior.Warrior;

public class Tsunami extends Spell {
    public Tsunami(){
        name = "Tsunami";
        manaPoint = 6;
        gillCost = 700 * manaPoint;
        spellDetail = "Deal 500 damage to all" +
                " non-Atlantian monster cards on both sides of field";
        spellType = SpellType.INSTANT;
    }

    public boolean canCast(){
        return canCast;
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend) {
        //TODO
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
