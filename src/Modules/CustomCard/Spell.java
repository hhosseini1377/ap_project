package Modules.CustomCard;

import Modules.Warrior.Warrior;

public abstract class Spell{

    private boolean isUsed = false;
    private String spellName;
    private String SpellDetails = null;

    public boolean getIsUsed() {
        return isUsed;
    }
    public void setUsed(boolean used) {
        isUsed = used;
    }

    public void setSpellDetails (String spellDetails) {
        SpellDetails = spellDetails;
    }
    public String getSpellDetails () {
        return SpellDetails;
    }

    public void setSpellName (String name)
    {
        spellName = name;
    }
    public String getSpellName ()
    {
        return spellName;
    }

    public abstract void doSpell(Warrior friendly, Warrior enemy);

}
