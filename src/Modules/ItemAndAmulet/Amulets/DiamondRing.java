package Modules.ItemAndAmulet.Amulets;

import Modules.ItemAndAmulet.Amulet;
import Modules.Warrior.Warrior;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class DiamondRing extends Amulet {
    public DiamondRing(){
        name = "Diamond Ring";
        gillCost = 6000;
        amuletImage = new ImageView(new Image(new File("./src/Files/Images/Amulets/" + this.name + ".jpeg").toURI().toString()));

    }

    public void castSpell(Warrior lord){
        lord.setMaxManaPoint(lord.getMaxManaPoint() + 3);
    }
}
