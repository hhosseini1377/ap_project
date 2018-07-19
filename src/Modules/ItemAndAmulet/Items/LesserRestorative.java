package Modules.ItemAndAmulet.Items;

import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;

public class LesserRestorative extends Item{

    public LesserRestorative(){
        gillCost = 2000;
        name = "Lesser Restorative";
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
        String detail = "Increase Player's HP by 500 and MP by 2";
        return detail;
    }

    @Override
    public void castSpell(Warrior player) {
        player.setManaPoint(player.getManaPoint() + 2);
        player.getCommander().increaseHP(500);
        System.out.println("player mana point was increased by 2");
        System.out.println("player health point was increased by 500");
    }
}
