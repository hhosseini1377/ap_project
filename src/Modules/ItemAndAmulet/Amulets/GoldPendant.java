package Modules.ItemAndAmulet.Amulets;

import Modules.ItemAndAmulet.Amulet;
import Modules.Warrior.Warrior;

public class GoldPendant extends Amulet{
    private String detail = "Increase Player's Max HP by 1000";

    public GoldPendant(){
        name = "Gold Pendant";
        gillCost = 8000;
    }

    public String getDetail() {
        return detail;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Amulet other) {
        return super.equals(other);
    }

    @Override
    public void castSpell(Warrior lord) {
        lord.getCommander().setInitialHP(lord.getCommander().getInitialHP() +1000);
    }
}
