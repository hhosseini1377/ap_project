package Modules.Card.Spell;

import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Random;

public class ReaperScythe extends Spell {
    public ReaperScythe() {
        name = "Reaper's Scythe";
        manaPoint = 4;
        gillCost = 700 * manaPoint;
        spellDetail = "Send an enemy monster or spell card\nfrom field to graveyard";
        spellType = SpellType.INSTANT;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.SCREEN_WIDTH * 3 / 18,Graphics.SCREEN_HEIGHT * 5 / 12,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.SCREEN_WIDTH * 6 / 18, Graphics.SCREEN_HEIGHT * 9 /12, cardImage,this, 0, 0, true);
    }

    public boolean canCast() {
        return canCast;
    }

    @Override
    public void castSpell() {
        if (!canCast) {
            System.out.println("this card cannot cast anymore");
        } else {
            int cardsInSpellField = enemy.getSpellField().getSpellCards().size();
            int cardsInMonsterField = enemy.getMonsterField().getMonsterCards().size();
            int totalCards = cardsInMonsterField + cardsInSpellField;
            Random ran = new Random();
            int cardToRemove = ran.nextInt() % totalCards + 1;
            if (cardToRemove <= cardsInSpellField) {
                enemy.getGraveYard().add(enemy.getSpellField().getSpellCards().get(cardToRemove - 1));
                enemy.getSpellField().remove(enemy.getSpellField().getSpellCards().get(cardToRemove - 1));
            } else {
                cardToRemove -= cardsInSpellField;
                enemy.getGraveYard().add(enemy.getMonsterField().getMonsterCards().get(cardToRemove - 1));
                enemy.getMonsterField().remove(enemy.getMonsterField().getMonsterCards().get(cardToRemove - 1));

            }
            System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
        }
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
