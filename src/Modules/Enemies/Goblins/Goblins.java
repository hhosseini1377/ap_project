package Modules.Enemies.Goblins;

import Modules.BattleGround.Deck;
import Modules.BattleGround.Fields.MonsterField;
import Modules.BattleGround.Fields.SpellField;
import Modules.BattleGround.GraveYard;
import Modules.BattleGround.Hand;
import Modules.Card.Card;
import Modules.Card.Monsters.Demonic.GoblinShaman;
import Modules.Card.Monsters.Normal;
import Modules.Card.Spell.ThrowingKnives;
import Modules.Warrior.Warrior;

public class Goblins extends Warrior {
    private Deck deck = new Deck();
    private Hand hand = new Hand();
    private GraveYard graveYard = new GraveYard();
    private MonsterField monsterField = new MonsterField();
    private SpellField spellField = new SpellField();
    private int prize = 10000;

    public int getPrize() {
        return prize;
    }

    public Goblins(){
        super();
        name = "Goblins";
        Normal goblinSumggler = new Normal("GoblinSmuggler",600, 400,2, false, true, "DEMONIC");
        GoblinShaman goblinShaman = new GoblinShaman();
        ThrowingKnives throwingKnives = new ThrowingKnives();
//        for (int i = 0; i < 10; i++)
            deck.add(goblinSumggler, 10);
//        for (int i = 0; i < 5; i++)
            deck.add(goblinShaman, 5);
//        for (int i = 0; i < 5; i++)
            deck.add(throwingKnives, 5);
    }

    public Card decideMove(MonsterField OpponentMonsterField, SpellField OpponentSpellField){
        return new Card();
    }
}
