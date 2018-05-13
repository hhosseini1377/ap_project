package Modules.ItemAndAmulet.Items;

import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;

public class LargeMPPotion extends Item{
    private String detail = "Increase Player's MP by 8";

    public LargeMPPotion(){
        gillCost = 4000;
        name = "Large MP Potion";
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
        player.setManaPoint(player.getManaPoint() + 8);
        System.out.println("player mana point was increased by 8");
    }
}
