package Modules.Card.Spell;

import Modules.Warrior.Warrior;

public class MagicSeal extends Spell {
    public MagicSeal(){
        name = "Magic Seal";
        manaPoint = 9;
        gillCost = 700 * manaPoint;
        spellDetail = "Remove all enemy spell cards from field and move them to graveyard";
        spellType = SpellType.CONTINUOUS;
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
