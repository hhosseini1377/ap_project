package Modules.Card.Monsters.Elven;

import Modules.Card.Card;
import Modules.Card.Monsters.General;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Spell.Spell;
import Modules.Warrior.Warrior;

public class NobleElf extends General{
    private String willName = "Elven Enliven";
    private String willDetail = "Increase a random friendly Elven monster card on the" +
            " field’s HP by 800 and AP by 600";
    private String battleCryName = "Purge";
    private String battleCryDetail = "Remove all enemy spell cards on the field and move them to hand";

    public NobleElf(){
        name = "Noble Elf";
        initialHP = 2000;
        initialAP = 2400;
        HP = initialHP;
        AP = initialAP;
        manaPoint = 9;
        gillCost = manaPoint * 700;
        isNimble = false;
        offenseType = true;
        monsterKind = MonsterKind.GENERAL;
        monsterTribe = MonsterTribe.ELVEN;
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        for (int i = 0; i < friend.getMonsterField().getMonsterCards().size(); i++) {
            if (friend.getMonsterField().getMonsterCards().get(i).getMonsterTribe().equals(MonsterTribe.ELVEN)) {
                friend.getMonsterField().getMonsterCards().get(i).increaseHP(800);
                friend.getMonsterField().getMonsterCards().get(i).increaseAP(600);
            }
        }
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        for (Card card:enemy.getSpellField().getSpellCards()) {
            enemy.getHand().add(card);
            enemy.getSpellField().remove((Spell)card);
        }
    }

    @Override
    public String willDetail() {
        return willDetail;
    }

    @Override
    public String battleCryDetail() {
        return battleCryDetail;
    }

    public String getWillName() {
        return willName;
    }

    public String getBattleCryName() {
        return battleCryName;
    }
}
