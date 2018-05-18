package Modules.ItemAndAmulet.Items;

import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;

public class MediumMPPotion extends Item{
    private String detail = "Increase Player's MP by 4";

    public MediumMPPotion(){
        gillCost = 2000;
        name = "Medium MP Potion";
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
        player.setManaPoint(player.getManaPoint() + 4);
        System.out.println("player mana point was increased by 4");
    }
}
