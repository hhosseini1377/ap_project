package Modules.Card.Monsters.Demonic;

import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

public class GoblinShaman extends SpellCaster{

    public GoblinShaman(){
        this.name = "Goblin Shaman";
        this.initialHP = 1000;
        this.HP = 1000;
        this.initialAP = 700;
        this.AP = 700;
        this.manaPoint = 4;
        gillCost = manaPoint * 500;
        this.monsterKind = MonsterKind.SPELL_CASTER;
        this.isNimble = false;
        this.offenseType = true;
        monsterTribe = MonsterTribe.DEMONIC;
        cardImage = new Image("Files/Images/CardImages/"+this.name+".jpg");
        cardView = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend){
        if (CanCast()){
            int randomNumber = (int)(Math.random() * enemy.getMonsterField().getMonsterCards().size());
            enemy.getMonsterField().getMonsterCards().get(randomNumber).increaseHP(400);
            canCast = false;
            System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
        }else
            System.out.println("this monster cannot cast now");
    }

    public String getSpellName() {
        String spellName = "mend";
        return spellName;
    }

    @Override
    public String spellDetail() {
        String spellDetail = "Increase a friendly monster card or player's HP by 400";
        return spellDetail;
    }
}
