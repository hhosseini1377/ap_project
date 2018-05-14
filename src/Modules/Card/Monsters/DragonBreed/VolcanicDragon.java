package Modules.Card.Monsters.DragonBreed;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Warrior.Warrior;

import java.util.Scanner;

public class VolcanicDragon extends SpellCaster {
    private String spellName = "Lava Spit";
    private String spellDetail = "Deal 500 damage to an enemy" +
            " monster card and reduce its AP by 500";

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
    }

    public String getSpellName() {
        return spellName;
    }

    private void cast(Monster monster) {
        monster.decreaseAP(500);
        monster.decreaseHP(500);
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
            if (enemy.getMonsterField().getMonsterCards().get(i - 1) == null) {
                System.out.println(i + ". slot" + i + ": Empty");
            } else
                System.out.println(i + ". slot" + i + ": " + enemy.getMonsterField().getMonsterCards().get(i - 1).getName());
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
        return spellDetail;
    }

}
