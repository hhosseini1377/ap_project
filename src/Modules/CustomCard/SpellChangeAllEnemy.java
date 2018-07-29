package Modules.CustomCard;

import Modules.Warrior.Warrior;

public class SpellChangeAllEnemy extends Spell {
    int APChange, HPChange;

    public SpellChangeAllEnemy(int APChange, int HPChange, String spellName, String spellDetail) {
        this.APChange = APChange;
        this.HPChange = HPChange;
        setSpellDetails(spellDetail);
        setSpellName(spellName);
    }

    @Override
    public void doSpell(Warrior friendly, Warrior enemy) {
        for (int i = 0; i < 5; i++) {
            if (enemy.getMonsterField().getMonsterCards().get(i) != null){
                enemy.getMonsterField().getMonsterCards().get(i).increaseHP(HPChange);
                enemy.getMonsterField().getMonsterCards().get(i).increaseAP(APChange);
            }
        }
    }
}
