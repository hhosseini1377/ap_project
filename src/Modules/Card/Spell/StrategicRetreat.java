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
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.SCREEN_WIDTH * 3 / 18,Graphics.SCREEN_HEIGHT * 5 / 12,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.SCREEN_WIDTH * 6 / 18, Graphics.SCREEN_HEIGHT * 9 /12, cardImage,this, 0, 0, true);
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
