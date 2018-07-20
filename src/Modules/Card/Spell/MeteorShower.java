package Modules.Card.Spell;

import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Random;

public class MeteorShower extends Spell {
    public MeteorShower(){
        name = "Meteor Shower";
        manaPoint = 8;
        gillCost = 700 * manaPoint;
        spellDetail = "Deal 800 damage to a random enemy\nmonster card on field or player";
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
        if (!canCast) {
            System.out.println("this card cannot cast anymore");
        } else {
            Random ran = new Random();
            int cardToDamage = ran.nextInt() % (enemy.getMonsterField().getMonsterCards().size() + 1);
            if (cardToDamage == 0) {
                enemy.getCommander().decreaseHP(800);
            } else {
                enemy.getMonsterField().getMonsterCards().get(cardToDamage - 1).decreaseHP(800);
            }
            System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
        }
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
