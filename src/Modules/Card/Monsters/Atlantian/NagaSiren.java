package Modules.Card.Monsters.Atlantian;

import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

public class NagaSiren extends SpellCaster{

    public NagaSiren(){
        name = "Naga Siren";
        HP = 1200;
        initialHP = HP;
        AP = 600;
        initialAP = AP;
        manaPoint = 6;
        gillCost = manaPoint * 500;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.ATLANTIAN;
        isNimble = true;
        offenseType = true;
        cardImage = new Image("Files/Images/CardImages/"+this.name+".jpg");
        cardView = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);
    }

    public String getSpellName() {
        String spellName = "Song of Siren";
        return spellName;
    }

    public void castSpell(Warrior enemy, Warrior friend){
        if (!CanCast()) {
            System.out.println("this monster cannot cast now");
            return;
        }
        friend.getMonsterField().getMonsterCards().forEach(card -> card.increaseHP(300));
        friend.getMonsterField().getMonsterCards().forEach(card -> card.increaseAP(200));
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
        String spellDetail = " Increase HP of all friendly monster cards by 300 \n and their AP by 200";
        return spellDetail;
    }
}
