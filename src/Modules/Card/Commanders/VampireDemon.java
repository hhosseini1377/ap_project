package Modules.Card.Commanders;

import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterTribe;

public class VampireDemon extends Commander{
    public VampireDemon(){
        name = "Damon";
        HP = 12000;
        initialHP = HP;
        AP = 1200;
        initialAP = AP;
        manaPoint = 12;
        isNimble = false;
        offenseType = true;
        isCommander = true;
        monsterTribe = MonsterTribe.DEMONIC;
    }
}
