package Modules.CustomCard;

import Modules.Warrior.Warrior;

import java.util.Random;

public class SpellChangeRandomAmount extends Spell{
    int APMaxChange;
    int HPMaxChange;

    public SpellChangeRandomAmount(int APMaxChange, int HPMaxChange, String SpellName, String SpellDetail) {
        this.APMaxChange = APMaxChange;
        this.HPMaxChange = HPMaxChange;
        setSpellName(SpellName);
        setSpellDetails(SpellDetail);
    }


    @Override
    public void doSpell(Warrior friendly, Warrior enemy) {

        Random random = new Random();
        int HPChange  = random.nextInt(HPMaxChange + 1);
        int APChange = random.nextInt(APMaxChange + 1);

        for (int i = 0; i < friendly.getMonsterField().getMonsterCards().size(); i++) {
            friendly.getMonsterField().getMonsterCards().get(i).increaseHP(HPChange);
            friendly.getMonsterField().getMonsterCards().get(i).increaseAP(APChange);
        }
    }
}
