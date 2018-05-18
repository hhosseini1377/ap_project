package Modules.Card.Monsters.Demonic;

import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Warrior.Warrior;

import java.util.ArrayList;

public class EvilEye extends SpellCaster{
        private String spellName = "Evil Gaze";

        public EvilEye(){
            this.name = "Evil Eye";
            this.initialHP = 400;
            this.HP = 400;
            this.initialAP = 400;
            this.AP = 400;
            this.manaPoint = 6;
            gillCost = manaPoint * 500;
            this.monsterKind = MonsterKind.SPELL_CASTER;
            this.isNimble = false;
            this.offenseType = true;
            monsterTribe = MonsterTribe.DEMONIC;
        }

        @Override
        public void castSpell(Warrior enemy, Warrior friend){
            if (CanCast()) {
                enemy.getMonsterField().getMonsterCards().forEach(card -> card.decreaseHP(800));
                System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
            }else
                System.out.println("this monster cannot cast now");
        }

        public String getSpellName() {
            return spellName;
        }

    @Override
    public void enterField(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has entered the field proudly!");
    }

    @Override
    public void die(Warrior enemy, Warrior friend) {
     super.die(enemy, friend);
    }

        public String spellDetail(){
            return "Deal 800 damage to all enemy monster cards and player";
        }
    }
