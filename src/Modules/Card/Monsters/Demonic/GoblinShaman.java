package Modules.Card.Monsters.Demonic;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Warrior.Warrior;

import java.util.ArrayList;

public class GoblinShaman extends SpellCaster{
    private String spellName = "mend";
    private String spellDetail = "Increase a friendly monster card or player's HP by 400";

    public GoblinShaman(){
        this.name = "Goblin Shaman";
        this.initialHP = 1000;
        this.HP = 1000;
        this.initialAP = 700;
        this.AP = 700;
        this.manaPoint = 4;
        gillCost = manaPoint * 500;
        this.monsterKind = MonsterKind.SPELL_CASTER;
        this.isNimble = false;
        this.offenseType = true;
        monsterTribe = MonsterTribe.DEMONIC;
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend){
        if (CanCast()){
            int randomNumber = (int)(Math.random() * enemy.getMonsterField().getMonsterCards().size());
            enemy.getMonsterField().getMonsterCards().get(randomNumber).increaseHP(400);
            canCast = false;
            System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
        }else
            System.out.println("this monster cannot cast now");
    }

    public String getSpellName() {
        return spellName;
    }

    @Override
    public String spellDetail() {
        return spellDetail;
    }
}
