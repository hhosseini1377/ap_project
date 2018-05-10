package Modules.Card.Monsters.Demonic;

import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;

import java.util.ArrayList;

public class UndeadMage extends SpellCaster{
    private String spellName = "Curse";

    public UndeadMage(){
        name = "Undead Mage";
        HP = 800;
        initialHP = 800;
        AP = 1000;
        initialAP = 1000;
        manaPoint = 6;
        gillCost = manaPoint * 500 ;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.DEMONIC;
        isNimble = false;
        offenseType = true;
    }

    public String getSpellName() {
        return spellName;
    }

    @Override
    public void castSpell(ArrayList<Monster> cards) {
        cards.get((int)(Math.random() * cards.size())).decreaseAP(500);
    }
}
