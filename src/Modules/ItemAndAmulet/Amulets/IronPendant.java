package Modules.ItemAndAmulet.Amulets;

import Modules.ItemAndAmulet.Amulet;

public class IronPendant extends Amulet{
    private String detail = "Increase Player's Max HP by 500";

    public IronPendant(){
        name = "Iron Pendant";
        gillCost = 2000;
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
    public void castSpell() {

    }
}
