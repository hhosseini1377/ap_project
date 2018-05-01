package Modules.Card.Monsters;

public class Normal extends Monster {
    public Normal(String name, int AP, int HP, boolean isNimble,boolean offenseType,
                   MonsterKind monsterKind, MonsterTribe tribe) {
        this.name = name;
        this.AP = AP;
        this.initialAP = AP;
        this.initialHP = HP;
        this.HP = HP;
        this.offenseType = offenseType;
        this.isNimble = isNimble;
        this.monsterKind = monsterKind;
        this.monsterTribe = tribe;
    }
}
