package Modules.Card.Monsters.Demonic;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

public class UndeadMage extends SpellCaster{

    public UndeadMage(){
        name = "Undead Mage";
        HP = 800;
        initialHP = 800;
        AP = 1000;
        initialAP = 1000;
        manaPoint = 6;
        gillCost = manaPoint * 500 ;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.DEMONIC;
        isNimble = false;
        offenseType = true;
        cardImage = new Image("Files/Images/CardImages/"+this.name+".jpg");
        cardView = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);
    }

    public String getSpellName() {
        String spellName = "Curse";
        return spellName;
    }

    @Override
    public void castSpell(Card card) {
        ((Monster)card).decreaseAP(500);
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend){
        if (!CanCast()) {
            System.out.println("this monster cannot cast now");
            return;
        }
        int random = (int)(Math.random() * enemy.getMonsterField().getMonsterCards().size());
        castSpell(enemy.getMonsterField().getMonsterCards().get(random));
    }


    public String spellDetail(){
        return "Reduce an enemy monster card's AP by 500";
    }
}
