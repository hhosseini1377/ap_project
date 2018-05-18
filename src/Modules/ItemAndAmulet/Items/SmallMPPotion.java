package Modules.ItemAndAmulet.Items;

import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;

public class SmallMPPotion extends Item{
    private String detail = "Increase Player's MP by 2";

    public SmallMPPotion(){
        gillCost = 1000;
        name = "Small MP Potion";
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
        player.setManaPoint(player.getManaPoint() + 2);
        System.out.println("player mana point was increased by 2");
    }
}
