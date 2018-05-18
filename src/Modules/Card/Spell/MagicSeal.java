package Modules.Card.Spell;

import Modules.Warrior.Warrior;

public class MagicSeal extends Spell {
    public MagicSeal(){
        name = "Magic Seal";
        manaPoint = 9;
        gillCost = 700 * manaPoint;
        spellDetail = "Remove all enemy spell cards from field and move them to graveyard";
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
            for (int i = 0; i < enemy.getSpellField().getSpellCards().size(); i++) {
                enemy.getGraveYard().add(enemy.getSpellField().getSpellCards().get(i));
                enemy.getSpellField().getSpellCards().remove(enemy.getSpellField().getSpellCards().get(i));
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
