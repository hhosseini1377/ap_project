package Modules.Card.Spell;

import Modules.Card.Monsters.MonsterTribe;
import Modules.Warrior.Warrior;

public class Tsunami extends Spell {
    public Tsunami(){
        name = "Tsunami";
        manaPoint = 6;
        gillCost = 700 * manaPoint;
        spellDetail = "Deal 500 damage to all" +
                " non-Atlantian monster cards on both sides of field";
        spellType = SpellType.INSTANT;
    }

    public boolean canCast(){
        return canCast;
    }

    @Override
    public void castSpell() {
        if(!canCast){
            System.out.println("this card cannot cast any more");
        }
        else {
            for (int i = 0; i < friend.getMonsterField().getMonsterCards().size(); i++) {
                if (!friend.getMonsterField().getMonsterCards().get(i).getMonsterTribe().equals(MonsterTribe.ATLANTIAN))
                    friend.getMonsterField().getMonsterCards().get(i).decreaseHP(500);
            }
            for (int i = 0; i < enemy.getMonsterField().getMonsterCards().size(); i++) {
                if (!enemy.getMonsterField().getMonsterCards().get(i).getMonsterTribe().equals(MonsterTribe.ATLANTIAN))
                    enemy.getMonsterField().getMonsterCards().get(i).decreaseHP(500);
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
