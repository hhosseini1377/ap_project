package Modules.Card.Spell;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Warrior.Warrior;

import java.util.Scanner;

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
        //TODO
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
