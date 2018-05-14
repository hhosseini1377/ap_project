package Modules.Card.Monsters;

public class Normal extends Monster {
    public Normal(String name, int AP, int HP, int manaPoint, boolean isNimble, boolean offenseType,
                  String tribe) {
        this.name = name;
        this.AP = AP;
        this.initialAP = AP;
        this.initialHP = HP;
        this.HP = HP;
        this.manaPoint = manaPoint;
        this.offenseType = offenseType;
        this.isNimble = isNimble;
        this.monsterKind = MonsterKind.NORMAL;
        if (tribe.equals("ELVEN"))
            this.monsterTribe = MonsterTribe.ELVEN;
        if (tribe.equals("DRAGON_BREED"))
            this.monsterTribe = MonsterTribe.DRAGON_BREED;
        if (tribe.equals("ATLANTIAN"))
            this.monsterTribe = MonsterTribe.ATLANTIAN;
        if (tribe.equals("DEMONIC"))
            this.monsterTribe = MonsterTribe.DEMONIC;
        this.gillCost = manaPoint * 300;
    }
}
