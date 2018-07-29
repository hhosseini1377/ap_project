package Modules.CustomCard;

import Modules.Card.Monsters.Hero;
import Modules.Warrior.Warrior;

public class CustomHeroCard extends Hero {
    public CustomHeroCard() {

    }

    @Override
    public String battleCryDetail () {
        return super.battleCryDetail();
    }

    @Override
    public String willDetail () {
        return super.willDetail();
    }

    @Override
    public void will (Warrior enemy, Warrior friend) {

    }

    @Override
    public void battleCry (Warrior enemy, Warrior friend) {

    }

    @Override
    public void castSpell (Warrior enemy, Warrior friend) {
        super.castSpell(enemy, friend);
    }

    @Override
    public String toString () {
        return super.toString();
    }
}

