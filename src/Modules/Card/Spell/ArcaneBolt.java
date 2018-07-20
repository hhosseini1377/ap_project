package Modules.Card.Spell;

import Modules.Card.Monsters.Monster;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;
import java.util.Scanner;

public class ArcaneBolt extends Spell {
    public ArcaneBolt(){
        name = "Arcane Bolt";
        manaPoint = 5;
        gillCost = 700 * manaPoint;
        spellDetail = "Deal 500 damage to enemy player\nand select and move an" +
                "enemy spell card\nfrom field to graveyard";
        spellType = SpellType.INSTANT;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.SCREEN_WIDTH * 3 / 18,Graphics.SCREEN_HEIGHT * 5 / 12,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.SCREEN_WIDTH * 6 / 18, Graphics.SCREEN_HEIGHT * 9 /12, cardImage,this, 0, 0, true);
    }

    public boolean canCast(){
        return canCast;
    }

    private void cast(Spell spell){
        enemy.getCommander().decreaseHP(500);
        enemy.getGraveYard().add(spell);
        enemy.getSpellField().getSpellCards().remove(spell);
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public void castSpell() {
        if(!canCast){
            System.out.println("this card cannot cast any more");
        }
        else{
            System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
            System.out.println("\nList of targets:");
            for(int i=1;i<=3;i++){
                if(enemy.getSpellField().getSpellCards().get(i-1) == null)
                    System.out.println(i + ". Slot" + i + ". Empty");
                else
                    System.out.println(i + ". Slot" + i +". " + enemy.getSpellField().getSpellCards().get(i-1).getName());
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
                                cast(enemy.getSpellField().getSpellCards().get(target-1));
                            }catch (Exception e){
                                System.out.println("invalid target");
                            }
                        canCast = false;
                        return;
                    default:
                        System.out.println("invalid input");
                        break;
                }
            }
        }
    }

    @Override
    public String spellDetail() {
        return super.spellDetail();
    }
}
