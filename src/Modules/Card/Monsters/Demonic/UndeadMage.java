package Modules.Card.Monsters.Demonic;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Warrior.Warrior;

import java.util.ArrayList;
import java.util.Scanner;

public class UndeadMage extends SpellCaster{
    private String spellName = "Curse";

    public UndeadMage(){
        name = "Undead Mage";
        HP = 800;
        initialHP = 800;
        AP = 1000;
        initialAP = 1000;
        manaPoint = 6;
        gillCost = manaPoint * 500 ;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.DEMONIC;
        isNimble = false;
        offenseType = true;
    }

    public String getSpellName() {
        return spellName;
    }

    @Override
    protected void cast(Card card) {
        ((Monster)card).decreaseAP(500);
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend){
        if (!CanCast()) {
            System.out.println("this monster cannot cast now");
            return;
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
        System.out.println("\nList of targets:");
        System.out.println("1. Enemy commander\nMonster field:");
        for (int i = 1; i <= 5; i++){
            if(enemy.getMonsterField().getMonsterCards().get(i-1) == null){
                System.out.println(i + ". slot" + i + ": Empty");
            }else
                System.out.println(i + ". slot" + i + ": " + enemy.getMonsterField().getMonsterCards().get(i-1).getName());
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
//                        cast(enemy.getCommander());
//                    }else{
                    try{
                        cast(enemy.getMonsterField().getMonsterCards().get(target));
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


    public String spellDetail(){
        return "Reduce an enemy monster card's AP by 500";
    }
}
