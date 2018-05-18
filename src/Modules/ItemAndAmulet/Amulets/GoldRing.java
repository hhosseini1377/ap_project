package Modules.ItemAndAmulet.Amulets;

import Modules.ItemAndAmulet.Amulet;
import Modules.Warrior.Warrior;

public class GoldRing extends Amulet{

    public GoldRing(){
        name = "Gold Ring";
        gillCost = 4000;
    }

    public void castSpell(Warrior lord){
        lord.setMaxManaPoint(lord.getMaxManaPoint() + 2);
    }
}
