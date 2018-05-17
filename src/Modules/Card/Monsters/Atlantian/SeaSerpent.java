package Modules.Card.Monsters.Atlantian;

import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Warrior.Warrior;

public class SeaSerpent extends SpellCaster{

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
        String spellName = "Serpent's Bite";
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
        canCast = false;
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public void enterField(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has entered the field proudly!");
    }

    @Override
    public void die(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has died mercilessly");
    }

    @Override
    public String spellDetail() {
        String spellDetail = "Deal 1000 damage to an enemy monster card or player";
        return spellDetail;
    }
}
