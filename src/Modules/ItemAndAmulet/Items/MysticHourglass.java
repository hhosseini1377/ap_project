package Modules.ItemAndAmulet.Items;

import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;

public class MysticHourglass extends Item{
    private String detail = "returns the time to before the battle started";

    public MysticHourglass(){
        gillCost = 10000;
        name = "Mystic Hourglass";
    }

    @Override
    public boolean equals(Item other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String spelldetail() {
        return detail;
    }

    @Override
    public void castSpell(Warrior player) {

    }

}
