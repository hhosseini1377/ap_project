package Modules.Card.Monsters.DragonBreed;

import Modules.Card.Card;
import Modules.Card.Monsters.Hero;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Spell.Spell;
import Modules.Warrior.Warrior;

import java.util.Scanner;

public class Igneel extends Hero {
    public Igneel() {
        name = "Igneel, The Dragon King";
        battleCryName = "King's Grace";
        spellName = "King's Wing Slash";
        willName = "King's Wail";
        battleCryDetail = "Send all non-Hero monster cards on both sides of field to their graveyards";
        willDetail = "Decrease all enemy monster cards' AP by 400";
        spellDetail = "Deal 600 damage to all enemy monster cards and player";
        HP = 4000;
        AP = 800;
        initialAP = AP;
        initialHP = HP;
        manaPoint = 10;
        gillCost = 1000 * manaPoint;
        isNimble = false;
        offenseType = true;
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend) {
        if (!canCast) {
            System.out.println("this warrior cannot cast a spell right now!");
            return;
        }
        enemy.getMonsterField().getMonsterCards().forEach(card -> card.decreaseHP(600));
        enemy.getCommander().decreaseHP(600);
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        enemy.getMonsterField().getMonsterCards().forEach(card -> card.decreaseAP(400));
        System.out.println(this.getName() + " has cast a spell:\n" + this.willDetail());
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        for (Monster monster:enemy.getMonsterField().getMonsterCards()){
            if (monster.getMonsterKind() != MonsterKind.HERO){
                enemy.getGraveYard().add(monster);
                enemy.getMonsterField().remove(monster);
            }
        }
        for (Monster monster:friend.getMonsterField().getMonsterCards()){
            if (monster.getMonsterKind() != MonsterKind.HERO){
                friend.getGraveYard().add(monster);
                friend.getMonsterField().remove(monster);
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.battleCryDetail());
    }
}