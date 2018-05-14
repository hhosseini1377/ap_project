package Modules.Card.Spell;

import Modules.Warrior.Warrior;

public class MeteorShower extends Spell {
    public MeteorShower(){
        name = "Meteor Shower";
        manaPoint = 8;
        gillCost = 700 * manaPoint;
        spellDetail = "Deal 800 damage to a random enemy monster card on field or player";
        spellType = SpellType.CONTINUOUS;
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
