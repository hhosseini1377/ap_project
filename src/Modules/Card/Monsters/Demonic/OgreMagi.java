package Modules.Card.Monsters.Demonic;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Warrior.Warrior;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class OgreMagi extends SpellCaster{
    private String spellName = "Enrage";

    public OgreMagi(){
        name = "Ogre Magi";
        HP = 1500;
        initialHP = 1500;
        AP = 800;
        initialAP = 800;
        manaPoint = 5;
        gillCost = manaPoint * 500;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.DEMONIC;
        isNimble = false;
        offenseType = true;
    }

    public String getSpellName() {
        return spellName;
    }

    public void castSpell(Card card){
        ((Monster) card).increaseAP(400);
        canCast = false;
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    public void castSpell(Warrior enemy, Warrior friend){
        if (!CanCast()) {
            System.out.println("this monster cannot cast now");
            return;
        }
        int random = (int)(Math.random() * friend.getMonsterField().getMonsterCards().size());
        castSpell(friend.getMonsterField().getMonsterCards().get(random));
    }

    @Override
    public String spellDetail() {
        return "Increase a friendly monster card's AP by 400";
    }
}
