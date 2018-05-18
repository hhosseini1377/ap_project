package Modules.Enemies.Lucifer;

import Modules.BattleGround.Deck;
import Modules.BattleGround.Fields.MonsterField;
import Modules.BattleGround.Fields.SpellField;
import Modules.BattleGround.GraveYard;
import Modules.BattleGround.Hand;
import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.Normal;
import Modules.Card.Spell.FirstAidKit;
import Modules.Card.Spell.Spell;
import Modules.Card.Spell.StrategicRetreat;
import Modules.Card.Spell.ThrowingKnives;
import Modules.Warrior.Warrior;

import java.util.Objects;

public class Lucifer extends Warrior{
    private Hand hand;
    private GraveYard graveYard;
    private MonsterField monsterField;
    private SpellField spellField;

    public int getPrize() {
        int prize = 30000;
        return prize;
    }

    public Lucifer(){
        super();
        Deck deck = new Deck ();
        Normal imp = new Normal("Imp", 300, 500, 2, false, true, "DEMONIC");

    }

    private Card decideHandCard(Warrior enemy) {
        if (hand.getCards().size() == 0)
            return null;
        Card bestCard = hand.getCards().get(0);
        for (Card card : hand.getCards()) {
            if (manaPoint >= card.getManaPoint()) {
                if (card instanceof Monster) {
                    if (!((Monster) card).isOffenseType()) {
                        bestCard = card;
                        break;
                    }
                }
            }
        }
        if (bestCard == null) {
            for (int i = 0; i < hand.getCards().size(); i++) {
                if (manaPoint >= hand.getCards().get(i).getManaPoint()) {
                    bestCard = hand.getCards().get(i);
                    break;
                }
            }
        }
        return bestCard;
    }

    public void makeMove(Warrior enemy) {

        if (this.getMonsterField().getAvailablePlaces() != 5) {
            if (enemy.getMonsterField().getAvailablePlaces() != 5) {
                //to cast spells of monsters
                for (Monster monster : this.getMonsterField().getMonsterCards()) {
                    if (monster.canCast()) {
                        monster.castSpell(enemy, this);
                    }
                }
                //if there are other monsters other than commander you will attack them
                while (this.getMonsterField().getAvailablePlaces() == 0 && enemy.getMonsterField().getAvailablePlaces() != 5) {
                    //if the slots are full you will destroy a monster of your own
                    Monster minimumAPMonster = this.getMonsterField().getMonsterCards().get(0);
                    for (Monster monster : this.getMonsterField().getMonsterCards()) {
                        if (monster.getAP() < minimumAPMonster.getAP() && monster.isOffenseType()) {
                            minimumAPMonster = monster;
                        }
                    }
                    //we find the monsters with least HP
                    Monster mostVulnerableEnemyMonster = this.getMonsterField().getMonsterCards().get(0);
                    for (Monster monster : enemy.getMonsterField().getMonsterCards()) {
                        if (monster.getHP() < mostVulnerableEnemyMonster.getHP()) {
                            mostVulnerableEnemyMonster = monster;
                        }
                    }
                    //now to attack
                    minimumAPMonster.decreaseHP(mostVulnerableEnemyMonster.getAP());
                    mostVulnerableEnemyMonster.decreaseHP(minimumAPMonster.getAP());
                }

                while (this.getMonsterField().getAvailablePlaces() < 4 && enemy.getMonsterField().getAvailablePlaces() != 5) {
                    Monster mostPowerfull = this.getMonsterField().getMonsterCards().get(0);
                    for (Monster monster : this.getMonsterField().getMonsterCards()) {
                        if (monster.getAP() > mostPowerfull.getAP()) {
                            mostPowerfull = monster;
                        }
                    }
                    Monster mostVulnerableEnemyMonster = this.getMonsterField().getMonsterCards().get(0);
                    for (Monster monster : enemy.getMonsterField().getMonsterCards()) {
                        if (monster.getHP() < mostVulnerableEnemyMonster.getHP()) {
                            mostVulnerableEnemyMonster = monster;
                        }
                    }
                    //now to attack
                    mostPowerfull.decreaseHP(mostVulnerableEnemyMonster.getAP());
                    mostVulnerableEnemyMonster.decreaseHP(mostPowerfull.getAP());
                }
            } else {
                //All of the monsters will attack the enemy commander
                try {
                    int size = this.getMonsterField().getMonsterCards().size();
                    for (int i = 0; i < size; i++){
                        enemy.getCommander().decreaseHP(this.getMonsterField().getMonsterCards().get(i).getAP());
                        this.getMonsterField().getMonsterCards().get(i).decreaseHP(enemy.getCommander().getAP());
                        if (this.getMonsterField().getMonsterCards().size() != size){
                            size = this.getMonsterField().getMonsterCards().size();
                            i--;
                        }
                    }
                }catch (Exception e){
                    System.out.println("an unknown problem");
                }
            }
        }

        //you will decide to bring a monster to the field
        Card decidedCard = decideHandCard(enemy);
        while (manaPoint > 0 && decideHandCard(enemy) != null && this.getMonsterField().getAvailablePlaces() > 0) {
            try {
                setManaPoint (getMaxManaPoint () - Objects.requireNonNull (decidedCard).getManaPoint ());
            }catch (NullPointerException e){
                System.out.println ("the chosen card is empty");
            }
            if (decidedCard instanceof Monster) {
                this.getMonsterField().add((Monster) decidedCard, -1);
                this.getHand().remove(decidedCard);
                ((Monster) decidedCard).enterField(enemy, this);
            } else {
                if (decidedCard instanceof StrategicRetreat) {
                    if (this.getMonsterField().getAvailablePlaces() != 5) {
                        Monster weakestMonster = this.getMonsterField().getMonsterCards().get(0);
                        for (Monster monster : this.getMonsterField().getMonsterCards()) {
                            if (monster.getAP() < weakestMonster.getAP()) {
                                weakestMonster = monster;
                            }
                        }
                        ((StrategicRetreat) decidedCard).castSpell(weakestMonster);
                        this.getHand().remove(decidedCard);
                    }
                }else if (decidedCard instanceof ThrowingKnives){
                    ((ThrowingKnives) decidedCard).castSpell(enemy.getCommander());
                    this.getHand().remove(decidedCard);
                }else if (decidedCard instanceof FirstAidKit){
                    ((FirstAidKit) decidedCard).castSpell(this.getCommander());
                    this.getHand().remove(decidedCard);
                }else {
                    this.getSpellField().add((Spell) decidedCard, -1);
                    this.getHand().remove(decidedCard);
                }
            }
            decidedCard = decideHandCard(enemy);
        }

    }
}
