package Modules.Card.Commanders;

import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterTribe;

public class GoblinOyabun extends Commander{
    public GoblinOyabun(){
        name = "Oyabun";
        HP = 7000;
        initialHP = HP;
        AP = 1000;
        initialAP = AP;
        manaPoint = 12;
        isNimble = false;
        offenseType = true;
        isCommander = true;
        monsterTribe = MonsterTribe.DEMONIC;
    }
}
