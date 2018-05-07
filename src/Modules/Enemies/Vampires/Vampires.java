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
        for (int i = 0; i < 4; i++)
            deck.add(undead);
        for (int i = 0; i < 3; i++)
            deck.add(giantBat);
        for (int i = 0; i < 3; i++)
            deck.add(stoutUndead);
        for (int i = 0; i < 2; i++)
            deck.add(undeadMage);
        for (int i = 0; i < 3; i++)
            deck.add(bloodFeast);
        for (int i = 0; i < 2; i++)
            deck.add(firstAidKit);
        deck.add(vampireAcolyte);
        deck.add(vampirePrince);
        deck.add(warDrum);
        deck.add(poisonousCauldron);
        deck.add(healingWard);
        deck.add(greaterPurge);
    }

    public Card decideMove(MonsterField OpponentMonsterField, SpellField OpponentSpellField){

    }
}
