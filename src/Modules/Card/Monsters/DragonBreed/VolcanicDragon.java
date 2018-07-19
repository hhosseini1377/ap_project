package Modules.Card.Monsters.DragonBreed;

import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;

import java.util.Scanner;

public class VolcanicDragon extends SpellCaster {

    public VolcanicDragon() {
        name = "Volcanic Dragon";
        HP = 2500;
        initialHP = HP;
        AP = 700;
        initialAP = AP;
        manaPoint = 8;
        gillCost = manaPoint * 500;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.DRAGON_BREED;
        isNimble = false;
        offenseType = false;
        cardImage = new Image("Files/Images/Red Dragon.jpg");
        cardView = new CardView(Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT * 3 / 12, cardImage, this,0, 0, false);
    }

    public String getSpellName() {
        String spellName = "Lava Spit";
        return spellName;
    }

    private void cast(Monster monster) {
        monster.decreaseAP(500);
        monster.decreaseHP(500);
        canCast = false;
    }

    public void castSpell(Warrior enemy, Warrior friend) {
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
        while (true) {
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
                    try {
                        cast(enemy.getMonsterField().getMonsterCards().get(target));
                        canCast = false;
                    } catch (Exception e) {
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
        String spellDetail = "Deal 500 damage to an enemy" +
                " monster card and reduce its AP by 500";
        return spellDetail;
    }

}
