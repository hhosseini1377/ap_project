package Modules.ItemAndAmulet.Items;

import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class MediumHPPotion extends Item{

    public MediumHPPotion(){
        gillCost = 2000;
        name = "Medium HP Potion";
        itemImage = new ImageView(new Image(new File("./src/Files/Images/Items/" + this.name + ".png").toURI().toString()));

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
        String detail = "Increase Player's HP by 1000";
        return detail;
    }

    @Override
    public void castSpell(Warrior player) {
        player.getCommander().increaseHP(1000);
        System.out.println("player health point was increased by 1000");
    }
}
