package Modules.CustomCard;

import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.SpellCaster;
import Modules.Warrior.Warrior;

public class CustomSpellCasterCard extends SpellCaster {

    Spell spell;

    public CustomSpellCasterCard(String name, int AP, int HP, int manaPoint, int gilPoint) {
        this.name = name;
        this.HP = HP;
        this.AP = AP;
        this.manaPoint = manaPoint;
        this.gillCost = gilPoint;
    }

    public Spell getSpell () {
        return spell;
    }

    public void setSpell (Spell spell) {
        this.spell = spell;
    }

    @Override
    public void castSpell (Warrior enemy, Warrior friend) {
        spell.doSpell(enemy, friend);
    }
}
