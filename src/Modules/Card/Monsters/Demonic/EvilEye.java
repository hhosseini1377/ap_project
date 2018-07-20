package Modules.Card.Monsters.Demonic;

import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public class EvilEye extends SpellCaster{

    public EvilEye(){
        this.name = "Evil Eye";
        this.initialHP = 400;
        this.HP = 400;
        this.initialAP = 400;
        this.AP = 400;
        this.manaPoint = 6;
        gillCost = manaPoint * 500;
        this.monsterKind = MonsterKind.SPELL_CASTER;
        this.isNimble = false;
        this.offenseType = true;
        monsterTribe = MonsterTribe.DEMONIC;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.SCREEN_WIDTH * 3 / 18,Graphics.SCREEN_HEIGHT * 5 / 12,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.SCREEN_WIDTH * 6 / 18, Graphics.SCREEN_HEIGHT * 9 /12, cardImage,this, 0, 0, true);
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend){
        if (CanCast()) {
        enemy.getMonsterField().getMonsterCards().forEach(card -> card.decreaseHP(800));
        canCast = false;
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
        }else
        System.out.println("this monster cannot cast now");
    }

    public String getSpellName() {
        String spellName = "Evil Gaze";
        return spellName;
    }

    @Override
    public void enterField(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has entered the field proudly!");
    }

    @Override
    public void die(Warrior enemy, Warrior friend) {
     super.die(enemy, friend);
    }

        public String spellDetail(){
            return "Deal 800 damage to all enemy monster cards and player";
        }
    }
