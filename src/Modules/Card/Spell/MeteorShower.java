package Modules.Card.Spell;

import Modules.Warrior.Warrior;

import java.util.Random;

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
    public void castSpell() {
        if (!canCast) {
            System.out.println("this card cannot cast anymore");
        } else {
            Random ran = new Random();
            int cardToDamage = ran.nextInt() % (enemy.getMonsterField().getMonsterCards().size() + 1);
            if (cardToDamage == 0) {
                enemy.getCommander().decreaseHP(800);
            } else {
                enemy.getMonsterField().getMonsterCards().get(cardToDamage - 1).decreaseHP(800);
            }
            System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
        }
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
