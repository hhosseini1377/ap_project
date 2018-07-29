package Modules.CustomCard;


import Modules.Card.Spell.SpellType;
import Modules.Warrior.Warrior;

public class CustomSpellCard extends Modules.Card.Spell.Spell {
    private Spell spell = null;

    public CustomSpellCard( String name, int manaPoint, int gilCost, SpellType spellType) {
        this.name = name;
        this.manaPoint = manaPoint;
        this.gillCost = gilCost;
        this.spellType = spellType;

    }

    public String getSpellDetails (){
        return spell.getSpellDetails();
    }

    public Spell getSpell() {
        return spell;
    }
    public void setSpell(Spell spell) {
        this.spell = spell;
    }

    @Override
    public void castSpell (){
        if (!canCast){

        }else {
            spell.doSpell(getFriend(), getEnemy());
            canCast = false;
        }
    }

    @Override
    public String toString () {
        return " Name: " + this.name + "\n MP cost: " + this.manaPoint +
                "\n Spell Name: " + spell.getSpellName() + "\n Details: " + spell.getSpellDetails();
    }
}
