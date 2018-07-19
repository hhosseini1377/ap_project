package Modules.Card.Spell;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Scanner;

public class FirstAidKit extends Spell{

    public FirstAidKit(){
        name = "First Aid Kit";
        manaPoint = 3;
        gillCost = 700 * manaPoint;
        spellDetail = "Increase HP of a selected friendly\n" +
                " monster or player by 500";
        spellType = SpellType.INSTANT;
        Image cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        CardView cardView = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,false);
        CardView cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);

    }

    public boolean canCast(){
        return canCast;
    }

    public void castSpell(Card monster){
        ((Monster) monster).increaseHP(500);
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public void castSpell() {
        if (!canCast){
            System.out.println("this card cannot cast anymore");
            return;
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
        System.out.println("\nList of targets:");
        System.out.println("1. commander\nMonster field:");
        for (int i = 1; i <= 5; i++){
            try {
                if (friend.getMonsterField().getMonsterCards().get(i - 1) == null) {
                    System.out.println((i + 1) + ". slot" + i + ": Empty");
                } else
                    System.out.println((i + 1) + ". slot" + i + ": " + friend.getMonsterField().getMonsterCards().get(i - 1).getName());
            }catch (Exception e){
                System.out.println((i + 1) + ". slot" + i + ": Empty");
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
                    if (target == 1){
                        castSpell(friend.getCommander());
                    }else{
                    try{
                        castSpell(friend.getMonsterField().getMonsterCards().get(target));
                    }catch (Exception e){
                        System.out.println("invalid target");
                    }
                    }
                    canCast = false;
                    return;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
