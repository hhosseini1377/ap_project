package Modules.Card.Monsters.Demonic;

import Modules.Card.Card;
import Modules.Card.Monsters.General;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Warrior.Warrior;

import java.util.ArrayList;

public class OgreWarchief extends General{
    private String willName = "Last Order";
    private String willDetail = "Increase all friendly monsters by 300";
    private String battleCryName = "War Stomp";
    private String battleCryDetail = "Deal 400 damage to all enemy monsters and player";

    public OgreWarchief(){
        name = "Ogre Warchief";
        initialAP = 1500;
        initialHP = 2500;
        HP = 2500;
        AP = 1500;
        manaPoint = 7;
        gillCost = manaPoint * 700;
        isNimble = false;
        offenseType = true;
        monsterKind = MonsterKind.GENERAL;
        monsterTribe = MonsterTribe.DEMONIC;
    }

    public String getWillName() {
        return willName;
    }

    public String getBattleCryName() {
        return battleCryName;
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        enemy.getMonsterField().getMonsterCards().
                forEach(card -> ((Monster) card).increaseAP(300));
        System.out.println(this.getName() + " has cast a spell:\n" + this.willDetail());
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        enemy.getMonsterField().getMonsterCards().
                forEach(card -> ((Monster) card).decreaseHP(400));
        enemy.getCommander().decreaseHP(400);
        System.out.println(this.getName() + " has cast a spell:\n" + this.battleCryDetail());
    }

    @Override
    public void enterField(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has entered the field proudly!");
        battleCry(enemy, friend);
    }

    @Override
    public void die(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has died mercilessly");
        will(enemy, friend);
    }

    @Override
    public String willDetail() {
        return this.willDetail;
    }

    @Override
    public String battleCryDetail() {
        return this.battleCryDetail;
    }
}
