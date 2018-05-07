package Modules.Warrior;

import Modules.BattleGround.Deck;
import Modules.BattleGround.Fields.MonsterField;
import Modules.BattleGround.Fields.SpellField;
import Modules.BattleGround.GraveYard;
import Modules.BattleGround.Hand;

public class Warrior {
    private Deck deck;
    private Hand hand;
    private GraveYard graveYard;
    private MonsterField monsterField;
    private SpellField spellField;
    private BackPack backPack;

    public Warrior(Deck deck) {
        this.deck = deck;
        hand = new Hand();
        graveYard = new GraveYard();
        monsterField = new MonsterField();
        spellField = new SpellField();
    }

    public Warrior(){
        hand = new Hand();
        graveYard = new GraveYard();
        monsterField = new MonsterField();
        spellField = new SpellField();
    }

    public Deck getDeck() {
        return deck;
    }

    public Hand getHand() {
        return hand;
    }

    public GraveYard getGraveYard() {
        return graveYard;
    }

    public MonsterField getMonsterField() {
        return monsterField;
    }

    public SpellField getSpellField() {
        return spellField;
    }

    public BackPack getBackPack() {
        return backPack;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void setGraveYard(GraveYard graveYard) {
        this.graveYard = graveYard;
    }

    public void setMonsterField(MonsterField monsterField) {
        this.monsterField = monsterField;
    }

    public void setSpellField(SpellField spellField) {
        this.spellField = spellField;
    }

    public void setBackPack(BackPack backPack) {
        this.backPack = backPack;
    }
}
