package Modules.Card.Monsters.Demonic;

import Modules.Card.Card;
import Modules.Card.Monsters.Hero;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Spell.Spell;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Scanner;
import java.util.regex.Matcher;

public class Cerberus extends Hero{
    public Cerberus() {
        name = "Cerberus, Gatekeeper of Hell";
        battleCryName = "Open the Gate";
        spellName = "Hellfire";
        willName = "Revenge of the Two Heads";
        battleCryDetail = "Draw three cards from deck to hand";
        willDetail = "Send two random enemy monster cards from field to graveyard";
        spellDetail = "Deal 300 damage to all enemy monster cards\n and Increase HP and AP of all friendly monster\n cards by 300";
        HP = 3000;
        AP = 2000;
        initialAP = AP;
        initialHP = HP;
        manaPoint = 10;
        gillCost = 1000 * manaPoint;
        isNimble = true;
        offenseType = true;
        monsterKind = MonsterKind.HERO;
        monsterTribe = MonsterTribe.DEMONIC;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.SCREEN_WIDTH * 3 / 18,Graphics.SCREEN_HEIGHT * 5 / 12,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend) {
        if (!canCast) {
            System.out.println("this warrior cannot cast a spell right now!");
            return;
        }
        enemy.getMonsterField().getMonsterCards().forEach(card -> card.decreaseHP(300));
        friend.getMonsterField().getMonsterCards().forEach(card -> {card.increaseHP(300);card.increaseAP(300);});
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        for (int i = 0; i < 2; i++){
            int random = (int)(Math.random() * enemy.getMonsterField().getMonsterCards().size());
            enemy.getGraveYard().add(enemy.getMonsterField().getMonsterCards().get(random));
            enemy.getMonsterField().remove(enemy.getMonsterField().getMonsterCards().get(random));
        }
        System.out.println(this.getName() + " has cast " + this.willName + ": " + this.willDetail());
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has cast " + this.battleCryName + ": " + this.battleCryDetail());
        for (int i = 0; i < 3; i++)
            friend.getHand().add(friend.getDeck().takeCard());
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
