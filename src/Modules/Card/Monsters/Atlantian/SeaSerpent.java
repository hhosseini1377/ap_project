package Modules.Card.Monsters.Atlantian;

import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public class SeaSerpent extends SpellCaster{

    public SeaSerpent(){
        name = "Sea Serpent";
        HP = 1800;
        initialHP = HP;
        AP = 200;
        initialAP = AP;
        manaPoint = 7;
        gillCost = manaPoint * 500;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.ATLANTIAN;
        isNimble = true;
        offenseType = true;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);
    }

    public String getSpellName() {
        String spellName = "Serpent's Bite";
        return spellName;
    }

    public void castSpell(Warrior enemy, Warrior friend){
        if (!CanCast()) {
            System.out.println("this monster cannot cast now");
            return;
        }
        int random = (int)(enemy.getMonsterField().getMonsterCards().size() * Math.random());
        try{
            enemy.getMonsterField().getMonsterCards().get(random).decreaseHP(1000);
        }catch (Exception e){
            enemy.getCommander().decreaseHP(1000);
        }
        canCast = false;
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public void enterField(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has entered the field proudly!");
    }

    @Override
    public void die(Warrior enemy, Warrior friend) {
     super.die(enemy, friend);
    }

    @Override
    public String spellDetail() {
        String spellDetail = " Deal 1000 damage to an enemy monster \ncard or player";
        return spellDetail;
    }
}
