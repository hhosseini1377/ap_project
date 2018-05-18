package Modules.Card.Spell;

import Modules.Warrior.Warrior;

public class LunarBlessing extends Spell {
    public LunarBlessing(){
    name = "Lunar Blessing";
    manaPoint = 6;
    gillCost = 700 * manaPoint;
    spellDetail = "Increase AP and HP of friendly Elven monster cards by 300";
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
