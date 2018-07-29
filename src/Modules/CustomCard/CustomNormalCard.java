package Modules.CustomCard;

import Modules.Card.Monsters.Monster;

public class CustomNormalCard extends Monster {
    public CustomNormalCard(String name, int AP, int HP, int manaPoint, int gilPoint) {
        this.name = name;
        this.HP = HP;
        this.AP = AP;
        this.manaPoint = manaPoint;
        this.gillCost = gilPoint;
    }
}
