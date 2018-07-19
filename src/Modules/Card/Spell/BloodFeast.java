package Modules.Card.Spell;

import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public class BloodFeast extends Spell{
    public BloodFeast(){
        name = "Blood Feast";
        manaPoint = 4;
        gillCost = manaPoint * 700;
        spellDetail = "Deal 500 damage to enemy player and heal\n" +
                "your player for 500 HP";
        spellType = SpellType.INSTANT;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);

    }

    @Override
    public void castSpell() {
        enemy.getCommander().decreaseHP(500);
        friend.getCommander().increaseHP(500);
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }
}
