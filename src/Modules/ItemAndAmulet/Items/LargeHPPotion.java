package Modules.ItemAndAmulet.Items;

import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class LargeHPPotion extends Item{

    public LargeHPPotion(){
        gillCost = 4000;
        name = "Large HP Potion";
        itemImage = new ImageView(new Image(new File("Files/Images/Items/" + this.name + ".jpg").toURI().toString()));

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
        String detail = "Increase Player's HP by 2000";
        return detail;
    }

    @Override
    public void castSpell(Warrior player) {
        player.getCommander().increaseHP(2000);
        System.out.println("player health point was increased by 2000");
    }
}
