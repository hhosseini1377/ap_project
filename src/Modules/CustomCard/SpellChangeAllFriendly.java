package Modules.CustomCard;

import Modules.Warrior.Warrior;

public class SpellChangeAllFriendly extends Spell {
    int APChange, HPChange;

    public SpellChangeAllFriendly(int APChange, int HPChange, String spellName, String spellDetail) {
        this.APChange = APChange;
        this.HPChange = HPChange;
        setSpellName(spellName);
        setSpellDetails(spellDetail);
    }

    @Override
    public void doSpell (Warrior friendly, Warrior enemy){
        for (int i = 0; i < friendly.getMonsterField().getMonsterCards().size(); i++) {
                friendly.getMonsterField().getMonsterCards().get(i).increaseHP(HPChange);
                friendly.getMonsterField().getMonsterCards().get(i).increaseAP(APChange);
        }
    }
}
