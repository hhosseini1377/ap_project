package Modules.Card.Monsters.Demonic;

import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Monsters.SpellCaster;
import Modules.Warrior.Warrior;

import java.util.ArrayList;

public class Necromancer extends SpellCaster{
    private String spellName = "Raise Dead";

    public Necromancer(){
        name = "Necromancer";
        HP = 1200;
        initialHP = 1200;
        AP = 1500;
        initialAP = 1500;
        manaPoint = 7;
        gillCost = manaPoint * 500;
        monsterKind = MonsterKind.SPELL_CASTER;
        monsterTribe = MonsterTribe.DEMONIC;
        isNimble = true;
        offenseType = true;
    }

    public String getSpellName() {
        return spellName;
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend){
        if (CanCast()) {
            int random = (int)(Math.random() * friend.getGraveYard().getDestroyedCards().size());
            Card card = friend.getGraveYard().getDestroyedCards().get(random);
            friend.getGraveYard().remove(card.getName());
            friend.getHand().add(card);
            canCast = false;

            System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
        }else
            System.out.println("this monster cannot cast now");
    }

    public String spellDetail(){
        return "Move a random card from your graveyard to your hand";
    }
}
