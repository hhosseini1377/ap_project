package Modules.CustomCard;

import Modules.Warrior.Warrior;

import java.util.Random;

public class SpellChangeRandomFriendly extends Spell {
    int APChange, HPChange;

    public SpellChangeRandomFriendly(int APChange, int HPChange, String spellName, String spellDetail) {
        this.APChange = APChange;
        this.HPChange = HPChange;
        setSpellDetails(spellDetail);
        setSpellName(spellName);
    }

    @Override
    public void doSpell(Warrior friendly, Warrior enemy) {
        Random random = new Random();
        int randomNumber;
        randomNumber = random.nextInt(5);
        while(true){
            if (friendly.getMonsterField().getMonsterCards().get(randomNumber) != null){
                friendly.getMonsterField().getMonsterCards().get(randomNumber).increaseHP(HPChange);
                friendly.getMonsterField().getMonsterCards().get(randomNumber).increaseAP(APChange);
            }
        }
    }
}
