package Modules.Card.Monsters.Demonic;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Warrior.Warrior;

import java.util.ArrayList;
import java.util.Scanner;

public class OgreMagi extends SpellCaster{
    private String spellName = "Enrage";

    public OgreMagi(){
        name = "Ogre Magi";
        HP = 1500;
        initialHP = 1500;
        AP = 800;
        initialAP = 800;
        manaPoint = 5;
        gillCost = manaPoint * 500;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.DEMONIC;
        isNimble = false;
        offenseType = true;
    }

    public String getSpellName() {
        return spellName;
    }

    protected void cast(Card card){
        ((Monster) card).increaseAP(400);
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
        for (int i = 1; i <= 5; i++){
            if(friend.getMonsterField().getMonsterCards().get(i-1) == null){
                System.out.println(i + ". slot" + i + ": Empty");
            }else
                System.out.println(i + ". slot" + i + ": " + friend.getMonsterField().getMonsterCards().get(i-1).getName());
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
        return "Increase a friendly monster card's AP by 400";
    }
}
