package Modules.Card.Spell;

import Modules.Card.Monsters.MonsterTribe;
import Modules.Warrior.Warrior;

public class LunarBlessing extends Spell {
    public LunarBlessing(){
    name = "Lunar Blessing";
    manaPoint = 6;
    gillCost = 700 * manaPoint;
    spellDetail = "Increase AP and HP of friendly Elven monster cards by 300";
    spellType = SpellType.AURA;
}

    public boolean canCast(){
        return canCast;
    }

    @Override
    public void castSpell() {
        if(!canCast){
            System.out.println("this card cannot cast anymore");
        }
        else {
            for (int i = 0; i < friend.getMonsterField().getMonsterCards().size(); i++) {
                if (friend.getMonsterField().getMonsterCards().get(i).getMonsterTribe().equals(MonsterTribe.ELVEN)) {
                    friend.getMonsterField().getMonsterCards().get(i).increaseAP(300);
                    friend.getMonsterField().getMonsterCards().get(i).increaseHP(500);
                }
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
