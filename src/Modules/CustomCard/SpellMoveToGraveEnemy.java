package Modules.CustomCard;

import Modules.Warrior.Warrior;

import java.util.Random;

public class SpellMoveToGraveEnemy extends Spell{
    public SpellMoveToGraveEnemy(String name, String SpellDetail) {
        setSpellDetails(SpellDetail);
        setSpellName(name);
    }

    @Override
    public void doSpell(Warrior friendly, Warrior enemy) {
        Random random = new Random();
        int index = random.nextInt(enemy.getMonsterField().getMonsterCards().size());
        enemy.getGraveYard().add(enemy.getMonsterField().getMonsterCards().get(index));
        enemy.getMonsterField().getMonsterCards().remove(index);

    }
}
