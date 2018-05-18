package Modules.Card.Commanders;

import Modules.Card.Monsters.MonsterTribe;

public class ArslanTheHero extends Commander{
    public ArslanTheHero(){
        name = "Arslan";
        HP = 10000;
        initialHP = HP;
        AP = 1000;
        initialAP = AP;
        manaPoint = 12;
        monsterTribe = MonsterTribe.HUMAN;
    }
}
