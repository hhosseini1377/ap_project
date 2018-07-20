package Modules.Card.Spell;

import Modules.Card.Monsters.MonsterKind;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public class TakeAllYouCan extends Spell {
    public TakeAllYouCan(){
        name = "Take All You Can";
        manaPoint = 7;
        gillCost = 700 * manaPoint;
        spellDetail = "Increase all friendly normal\nmonster cards' HP and AP by 400";
        spellType = SpellType.AURA;
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
            for (int i = 0; i < friend.getMonsterField().getMonsterCards().size(); i++) {
                if (friend.getMonsterField().getMonsterCards().get(i).getMonsterKind().equals(MonsterKind.NORMAL)) {
                    friend.getMonsterField().getMonsterCards().get(i).increaseAP(400);
                    friend.getMonsterField().getMonsterCards().get(i).increaseHP(400);
                }
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
