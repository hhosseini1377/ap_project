package Modules.Card.Spell;

import Modules.BattleGround.Fields.MonsterField;
import Modules.Card.Card;
import Modules.Warrior.Warrior;

public class PoisonousCauldron extends Spell{
    public PoisonousCauldron(){
        name = "Poisonous Cauldron";
        manaPoint = 4;
        gillCost = 700 * manaPoint;
        spellDetail = "Deal 100 damage to all enemy monster cards and enemy player";
        spellType = SpellType.CONTINUOUS;
    }

    public boolean canCast(){
        return canCast;
    }


    @Override
    public void castSpell() {
        if (!canCast) {
            System.out.println("this card can not cast any more");
        } else {
            for (int i = 0; i < enemy.getMonsterField().getMonsterCards().size(); i++)
                enemy.getMonsterField().getMonsterCards().get(i).decreaseHP(100);
            enemy.getCommander().decreaseHP(100);
            System.out.println(this.getName() + "has cast:\n" + this.spellDetail);
        }
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
