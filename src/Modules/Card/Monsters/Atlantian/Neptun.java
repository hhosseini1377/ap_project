package Modules.Card.Monsters.Atlantian;

import Modules.Card.Card;
import Modules.Card.Monsters.Hero;
import Modules.Card.Monsters.Monster;
import Modules.Card.Spell.Spell;
import Modules.Warrior.Warrior;

import java.util.Scanner;

public class Neptun extends Hero {
    public Neptun() {
        name = "Neptun, King of Atlantis";
        battleCryName = "Call to Arms";
        spellName = "Trident Beam";
        willName = "Ocean's Cry";
        battleCryDetail = "Select and move a card from hand to play field";
        willDetail = "Deal 400 damage to all enemy monster cards and player and" +
                " increase all friend Atlantian monster cards' AP by 500";
        spellDetail = "Deal 800 damage to three random enemy monster cards or player";
        HP = 2200;
        AP = 2500;
        initialAP = AP;
        initialHP = HP;
        manaPoint = 10;
        isNimble = true;
        offenseType = true;
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend) {
        if (!canCast) {
            System.out.println("this warrior cannot cast a spell right now!");
            return;
        }
        for (int i = 0; i < 3; i++) {
            try {
                int random = (int) (Math.random() * enemy.getMonsterField().getMonsterCards().size());
                enemy.getMonsterField().getMonsterCards().get(random).decreaseAP(800);
            } catch (Exception e) {
                System.out.println("there is not enough cards in monsterfield!");
                return;
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        friend.getMonsterField().getMonsterCards().forEach(card -> {
            card.increaseAP(200);
            card.increaseHP(500);
        });
        friend.getCommander().increaseHP(500);
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        if (!CanCast()) {
            System.out.println("this monster cannot cast now");
            return;
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
        System.out.println("\nList of targets:");
//        System.out.println("1. Enemy commander\nMonster field:");
        for (int i = 1; i <= 5; i++){
            if(friend.getHand().getCards().get(i-1) == null){
                System.out.println(i + ". slot" + i + ": Empty");
            }else
                System.out.println(i + ". slot" + i + ": " + friend.getHand().getCards().get(i-1).getName());
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
                        Card card = friend.getHand().getCards().get(target);
                        if (card instanceof Spell){
                            friend.getSpellField().add((Spell) card, -1);
                        }else {
                            friend.getMonsterField().add((Monster)card, -1);
                        }
                        friend.getHand().remove(card);
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
}