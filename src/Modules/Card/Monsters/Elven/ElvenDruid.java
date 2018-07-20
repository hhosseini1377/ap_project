package Modules.Card.Monsters.Elven;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Scanner;

public class ElvenDruid extends SpellCaster{

    public ElvenDruid(){
        name = "Elven Druid";
        HP = 1200;
        initialHP = 1200;
        AP = 600;
        initialAP = 600;
        manaPoint = 5;
        gillCost = manaPoint * 500;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.ELVEN;
        isNimble = false;
        offenseType = true;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.SCREEN_WIDTH * 3 / 18,Graphics.SCREEN_HEIGHT * 5 / 12,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);
    }

    public String getSpellName() {
        String spellName = "Rejuvenation";
        return spellName;
    }

    protected void cast(Card card){
        ((Monster) card).increaseAP(300);
        ((Monster) card).increaseHP(500);
        canCast = false;
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    public void castSpell(Warrior enemy, Warrior friend){
        if (!CanCast()) {
            System.out.println("this monster cannot cast now");
            return;
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
        System.out.println("\nList of targets:");
//        System.out.println("1. Enemy commander\nMonster field:");
        for (int i = 1; i <= 5; i++) {
            try {
                if (friend.getMonsterField().getMonsterCards().get(i - 1) == null) {
                    System.out.println(i + ". slot" + i + ": Empty");
                } else
                    System.out.println(i + ". slot" + i + ": " + friend.getMonsterField().getMonsterCards().get(i - 1).getName());
            } catch (Exception e) {
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
                        cast(friend.getMonsterField().getMonsterCards().get(target));
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
    public String spellDetail() {
        String spellDetail = " Increase a selected friendly monster card’s HP by 500 and AP by 300";
        return spellDetail;
    }
}
