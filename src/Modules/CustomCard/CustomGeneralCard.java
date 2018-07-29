package Modules.CustomCard;

import Modules.Card.Monsters.General;
import Modules.Warrior.Warrior;

public class CustomGeneralCard extends General {
    public Spell getBattleCry() {
        return battleCry;
    }

    public void setBattleCry(Spell battleCry) {
        this.battleCry = battleCry;
    }

    Spell battleCry;

    public Spell getWill() {
        return will;
    }

    public void setWill(Spell will) {
        this.will = will;
    }

    Spell will;

    public CustomGeneralCard(String name, int AP, int HP, int manaPoint, int gilPoint) {
        this.name = name;
        this.HP = HP;
        this.AP = AP;
        this.manaPoint = manaPoint;
        this.gillCost = gilPoint;
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        battleCry.doSpell(friend,enemy);
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        will.doSpell(friend, enemy);
    }
}
