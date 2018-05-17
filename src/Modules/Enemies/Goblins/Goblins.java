package Modules.Enemies.Goblins;

import Modules.BattleGround.Deck;
import Modules.BattleGround.Fields.MonsterField;
import Modules.BattleGround.Fields.SpellField;
import Modules.BattleGround.GraveYard;
import Modules.BattleGround.Hand;
import Modules.Card.Card;
import Modules.Card.Monsters.Demonic.GoblinShaman;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.Normal;
import Modules.Card.Spell.Spell;
import Modules.Card.Spell.ThrowingKnives;
import Modules.Warrior.Warrior;

public class Goblins extends Warrior {
    private int prize = 10000;

    public int getPrize() {
        return prize;
    }

    public Goblins() {
        super();
        name = "Goblins";
        Normal goblinSumggler = new Normal("Goblin Smuggler", 600, 400, 2, false, true, "DEMONIC");
        hand.add(goblinSumggler);
        GoblinShaman goblinShaman = new GoblinShaman();
        ThrowingKnives throwingKnives = new ThrowingKnives();
        deck.add(goblinSumggler, 10);
        deck.add(goblinShaman, 5);
        deck.add(throwingKnives, 5);
    }

    private Card decideHandCard(Warrior enemy) {
        if (hand.getCards().size() == 0)
            return null;
        Card bestCard = null;
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
        //first you will decide to bring a monster to the field
        Card decidedCard = decideHandCard(enemy);
        while (manaPoint > 0 && decideHandCard(enemy) != null) {
            setManaPoint(getMaxManaPoint() - decidedCard.getManaPoint());
            this.getHand().remove(decidedCard);
            if (decidedCard instanceof Monster) {
                this.getMonsterField().add((Monster) decidedCard, -1);
            } else {
                this.getSpellField().add((Spell) decidedCard, -1);
            }
            decidedCard = decideHandCard(enemy);
        }

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
                    for (Monster monster : this.getMonsterField().getMonsterCards()) {
                        enemy.getCommander().decreaseHP(monster.getAP());
                        monster.decreaseHP(enemy.getCommander().getAP());
                    }
                }catch (Exception e){

                }
            }
        }
    }
}