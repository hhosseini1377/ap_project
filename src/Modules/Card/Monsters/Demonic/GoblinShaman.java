package Modules.Card.Monsters.Demonic;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;

import java.util.ArrayList;

public class GoblinShaman extends SpellCaster{
    private String spellName = "mend";

    public GoblinShaman(){
        this.name = "Goblin Shaman";
        this.initialHP = 1000;
        this.HP = 1000;
        this.initialAP = 700;
        this.AP = 700;
        this.manaPoint = 4;
        gillCost = manaPoint * 500;
        this.monsterKind = MonsterKind.SPELL_CASTER;
        this.isNimble = false;
        this.offenseType = true;
        monsterTribe = MonsterTribe.DEMONIC;
    }

    public void castSpell(ArrayList<Monster> enemyCards, ArrayList<Monster> friendlyCards){
        int randomNumber = (int)(Math.random() * enemyCards.size());
        enemyCards.get(randomNumber).increaseHP(400);
    }

    public String getSpellName() {
        return spellName;
    }
}
