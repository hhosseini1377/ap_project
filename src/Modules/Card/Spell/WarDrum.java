package Modules.Card.Spell;

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
    public void castSpell() {
        if(!canCast){
            System.out.println("this card cannot cast any more");
        }
        else {
            for (int i = 0; i < friend.getMonsterField().getMonsterCards().size(); i++) {
                friend.getMonsterField().getMonsterCards().get(i).increaseAP(300);
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
