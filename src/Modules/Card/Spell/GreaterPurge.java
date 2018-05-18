package Modules.Card.Spell;

import Modules.Warrior.Warrior;

public class GreaterPurge extends Spell{
    public GreaterPurge(){
    name = "Greater Purge";
    manaPoint = 7;
    gillCost = 700 * manaPoint;
    spellDetail = "Remove all spell catds on field from both sides and move them to hand";
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
            for (int i = 0; i < enemy.getSpellField().getSpellCards().size(); i++) {
                enemy.getHand().add(enemy.getSpellField().getSpellCards().get(i));
                enemy.getSpellField().getSpellCards().remove(enemy.getSpellField().getSpellCards().get(i));
            }
            for (int i = 0; i < friend.getSpellField().getSpellCards().size(); i++) {
                friend.getHand().add(friend.getSpellField().getSpellCards().get(i));
                friend.getSpellField().getSpellCards().remove(friend.getSpellField().getSpellCards().get(i));
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
