package Modules.Card.Monsters.DragonBreed;

import Modules.Card.Monsters.Hero;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public class Igneel extends Hero {
    public Igneel() {
        name = "Igneel, The Dragon King";
        battleCryName = "King's Grace";
        spellName = "King's Wing Slash";
        willName = "King's Wail";
        battleCryDetail = "Send all non-Hero monster cards on both sides\n of field to their graveyards";
        willDetail = "Decrease all enemy monster cards' AP by 400";
        spellDetail = "Deal 600 damage to all enemy monster cards and player";
        HP = 4000;
        AP = 800;
        initialAP = AP;
        initialHP = HP;
        manaPoint = 10;
        gillCost = 1000 * manaPoint;
        isNimble = false;
        offenseType = true;
        monsterKind = MonsterKind.HERO;
        monsterTribe = MonsterTribe.DRAGON_BREED;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.SCREEN_WIDTH * 3 / 18,Graphics.SCREEN_HEIGHT * 5 / 12,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.SCREEN_WIDTH * 6 / 18, Graphics.SCREEN_HEIGHT * 9 /12, cardImage,this, 0, 0, true);
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend) {
        if (!canCast) {
            System.out.println("this warrior cannot cast a spell right now!");
            return;
        }
        enemy.getMonsterField().getMonsterCards().forEach(card -> card.decreaseHP(600));
        enemy.getCommander().decreaseHP(600);
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        enemy.getMonsterField().getMonsterCards().forEach(card -> card.decreaseAP(400));
        System.out.println(this.getName() + " has cast a spell:\n" + this.willDetail());
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        for (Monster monster:enemy.getMonsterField().getMonsterCards()){
            if (monster.getMonsterKind() != MonsterKind.HERO){
                enemy.getGraveYard().add(monster);
                enemy.getMonsterField().remove(monster);
                monster.die(enemy, friend);
            }
        }
        for (Monster monster:friend.getMonsterField().getMonsterCards()){
            if (monster.getMonsterKind() != MonsterKind.HERO){
                friend.getGraveYard().add(monster);
                friend.getMonsterField().remove(monster);
                monster.die(enemy, friend);
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.battleCryDetail());
    }

    @Override
    public String spellDetail() {
        return spellDetail;
    }

    @Override
    public String willDetail() {
        return willDetail;
    }

    @Override
    public String battleCryDetail() {
        return battleCryDetail;
    }

    @Override
    public String getSpellName() {
        return spellName;
    }

    @Override
    public String getWillName() {
        return willName;
    }

    @Override
    public String getBattleCryName() {
        return battleCryName;
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
}