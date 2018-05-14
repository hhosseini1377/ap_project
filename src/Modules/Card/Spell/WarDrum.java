package Modules.Card.Spell;

import Modules.Card.Card;
import Modules.Warrior.Warrior;

public class WarDrum extends Spell{
    public WarDrum(){
        name = "War Drum";
        manaPoint = 6;
        gillCost = 700 * manaPoint;
        spellDetail = "Increase all friendly monster cards' AP by 300";
        spellType = SpellType.AURA;
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
