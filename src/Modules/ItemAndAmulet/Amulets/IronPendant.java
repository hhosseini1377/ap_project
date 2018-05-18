package Modules.ItemAndAmulet.Amulets;

import Modules.ItemAndAmulet.Amulet;

public class IronPendant extends Amulet{

    public IronPendant(){
        name = "Iron Pendant";
        gillCost = 2000;
    }

    public String getDetail() {
        String detail = "Increase Player's Max HP by 500";
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

    public void castSpell() {

    }
}
