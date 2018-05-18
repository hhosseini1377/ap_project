package Modules.ItemAndAmulet.Items;

import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;

public class LargeHPPotion extends Item{
    private String detail = "Increase Player's HP by 2000";

    public LargeHPPotion(){
        gillCost = 4000;
        name = "Large HP Potion";
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
        player.getCommander().increaseHP(2000);
        System.out.println("player health point was increased by 2000");
    }
}
