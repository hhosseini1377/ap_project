package Modules.Card.Monsters.Demonic;

import Modules.Card.Card;
import Modules.Card.Monsters.General;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Spell.Spell;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.util.Scanner;

public class DarkKnight extends General{
    public DarkKnight(){
    name = "Dark Knight";
    initialHP = 2500;
    initialAP = 2500;
    HP = initialHP;
    AP = initialAP;
    manaPoint = 8;
    gillCost = manaPoint * 700;
    isNimble = false;
    offenseType = true;
    monsterKind = MonsterKind.GENERAL;
    monsterTribe = MonsterTribe.DEMONIC;
        cardImage = new Image("Files/Images/CardImages/"+this.name+".jpg");
    cardView = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,false);
    cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);
}

    @Override
    public void will(Warrior enemy, Warrior friend) {
        friend.getCommander().increaseHP(1000);
        System.out.println(this.getName() + " has cast " + this.getWillName() + ": " + this.willDetail());
    }

    public String getWillName() {
        String willName = "Loyalty";
        return willName;
    }

    public String getBattleCryName() {
        String battleCryName = "Sacrifice";
        return battleCryName;
    }

    public void battleCry(Card card) {
        friend.getHand().remove(card);
        friend.getGraveYard().add(card);
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has cast " +this.getBattleCryName() + ": " + this.battleCryDetail());
        System.out.println("\nList of targets:");

        for (int i = 1; i <= 5; i++){
            try {
                if (friend.getHand().getCards().get(i - 1) == null) {
                    System.out.println(i + ". slot" + i + ": Empty ");
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
                    try{
                        Card card = friend.getHand().getCards().get(target);
                        battleCry(card);
                    }catch (Exception e){
                        System.out.println("invalid target");
                    }
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }
    }

    @Override
    public void enterField(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has entered the field!");
        battleCry(enemy, friend);
    }

    @Override
    public void die(Warrior enemy, Warrior friend) {
        super.die(enemy, friend);
        will(enemy, friend);
    }

    @Override
    public String willDetail() {
        String willDetail = "Heal your player for 1000 HP";
        return willDetail;
    }

    @Override
    public String battleCryDetail() {
        String battleCryDetail = "Select and move a card from hand to graveyard";
        return battleCryDetail;
    }
}
