package Modules.Card.Spell;

import Modules.Card.Card;
import Modules.Warrior.Warrior;

public class BloodFeast extends Spell{
    public BloodFeast(){
        name = "Blood Feast";
        manaPoint = 4;
        gillCost = manaPoint * 700;
        spellDetail = "Deal 500 damage to enemy player and heal " +
                "your player for 500 HP";
        spellType = SpellType.INSTANT;
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend) {
        enemy.getCommander().decreaseHP(500);
        friend.getCommander().increaseHP(500);
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }
}
