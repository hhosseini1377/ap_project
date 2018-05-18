package Modules.Card.Spell;

import Modules.Warrior.Warrior;

public class StrategicRetreat extends Spell {
    public StrategicRetreat(){
        name = "Strategic Retreat";
        manaPoint = 6;
        gillCost = 700 * manaPoint;
        spellDetail = "Select and move a" +
                " monster card from field to hand and draw one card from deck";
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
