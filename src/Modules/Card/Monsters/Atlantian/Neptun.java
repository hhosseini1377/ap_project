package Modules.Card.Monsters.Atlantian;

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

public class Neptun extends Hero {
    public Neptun() {
        name = "Neptune, King of Atlantis";
        battleCryName = "Call to Arms";
        spellName = "Trident Beam";
        willName = "Ocean's Cry";
        battleCryDetail = " Select and move a card from\nhand to play field";
        willDetail = " Deal 400 damage to all enemy\nmonster cards and player and\n" +
                " increase all friend Atlantian monster cards' AP by 500";
        spellDetail = "Deal 800 damage to three random\nenemy monster cards or player";
        HP = 2200;
        AP = 2500;
        initialAP = AP;
        initialHP = HP;
        manaPoint = 10;
        gillCost = 1000 * manaPoint;
        isNimble = true;
        offenseType = true;
        monsterKind = MonsterKind.HERO;
        monsterTribe = MonsterTribe.ATLANTIAN;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend) {
        if (!canCast) {
            System.out.println("this warrior cannot cast a spell right now!");
            return;
        }
        for (int i = 0; i < 3; i++) {
            try {
                int random = (int) (Math.random() * enemy.getMonsterField().getMonsterCards().size());
                enemy.getMonsterField().getMonsterCards().get(random).decreaseAP(800);
            } catch (Exception e) {
                System.out.println("there is not enough cards in monsterfield!");
                return;
            }
        }
        canCast = false;
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        friend.getMonsterField().getMonsterCards().forEach(card -> {
            card.increaseAP(200);
            card.increaseHP(500);
        });
        friend.getCommander().increaseHP(500);
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has cast a spell:\n" + this.battleCryDetail());
        System.out.println("\nList of targets:");
        for (int i = 1; i <= 5; i++){
            try {
                if (friend.getHand().getCards().get(i - 1) == null) {
                    System.out.println(i + ". slot" + i + ": Empty");
                } else
                    System.out.println(i + ". slot" + i + ": " + friend.getHand().getCards().get(i - 1).getName());
            }catch (Exception e){
                System.out.println(i + ". slot" + i + ": Empty");
            }
        }

        Scanner scanner = new Scanner(System.in);
        while(true) {
            switch (scanner.next()) {
                case "Exit":
                    System.out.println("no target was decided...");
                    return;
                case "Help":
                    System.out.println("1. Target #TargetNum : To cast the spell on the specified target\n" +
                            "2. Exit: To skip spell casting");
                    break;
                case "Target":
                    int target = scanner.nextInt();
//                    if (target == 1){
//                        cast(friend.getCommander());
//                    }else{
                    try{
                        Card card = friend.getHand().getCards().get(target);
                        if (card instanceof Spell){
                            friend.getSpellField().add((Spell) card, -1);
                        }else {
                            friend.getMonsterField().add((Monster)card, -1);
                            ((Monster) card).enterField(enemy, friend);
                        }
                        friend.getHand().remove(card);
                    }catch (Exception e){
                        System.out.println("invalid target");
                    }
//                    }
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }
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