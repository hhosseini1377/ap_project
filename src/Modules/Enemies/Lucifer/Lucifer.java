package Modules.Enemies.Lucifer;

import Modules.BattleGround.Deck;
import Modules.BattleGround.Fields.MonsterField;
import Modules.BattleGround.Fields.SpellField;
import Modules.BattleGround.GraveYard;
import Modules.BattleGround.Hand;
import Modules.Card.Card;
import Modules.Card.Monsters.Normal;
import Modules.Card.Spell.*;
import Modules.Warrior.Warrior;

public class Lucifer extends Warrior{
    private Deck deck;
    private Hand hand;
    private GraveYard graveYard;
    private MonsterField monsterField;
    private SpellField spellField;
    private int prize = 30000;

    public int getPrize() {
        return prize;
    }

    public Lucifer(){
        super();
        deck = new Deck();
        Normal imp = new Normal("Imp", 300, 500, 2, false, true, "DEMONIC");

    }

    public Card decideMove(MonsterField OpponentMonsterField, SpellField OpponentSpellField){

    }
}
