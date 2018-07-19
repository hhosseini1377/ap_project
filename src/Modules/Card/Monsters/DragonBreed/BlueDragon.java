package Modules.Card.Monsters.DragonBreed;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.SnapshotParameters;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;

import java.util.Scanner;

public class BlueDragon extends SpellCaster{

    public BlueDragon(){
        name = "Blue Dragon";
        HP = 800;
        initialHP = HP;
        AP = 1200;
        initialAP = AP;
        manaPoint = 5;
        gillCost = manaPoint * 500;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.DRAGON_BREED;
        isNimble = false;
        offenseType = true;

        cardView = new CardView(Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT * 3 / 12, cardImage, this, 0, 0, false);
        cardViewBig = new CardView(Graphics.SCREEN_WIDTH * 6 / 18, Graphics.SCREEN_HEIGHT * 9 /12, cardImage,this, 0, 0, true);
    }


    public String getSpellName() {
        String spellName = "Magical Fire";
        return spellName;
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
                if (enemy.getMonsterField().getMonsterCards().get(i - 1) == null) {
                    System.out.println(i + ". slot" + i + ": Empty");
                } else
                    System.out.println(i + ". slot" + i + ": " + enemy.getMonsterField().getMonsterCards().get(i - 1).getName());
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
                        Card card = enemy.getMonsterField().getMonsterCards().get(target);
                        enemy.getGraveYard().add(card);
                        ((Monster)card).die(enemy, friend);
                        enemy.getMonsterField().remove((Monster) card);
                        canCast = false;
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
    public String toString() {
        return super.toString();
    }

    @Override
    public String spellDetail() {
        String spellDetail = "Move an enemy monster card from field to graveyard";
        return spellDetail;
    }
}
