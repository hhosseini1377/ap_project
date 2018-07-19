package Modules.Card.Monsters.Demonic;

import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Graphic.Graphics;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;
import java.util.ArrayList;

public class VampireAcolyte extends SpellCaster{

    public VampireAcolyte(){
        name = "Vampire Acolyte";
        HP = 1500;
        initialHP = 1500;
        AP = 1500;
        initialAP = 1500;
        manaPoint = 7;
        gillCost = manaPoint * 500 ;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.DEMONIC;
        isNimble = true;
        offenseType = true;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);
    }

    public String getSpellName() {
        String spellName = "Black Wave";
        return spellName;
    }

    public void castSpell(ArrayList<Monster> enemyCards, ArrayList<Monster> friendlyCards) {
        if (!CanCast()) {
            System.out.println("this monster cannot cast now");
            return;
        }
        enemyCards.forEach(monster -> monster.decreaseHP(300));
        friendlyCards.forEach(monster -> monster.increaseHP(300));
        canCast = false;
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    public String spellDetail(){
        return "Deal 300 damage to all enemy monster cards\n and heal all friendly monster cards for 300 HP";
    }
}
