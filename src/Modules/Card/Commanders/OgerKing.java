package Modules.Card.Commanders;

import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterTribe;

public class OgerKing extends Commander{
    public OgerKing(){
        name = "Shagholam";
        HP = 4000;
        initialHP = HP;
        AP = 800;
        initialAP = AP;
        manaPoint = 12;
        isNimble = false;
        offenseType = true;
        isCommander = true;
        monsterTribe = MonsterTribe.DEMONIC;
    }
}
