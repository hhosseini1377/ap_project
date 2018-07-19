package Modules.ItemAndAmulet.Items;

import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;
import javafx.scene.image.Image;

import javax.swing.text.html.ImageView;

    public class GreaterRestorative extends Item{

    public GreaterRestorative(){
        gillCost = 4000;
        name = "Greater Restorative";
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
        String detail = "Increase Player's HP by 1000 and MP by 4";
        return detail;
    }

    @Override
    public void castSpell(Warrior player) {
        player.setManaPoint(player.getManaPoint() + 4);
        player.getCommander().increaseHP(1000);
        System.out.println("player mana point was increased by 4");
        System.out.println("player health point was increased by 1000");
    }
}
