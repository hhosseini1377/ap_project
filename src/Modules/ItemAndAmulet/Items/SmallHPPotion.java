package Modules.ItemAndAmulet.Items;

import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;

public class SmallHPPotion extends Item{

    public SmallHPPotion(){
        gillCost = 1000;
        name = "Small HP Potion";
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
        String detail = "Increase Player's HP by 500";
        return detail;
    }

    @Override
    public void castSpell(Warrior player) {
        player.getCommander().increaseHP(500);
        System.out.println("player health point was increased by 500");
    }
}
