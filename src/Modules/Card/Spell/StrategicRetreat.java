package Modules.Card.Spell;

import Modules.Card.Card;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public class StrategicRetreat extends Spell {
    public StrategicRetreat(){
        name = "Strategic Retreat";
        manaPoint = 6;
        gillCost = 700 * manaPoint;
        spellDetail = "Select and move a" +
                " monster card from\nfield to hand and draw one card from deck";
        spellType = SpellType.INSTANT;
        Image cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        CardView cardView = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,false);
        CardView cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);

    }

    public boolean canCast(){
        return canCast;
    }

    @Override
    public void castSpell(Card card){

    }

    @Override
    public void castSpell() {
        //TODO
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
