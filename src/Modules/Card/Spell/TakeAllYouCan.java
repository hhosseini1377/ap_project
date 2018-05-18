package Modules.Card.Spell;

import Modules.Card.Monsters.MonsterKind;
import Modules.Warrior.Warrior;

public class TakeAllYouCan extends Spell {
    public TakeAllYouCan(){
        name = "Take All You Can";
        manaPoint = 7;
        gillCost = 700 * manaPoint;
        spellDetail = "Increase all friendly normal monster cards' HP and AP by 400";
        spellType = SpellType.AURA;
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
                if (friend.getMonsterField().getMonsterCards().get(i).getMonsterKind().equals(MonsterKind.NORMAL)) {
                    friend.getMonsterField().getMonsterCards().get(i).increaseAP(400);
                    friend.getMonsterField().getMonsterCards().get(i).increaseHP(400);
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
