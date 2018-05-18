package Modules.ItemAndAmulet.Amulets;

import Modules.ItemAndAmulet.Amulet;
import Modules.Warrior.Warrior;

public class DiamondRing extends Amulet {
    public DiamondRing(){
        name = "Diamond Ring";
        gillCost = 6000;
    }

    public void castSpell(Warrior lord){
        lord.setMaxManaPoint(lord.getMaxManaPoint() + 3);
    }
}
