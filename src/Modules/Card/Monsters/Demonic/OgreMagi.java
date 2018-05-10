package Modules.Card.Monsters.Demonic;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;

import java.util.ArrayList;

public class OgreMagi extends SpellCaster{
    private String spellName = "Enrage";

    public OgreMagi(){
        name = "Ogre Magi";
        HP = 1500;
        initialHP = 1500;
        AP = 800;
        initialAP = 800;
        manaPoint = 5;
        gillCost = manaPoint * 500;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.DEMONIC;
        isNimble = false;
        offenseType = true;
    }

    public String getSpellName() {
        return spellName;
    }

    public void castSpell(ArrayList<Monster> enemyCards, ArrayList<Monster> friendlyCards){
        enemyCards.get((int)(Math.random() * enemyCards.size())).increaseAP(400);
    }
}
