package Modules.ItemAndAmulet;

import Modules.Warrior.Warrior;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.File;
import java.io.Serializable;


public abstract class Amulet implements Serializable {
    protected int gillCost;
    protected String name;
    protected ImageView amuletImage;

    public void startViews(){
        amuletImage = new ImageView(new Image(new File("./src/Files/Images/Amulets/" + this.name + ".jpeg").toURI().toString()));

    }

    public ImageView getAmuletImage() {
        return amuletImage;
    }

    public int getGillCost() {
        return gillCost;
    }

    public void setGillCost(int gillCost) {
        this.gillCost = gillCost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract void castSpell(Warrior lord);

    public String toString(){
        return name;
    }

    public boolean equals(Amulet other){
        return other.getName().equals(this.getName());
    }

    public String detail(){
        return "nothing special";
    }

    public int hashCode(){
        return super.hashCode();
    }
}
