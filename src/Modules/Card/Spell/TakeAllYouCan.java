package Modules.Card.Spell;

import Modules.Warrior.Warrior;

public class TakeAllYouCan extends Spell {
    public TakeAllYouCan(){
        name = "Take All You Can";
        manaPoint = 7;
        gillCost = 700 * manaPoint;
        spellDetail = "Increase all friendly normal monster cards' HP and AP by 400";
        spellType = SpellType.AURA;
    }

    public boolean canCast(){
        return canCast;
    }

    @Override
    public void castSpell() {
        //TODO
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
