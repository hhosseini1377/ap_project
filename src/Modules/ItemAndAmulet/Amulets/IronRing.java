package Modules.ItemAndAmulet.Amulets;

import Modules.ItemAndAmulet.Amulet;
import Modules.Warrior.Warrior;

public class IronRing extends Amulet{
    public IronRing(){
        name = "Iron Ring";
        gillCost = 2000;
    }

    public void castSpell(Warrior lord){
        lord.setMaxManaPoint(lord.getMaxManaPoint() + 1);
    }
}
