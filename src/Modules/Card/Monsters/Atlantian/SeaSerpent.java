package Modules.Card.Monsters.Atlantian;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Warrior.Warrior;

import java.util.Scanner;

public class SeaSerpent extends SpellCaster{
    private String spellName = "Serpent's Bite";
    private String spellDetail = "Deal 1000 damage to an enemy monster card or player";

    public SeaSerpent(){
        name = "Sea Serpent";
        HP = 1800;
        initialHP = HP;
        AP = 200;
        initialAP = AP;
        manaPoint = 7;
        gillCost = manaPoint * 500;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.ATLANTIAN;
        isNimble = true;
        offenseType = true;
    }

    public String getSpellName() {
        return spellName;
    }

    public void castSpell(Warrior enemy, Warrior friend){
        if (!CanCast()) {
            System.out.println("this monster cannot cast now");
            return;
        }
        int random = (int)(enemy.getMonsterField().getMonsterCards().size() * Math.random());
        try{
            enemy.getMonsterField().getMonsterCards().get(random).decreaseHP(1000);
        }catch (Exception e){
            enemy.getCommander().decreaseHP(1000);
        }
    }

    @Override
    public String spellDetail() {
        return spellDetail;
    }
}
