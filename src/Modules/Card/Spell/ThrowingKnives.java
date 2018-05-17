package Modules.Card.Spell;

import Modules.Card.Card;
import Modules.Warrior.Warrior;

public class ThrowingKnives extends Spell{
    public ThrowingKnives(){
        name = "Throwing Knives";
        manaPoint = 3;
        gillCost = 700 * manaPoint;
        spellDetail = "Deal 500 damage to a " +
                "selected enemy monster card on the field or to enemy player";
        spellType = SpellType.INSTANT;
    }

    public boolean canCast(){
        return canCast;
    }

    @Override
    public void castSpell(Card card) {
        //TODO
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
