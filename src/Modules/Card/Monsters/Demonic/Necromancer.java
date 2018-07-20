package Modules.Card.Monsters.Demonic;

import Modules.Card.Card;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public class Necromancer extends SpellCaster{

    public Necromancer(){
        name = "Necromancer";
        HP = 1200;
        initialHP = 1200;
        AP = 1500;
        initialAP = 1500;
        manaPoint = 7;
        gillCost = manaPoint * 500;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.DEMONIC;
        isNimble = true;
        offenseType = true;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.SCREEN_WIDTH * 3 / 18,Graphics.SCREEN_HEIGHT * 5 / 12,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);
    }

    public String getSpellName() {
        String spellName = "Raise Dead";
        return spellName;
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend){
        if (CanCast()) {
            int random = (int)(Math.random() * friend.getGraveYard().getDestroyedCards().size());
            Card card = friend.getGraveYard().getDestroyedCards().get(random);
            friend.getGraveYard().remove(card.getName());
            friend.getHand().add(card);
            canCast = false;

            System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
        }else
            System.out.println("this monster cannot cast now");
    }

    public String spellDetail(){
        return "Move a random card from your graveyard to your hand";
    }
}
