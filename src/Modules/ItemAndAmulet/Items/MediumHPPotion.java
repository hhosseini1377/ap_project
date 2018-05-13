package Modules.ItemAndAmulet.Items;

import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;

public class MediumHPPotion extends Item{
    private String detail = "Increase Player's HP by 1000";

    public MediumHPPotion(){
        gillCost = 2000;
        name = "Medium HP Potion";
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
        player.getCommander().increaseHP(1000);
        System.out.println("player health point was increased by 1000");
    }
}
