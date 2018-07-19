package Modules.Card.Monsters.Atlantian;

import Modules.Card.Monsters.General;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public class Kraken extends General{

    public Kraken(){
        name = "Kraken";
        initialHP = 1800;
        initialAP = 2200;
        HP = initialHP;
        AP = initialAP;
        manaPoint = 8;
        gillCost = manaPoint * 700;
        isNimble = false;
        offenseType = true;
        monsterKind = MonsterKind.GENERAL;
        monsterTribe = MonsterTribe.ATLANTIAN;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        enemy.getMonsterField().getMonsterCards().forEach(card -> card.decreaseHP(400));
        enemy.getCommander().decreaseHP(400);
        System.out.println(this.getName() + " has cast a spell:\n" + this.willDetail());
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        int random = (int)(Math.random() * enemy.getMonsterField().getMonsterCards().size());
        try{
            enemy.getHand().add(enemy.getMonsterField().getMonsterCards().get(random));
            enemy.getMonsterField().remove(enemy.getMonsterField().getMonsterCards().get(random));
        }catch (Exception e){
            System.out.println("there was no monster in the field");
        }
        enemy.getMonsterField().getMonsterCards().forEach(card -> card.decreaseAP(200));
        System.out.println(this.getName() + " has cast a spell:\n" + this.battleCryDetail());
    }

    @Override
    public void enterField(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has entered the field proudly!");
        battleCry(enemy, friend);
    }

    @Override
    public void die(Warrior enemy, Warrior friend) {
        super.die(enemy, friend);
        will(enemy, friend);
    }

    @Override
    public String willDetail() {
        String willDetail = "Deal 400 damage to all enemy monster cards and player";
        return willDetail;
    }

    @Override
    public String battleCryDetail() {
        String battleCryDetail = " Return one random enemy monster card from field to \n hand and reduce all enemy monsters’ AP by 200";
        return battleCryDetail;
    }

    public String getWillName() {
        String willName = "Titan's Fall";
        return willName;
    }

    public String getBattleCryName() {
        String battleCryName = "Titan's Presence";
        return battleCryName;
    }
}
