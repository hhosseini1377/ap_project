package Modules.Warrior;

import Modules.BattleGround.Deck;
import Modules.BattleGround.Fields.MonsterField;
import Modules.BattleGround.Fields.SpellField;
import Modules.BattleGround.GraveYard;
import Modules.BattleGround.Hand;
import Modules.Card.Card;
import Modules.Card.Commanders.ArslanTheHero;
import Modules.Card.Commanders.Commander;
import Modules.User.User;

public class Warrior {
    private User user;
    protected Commander commander;
    protected String name;
    protected Deck deck;
    protected Hand hand;
    protected GraveYard graveYard;
    protected MonsterField monsterField;
    protected SpellField spellField;
    protected BackPack backPack;
    protected int manaPoint = 0;
    protected int maxManaPoint;
    protected int winPrize;

    public Warrior(Deck deck, String name) {
        this.deck = new Deck();
        for (Card card: deck.getCards()){
            this.deck.add(card, 1);
        }
        hand = new Hand();
        graveYard = new GraveYard();
        monsterField = new MonsterField();
        spellField = new SpellField();
        this.name = name;
        commander = new ArslanTheHero();
        try {
            backPack.getAmulet().castSpell(this);
        }catch (NullPointerException e){
            System.out.println("No amulet equipped!");
        }
    }

    protected Warrior (){
        deck = new Deck();
        backPack = new BackPack();
        hand = new Hand();
        graveYard = new GraveYard();
        monsterField = new MonsterField();
        spellField = new SpellField();
        try {
            backPack.getAmulet().castSpell(this);
        }catch (NullPointerException e){
            System.out.println("No amulet equipped!");
        }
    }

    public int getWinPrize () {
        return winPrize;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public int getMaxManaPoint() {
        return maxManaPoint;
    }

    public void setManaPoint(int manaPoint) {
            this.manaPoint = manaPoint;
    }

    public void setMaxManaPoint(int maxManaPoint){
        if (maxManaPoint <= 12)
            this.maxManaPoint = maxManaPoint;
        else
            this.maxManaPoint = 12;
        this.manaPoint = this.maxManaPoint;
    }

    public Commander getCommander() {
        return commander;
    }

    public void setCommander(Commander commander) {
        this.commander = commander;
    }

    public String getName() {
        return name;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void makeMove(Warrior enemy){

    }

    public boolean hasCard(String name){
        return hand.hasCard(name) || graveYard.hasCard(name) || monsterField.hasCard(name) || spellField.hasCard(name);
    }
}
