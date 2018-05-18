package Modules.Card.Spell;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Warrior.Warrior;

import java.util.Scanner;

public class HealingWard extends Spell {
    public HealingWard(){
        name = "Healing Ward";
        manaPoint = 5;
        gillCost = 700 * manaPoint;
        spellDetail = "Increase all friendly monster cards' HP by 200";
        spellType = SpellType.CONTINUOUS;
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
                friend.getMonsterField().getMonsterCards().get(i).increaseHP(200);
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
