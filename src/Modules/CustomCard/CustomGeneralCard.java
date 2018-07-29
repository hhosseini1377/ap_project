package Modules.CustomCard;

import Modules.Card.Monsters.General;
import Modules.Warrior.Warrior;

public class CustomGeneralCard extends General {
    Spell battleCry;
    Spell will;

    public CustomGeneralCard(String name, int AP, int HP, int manaPoint, int gilPoint, Spell battleCry, Spell will) {
        this.name = name;
        this.HP = HP;
        this.AP = AP;
        this.manaPoint = manaPoint;
        this.gillCost = gilPoint;
        this.battleCry = battleCry;
        this.will = will;
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
