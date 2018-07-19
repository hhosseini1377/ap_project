package Modules.Card.Monsters.Elven;

import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Card.Spell.Spell;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

public class ElvenSorceress extends SpellCaster{

    public ElvenSorceress(){
        name = "Elven Sorceress";
        HP = 1000;
        initialHP = HP;
        AP = 1000;
        initialAP = AP;
        manaPoint = 7;
        gillCost = manaPoint * 500;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.ELVEN;
        isNimble = true;
        offenseType = true;
        cardImage = new Image("Files/Images/CardImages/"+this.name+".jpg");
        cardView = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);
    }

    public String getSpellName() {
        String spellName = "Arcane Explosion";
        return spellName;
    }

    public void castSpell(Warrior enemy, Warrior friend){
        if (!CanCast()) {
            System.out.println("this monster cannot cast now");
            return;
        }
        enemy.getMonsterField().getMonsterCards().forEach(card -> card.decreaseHP(400));
        try {
            Spell spell = enemy.getSpellField().getSpellCards().get((int) (Math.random() *
                    enemy.getSpellField().getSpellCards().size()));
            enemy.getSpellField().remove(spell);
            enemy.getGraveYard().add(spell);
            canCast = false;
            System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
        }catch (Exception e){
            System.out.println("Spell field empty!" + e.toString());
        }
    }

    @Override
    public String spellDetail() {
        String spellDetail = " Increase a selected friendly monster card’s HP\n by 500 and AP by 300";
        return spellDetail;
    }
}
