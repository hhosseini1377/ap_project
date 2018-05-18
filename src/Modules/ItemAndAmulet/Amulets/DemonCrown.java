package Modules.ItemAndAmulet.Amulets;

import Modules.ItemAndAmulet.Amulet;
import Modules.Warrior.Warrior;

public class DemonCrown extends Amulet {
    public DemonCrown(){
        name = "Demon King's Crown";
    }

    public void castSpell(Warrior lord){
        double coefficient = 0.2;
        lord.getCommander().setReductionCoefficiency(coefficient);
    }
}
