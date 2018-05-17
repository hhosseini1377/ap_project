package Modules.Card.Spell;

import Modules.Card.Monsters.Monster;
import Modules.Warrior.Warrior;

public class ArcaneBolt extends Spell {
    public ArcaneBolt(){
        name = "Arcane Bolt";
        manaPoint = 5;
        gillCost = 700 * manaPoint;
        spellDetail = "Deal 500 damage to enemy player and select and move an" +
                "enemy spell card from field to graveyard";
        spellType = SpellType.INSTANT;
    }

    public boolean canCast(){
        return canCast;
    }

    private void cast(Monster monster){
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
