package Modules.Enemies.Ogres;

import Modules.BattleGround.Deck;
import Modules.BattleGround.Fields.MonsterField;
import Modules.BattleGround.Fields.SpellField;
import Modules.BattleGround.GraveYard;
import Modules.BattleGround.Hand;
import Modules.Card.Card;
import Modules.Card.Monsters.Demonic.OgreMagi;
import Modules.Card.Monsters.Demonic.OgreWarchief;
import Modules.Card.Monsters.Normal;
import Modules.Card.Spell.FirstAidKit;
import Modules.Card.Spell.PoisonousCauldron;
import Modules.Card.Spell.ThrowingKnives;
import Modules.Warrior.Warrior;

public class Ogres extends Warrior {
    private Deck deck;
    private Hand hand;
    private GraveYard graveYard;
    private MonsterField monsterField;
    private SpellField spellField;
    private int prize = 20000;

    public int getPrize() {
        return prize;
    }

    public Ogres(){
        super();
        deck = new Deck();
        Normal ogreWarrior =  new Normal("OgreWarrior", 800, 500, 3, false, true, "DEMONIC");
        Normal ogreFrontliner = new Normal("OgreFrontliner", 1800, 600, 5, false, false, "DEMONIC");
        OgreMagi ogreMagi = new OgreMagi();
        OgreWarchief ogreWarchief  = new OgreWarchief();
        ThrowingKnives throwingKnives = new ThrowingKnives();
        PoisonousCauldron poisonousCauldron = new PoisonousCauldron();
        FirstAidKit firstAidKit = new FirstAidKit();
        for (int i = 0; i < 4; i++)
            deck.add(ogreFrontliner);
        for (int i = 0; i < 6; i++)
            deck.add(ogreWarrior);
        for (int i = 0; i < 5; i++)
            deck.add(throwingKnives);
        for (int i = 0; i < 2; i++)
            deck.add(ogreMagi);
        for (int i = 0; i < 3; i++)
            deck.add(firstAidKit);
        deck.add(ogreWarchief);
        deck.add(poisonousCauldron);
    }

    public Card decideMove(MonsterField OpponentMonsterField, SpellField OpponentSpellField){
        return new Card();
    }
}