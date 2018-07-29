package Modules.CustomCard;

import Modules.Card.Monsters.Hero;
import Modules.Warrior.Warrior;

public class CustomHeroCard extends Hero {
    private Spell battleCry;
    private Spell spell;
    private Spell will;


    public Spell getBattleCry() {
        return battleCry;
    }

    public void setBattleCry(Spell battleCry) {
        this.battleCry = battleCry;
    }

    public Spell getSpell() {
        return spell;
    }

    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    public Spell getWill() {
        return will;
    }

    public void setWill(Spell will) {
        this.will = will;
    }

    public CustomHeroCard(String name, int AP, int HP, int manaPoint, int gilPoint) {
        this.name = name;
        this.HP = HP;
        this.AP = AP;
        this.manaPoint = manaPoint;
        this.gillCost = gilPoint;
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend) {
        spell.doSpell(friend, enemy);
        setCanCast(false);
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        will.doSpell(friend, enemy);
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        battleCry.doSpell(friend, enemy);
    }

    @Override
    public String toString () {
        return "Name: " + name + "\nHP: " + HP + "\nAP: " + AP +
                "\nMP cost: " + manaPoint +
                "\nIs Nimble: " + isNimble + "\nIs Defensive: " + !offenseType +
                "\nSpell detail: " + spell.getSpellName() + ", " + spell.getSpellDetails() +
                "\nWill detail: " + will.getSpellName() + ", " + will.getSpellDetails() +
                "\nBattle cry detail: " + battleCry.getSpellName() + ", " + battleCry.getSpellDetails();
    }
}
