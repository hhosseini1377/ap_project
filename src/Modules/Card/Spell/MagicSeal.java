package Modules.Card.Spell;

import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public class MagicSeal extends Spell {
    public MagicSeal(){
        name = "Magic Seal";
        manaPoint = 9;
        gillCost = 700 * manaPoint;
        spellDetail = "Remove all enemy spell cards from\nfield and move them to graveyard";
        spellType = SpellType.CONTINUOUS;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.SCREEN_WIDTH * 3 / 18,Graphics.SCREEN_HEIGHT * 5 / 12,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.SCREEN_WIDTH * 6 / 18, Graphics.SCREEN_HEIGHT * 9 /12, cardImage,this, 0, 0, true);
    }

    public boolean canCast(){
        return canCast;
    }

    @Override
    public void castSpell() {
        if(!canCast){
            System.out.println("this card cannot cast any more");
        }
        else {
            for (int i = 0; i < enemy.getSpellField().getSpellCards().size(); i++) {
                enemy.getGraveYard().add(enemy.getSpellField().getSpellCards().get(i));
                enemy.getSpellField().getSpellCards().remove(enemy.getSpellField().getSpellCards().get(i));
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
