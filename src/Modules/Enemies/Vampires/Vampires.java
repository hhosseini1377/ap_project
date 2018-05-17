package Modules.Enemies.Vampires;

import Modules.BattleGround.Deck;
import Modules.BattleGround.Fields.MonsterField;
import Modules.BattleGround.Fields.SpellField;
import Modules.BattleGround.GraveYard;
import Modules.BattleGround.Hand;
import Modules.Card.Card;
import Modules.Card.Monsters.Demonic.UndeadMage;
import Modules.Card.Monsters.Demonic.VampireAcolyte;
import Modules.Card.Monsters.Demonic.VampirePrince;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.Normal;
import Modules.Card.Spell.*;
import Modules.Warrior.Warrior;

public class Vampires extends Warrior {
    private Deck deck;
    private Hand hand;
    private GraveYard graveYard;
    private MonsterField monsterField;
    private SpellField spellField;
    private int prize = 30000;

    public int getPrize() {
        return prize;
    }

    public Vampires(){
        super();
        deck = new Deck();
        PoisonousCauldron poisonousCauldron = new PoisonousCauldron();
        FirstAidKit firstAidKit = new FirstAidKit();
        Normal stoutUndead = new Normal("StoutUndead", 1200, 600, 4, false, false, "DEMONIC");
        Normal undead = new Normal("Undead", 200, 400, 1, false, true, "DEMONIC");
        Normal giantBat = new Normal("GiantBat", 500, 900, 3, true, true, "DEMONIC");
        UndeadMage undeadMage = new UndeadMage();
        VampireAcolyte vampireAcolyte = new VampireAcolyte();
        VampirePrince vampirePrince = new VampirePrince();
        BloodFeast bloodFeast = new BloodFeast();
        GreaterPurge greaterPurge = new GreaterPurge();
        HealingWard healingWard = new HealingWard();
        WarDrum warDrum = new WarDrum();
        deck.add(undead, 4);
        deck.add(giantBat, 3);
        deck.add(stoutUndead, 3);
        deck.add(undeadMage, 2);
        deck.add(bloodFeast, 3);
        deck.add(firstAidKit, 2);
        deck.add(vampireAcolyte, 1);
        deck.add(vampirePrince, 1);
        deck.add(warDrum, 1);
        deck.add(poisonousCauldron, 1);
        deck.add(healingWard, 1);
        deck.add(greaterPurge, 1);
    }

    public Card decideMove(MonsterField OpponentMonsterField, SpellField OpponentSpellField){
        Card bestCard = null;
        for (Card card : hand.getCards()){
            if (manaPoint >= card.getManaPoint()) {
                if (card instanceof Monster){
                    if(!((Monster)card).isOffenseType()){
                        bestCard = card;
                        break;
                    }else{
                        bestCard = card;
                    }
                }else{
                    bestCard = card;
                }
            }
        }
        return bestCard;
    }
}
