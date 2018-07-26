package Modules.Card.Commanders;

import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterTribe;

public class DaiMao extends Commander{
    public DaiMao(){
        name = "Satan";
        HP = 10000;
        initialHP = HP;
        AP = 2000;
        initialAP = AP;
        manaPoint = 12;
        isNimble = false;
        offenseType = true;
        isCommander = true;
        monsterTribe = MonsterTribe.DEMONIC;
    }
}
