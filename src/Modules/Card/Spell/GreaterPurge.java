package Modules.Card.Spell;

import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public class GreaterPurge extends Spell{
    public GreaterPurge(){
    name = "Greater Purge";
    manaPoint = 7;
    gillCost = 700 * manaPoint;
    spellDetail = "Remove all spell cards on field\nfrom both sides and move them to hand";
    spellType = SpellType.INSTANT;
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
                enemy.getHand().add(enemy.getSpellField().getSpellCards().get(i));
                enemy.getSpellField().getSpellCards().remove(enemy.getSpellField().getSpellCards().get(i));
            }
            for (int i = 0; i < friend.getSpellField().getSpellCards().size(); i++) {
                friend.getHand().add(friend.getSpellField().getSpellCards().get(i));
                friend.getSpellField().getSpellCards().remove(friend.getSpellField().getSpellCards().get(i));
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
