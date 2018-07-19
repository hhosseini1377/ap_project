package Modules.Card.Monsters.Elven;

import Modules.Card.Card;
import Modules.Card.Monsters.General;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Spell.Spell;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public class NobleElf extends General{

    public NobleElf(){
        name = "Noble Elf";
        initialHP = 2000;
        initialAP = 2400;
        HP = initialHP;
        AP = initialAP;
        manaPoint = 9;
        gillCost = manaPoint * 700;
        isNimble = false;
        offenseType = true;
        monsterKind = MonsterKind.GENERAL;
        monsterTribe = MonsterTribe.ELVEN;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        for (int i = 0; i < friend.getMonsterField().getMonsterCards().size(); i++) {
            if (friend.getMonsterField().getMonsterCards().get(i).getMonsterTribe().equals(MonsterTribe.ELVEN)) {
                friend.getMonsterField().getMonsterCards().get(i).increaseHP(800);
                friend.getMonsterField().getMonsterCards().get(i).increaseAP(600);
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.willDetail());
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        for (Card card:enemy.getSpellField().getSpellCards()) {
            enemy.getHand().add(card);
            enemy.getSpellField().remove((Spell)card);
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.battleCryDetail());
    }

    @Override
    public String willDetail() {
        String willDetail = "Increase a random friendly Elven monster card on the\n" +
                " field’s HP by 800 and AP by 600";
        return willDetail;
    }

    @Override
    public String battleCryDetail() {
        String battleCryDetail = "Remove all enemy spell cards on the field and\n move them to hand";
        return battleCryDetail;
    }

    @Override
    public void enterField (Warrior enemy, Warrior friend) {
        super.enterField(enemy, friend);
        battleCry(enemy, friend);
    }

    @Override
    public void die (Warrior enemy, Warrior friend) {
        super.die(enemy, friend);
        will(enemy, friend);
    }

    public String getWillName() {
        String willName = "Elven Enliven";
        return willName;
    }

    public String getBattleCryName() {
        String battleCryName = "Purge";
        return battleCryName;
    }
}
