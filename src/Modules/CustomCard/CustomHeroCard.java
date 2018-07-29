package Modules.CustomCard;

import Modules.Card.Monsters.Hero;
import Modules.Warrior.Warrior;

public class CustomHeroCard extends Hero {
    Spell battleCry;
    Spell spell;
    Spell will;

    public CustomHeroCard(String name, int AP, int HP, int manaPoint, int gilPoint ,Spell battleCry, Spell spell, Spell will) {
        this.name = name;
        this.HP = HP;
        this.AP = AP;
        this.manaPoint = manaPoint;
        this.gillCost = gilPoint;
        this.battleCry = battleCry;
        this.spell = spell;
        this.will = will;
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
}
