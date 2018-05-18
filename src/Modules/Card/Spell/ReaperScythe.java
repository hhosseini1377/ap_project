package Modules.Card.Spell;

import Modules.Warrior.Warrior;

public class ReaperScythe extends Spell {
    public ReaperScythe(){
        name = "Reaper's Scythe";
        manaPoint = 4;
        gillCost = 700 * manaPoint;
        spellDetail = "Send an enemy monster or spell card from field to graveyard";
        spellType = SpellType.INSTANT;
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
