package Modules.Card.Monsters.Atlantian;

import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Card.Spell.Spell;
import Modules.Warrior.Warrior;

public class NagaSiren extends SpellCaster{
    private String spellName = "Song of Siren";
    private String spellDetail = "Increase HP of all friendly monster cards by 300 and their AP by 200";

    public NagaSiren(){
        name = "Naga Siren";
        HP = 1200;
        initialHP = HP;
        AP = 600;
        initialAP = AP;
        manaPoint = 6;
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
        friend.getMonsterField().getMonsterCards().forEach(card -> card.increaseHP(300));
        friend.getMonsterField().getMonsterCards().forEach(card -> card.increaseAP(200));
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public String spellDetail() {
        return spellDetail;
    }
}
