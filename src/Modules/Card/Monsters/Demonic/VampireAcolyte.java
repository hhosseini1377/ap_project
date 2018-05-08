package Modules.Card.Monsters.Demonic;

import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;

import java.util.ArrayList;

public class VampireAcolyte extends SpellCaster{
    private String spellName = "Black Wave";

    public VampireAcolyte(){
        name = "VampireAcolyte";
        HP = 1500;
        initialHP = 1500;
        AP = 1500;
        initialAP = 1500;
        manaPoint = 7;
        gillCost = manaPoint * 500 ;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.DEMONIC;
        isNimble = true;
        offenseType = true;
    }

    public String getSpellName() {
        return spellName;
    }

    public void castSpell(ArrayList<Monster> enemyCards, ArrayList<Monster> friendlyCards) {
        enemyCards.forEach(monster -> monster.decreaseHP(300));
        friendlyCards.forEach(monster -> monster.increaseHP(300));
    }
}
