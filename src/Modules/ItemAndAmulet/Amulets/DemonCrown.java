package Modules.ItemAndAmulet.Amulets;

import Modules.ItemAndAmulet.Amulet;
import Modules.Warrior.Warrior;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class DemonCrown extends Amulet {
    public DemonCrown(){
            name = "Demon King's Crown";
            amuletImage = new ImageView(new Image(new File("./src/Files/Images/Amulets/" + this.name + ".jpeg").toURI().toString()));
    }

    public void castSpell(Warrior lord){
        double coefficient = 0.2;
        lord.getCommander().setReductionCoefficiency(coefficient);
    }
}
