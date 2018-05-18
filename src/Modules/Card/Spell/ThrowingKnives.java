package Modules.Card.Spell;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Warrior.Warrior;

import java.util.Scanner;

public class ThrowingKnives extends Spell{
    public ThrowingKnives(){
        name = "Throwing Knives";
        manaPoint = 3;
        gillCost = 700 * manaPoint;
        spellDetail = "Deal 500 damage to a " +
                "selected enemy monster card on the field or to enemy player";
        spellType = SpellType.INSTANT;
    }

    public boolean canCast(){
        return canCast;
    }

    @Override
    public void castSpell(Card card) {
        ((Monster) card).decreaseHP(500);
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public void castSpell() {
        if (!canCast) {
            System.out.println("this card cannot cast anymore");
            return;
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
        System.out.println("\nList of targets:");
        System.out.println("1. commander\nMonster field:");
        for (int i = 1; i <= 5; i++) {
            try {
                if (enemy.getMonsterField().getMonsterCards().get(i - 1) == null) {
                    System.out.println((i + 1) + ". slot" + i + ": Empty");
                } else
                    System.out.println((i + 1) + ". slot" + i + ": " + enemy.getMonsterField().getMonsterCards().get(i - 1).getName());
            } catch (Exception e) {
                System.out.println((i + 1) + ". slot" + i + ": Empty");
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
                    if (target == 1) {
                        castSpell(enemy.getCommander());
                    } else {
                        try {
                            castSpell(enemy.getMonsterField().getMonsterCards().get(target));
                        } catch (Exception e) {
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
