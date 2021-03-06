package Modules.Card.Monsters.DragonBreed;

import Modules.Card.Card;
import Modules.Card.Monsters.General;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public class GreaterDragon extends General{

    public GreaterDragon(){
        name = "Greater Dragon";
        initialHP = 2000;
        initialAP = 1800;
        HP = initialHP;
        AP = initialAP;
        manaPoint = 8;
        gillCost = manaPoint * 700;
        isNimble = false;
        offenseType = true;
        monsterKind = MonsterKind.GENERAL;
        monsterTribe = MonsterTribe.DRAGON_BREED;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.SCREEN_WIDTH * 3 / 18,Graphics.SCREEN_HEIGHT * 5 / 12,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.SCREEN_WIDTH * 6 / 18, Graphics.SCREEN_HEIGHT * 9 /12, cardImage,this, 0, 0, true);
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        for (int i = 0; i < 2; i++) {
            Card card = friend.getDeck().takeCard();
            friend.getHand().add(card);
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.willDetail());
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        int random = (int)(Math.random() * enemy.getMonsterField().getMonsterCards().size());
        enemy.getGraveYard().add(enemy.getMonsterField().getMonsterCards().get(random));
        enemy.getMonsterField().remove(enemy.getMonsterField().getMonsterCards().get(random));
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
        String willDetail = "Draw two cards from deck to hand";
        return willDetail;
    }

    @Override
    public String battleCryDetail() {
        String battleCryDetail = "Send a random enemy monster card from field\n to graveyard";
        return battleCryDetail;
    }

    public String getWillName() {
        String willName = "Dragon's Call";
        return willName;
    }

    public String getBattleCryName() {
        String battleCryName = "Devour";
        return battleCryName;
    }

}
