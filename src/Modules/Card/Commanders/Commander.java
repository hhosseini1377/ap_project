package Modules.Card.Commanders;

import Modules.Card.Monsters.Monster;

public class Commander extends Monster{

    public Commander(){
        name = "Arslan";
        gillCost = 200000;
        HP = 10000;
        initialHP = HP;
        AP = 1000;
        initialAP = AP;
        manaPoint = 12;
        isNimble = false;
        offenseType = true;
        isCommander = true;
    }
}
