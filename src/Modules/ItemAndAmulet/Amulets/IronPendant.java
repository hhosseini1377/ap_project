package Modules.ItemAndAmulet.Amulets;

import Modules.ItemAndAmulet.Amulet;
import Modules.Warrior.Warrior;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;

public class IronPendant extends Amulet{
    private String detail = "Increase Player's Max HP by 500";

    public IronPendant(){
        name = "Iron Pendant";
        gillCost = 2000;
        amuletImage = new ImageView(new Image(new File("./src/Files/Images/Amulets/" + this.name + ".jpeg").toURI().toString()));

    }

    public String getDetail() {
        return detail;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Amulet other) {
        return super.equals(other);
    }

    @Override
    public void castSpell(Warrior lord) {
        lord.getCommander().setInitialHP(lord.getCommander().getInitialHP() +500);
    }
}
