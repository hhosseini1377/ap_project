package Modules.Card.Spell;

import Modules.Card.Monsters.Monster;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public class PoisonousCauldron extends Spell{
    public PoisonousCauldron(){
        name = "Poisonous Cauldron";
        manaPoint = 4;
        gillCost = 700 * manaPoint;
        spellDetail = "Deal 100 damage to all enemy\nmonster cards and enemy player";
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
        for (Monster monster:enemy.getMonsterField().getMonsterCards()){
            monster.decreaseHP(100);
        }
        enemy.getCommander().decreaseHP(100);
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
