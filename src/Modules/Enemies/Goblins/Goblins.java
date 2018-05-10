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
    private Deck deck;
    private Hand hand;
    private GraveYard graveYard;
    private MonsterField monsterField;
    private SpellField spellField;
    private int prize = 10000;

    public int getPrize() {
        return prize;
    }

    public Goblins(){
        super();
        deck = new Deck();
        Normal goblinSumggler = new Normal("GoblinSmuggler",600, 400,2, false, true, "DEMONIC");
        GoblinShaman goblinShaman = new GoblinShaman();
        ThrowingKnives throwingKnives = new ThrowingKnives();
        for (int i = 0; i < 10; i++)
            deck.add(goblinSumggler);
        for (int i = 0; i < 5; i++)
            deck.add(goblinShaman);
        for (int i = 0; i < 5; i++)
            deck.add(throwingKnives);
    }

    public Card decideMove(MonsterField OpponentMonsterField, SpellField OpponentSpellField){
        return new Card();
    }
}
