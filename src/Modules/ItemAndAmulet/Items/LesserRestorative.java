package Modules.ItemAndAmulet.Items;

import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class LesserRestorative extends Item{

    public LesserRestorative(){
        gillCost = 2000;
        name = "Lesser Restorative";
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
