package Modules.Card.Monsters.Demonic;

import Modules.Card.Monsters.General;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Warrior.Warrior;

public class VampirePrince extends General{

    public VampirePrince(){
        name = "Vampire Prince";
        initialHP = 2000;
        initialAP = 2200;
        HP = 2000;
        AP = 2200;
        manaPoint = 9;
        gillCost = manaPoint * 700;
        isNimble = false;
        offenseType = true;
        monsterKind = MonsterKind.GENERAL;
        monsterTribe = MonsterTribe.DEMONIC;
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        int random;
        for (int i = 0; i < 2; i++) {
            try {
                random = (int) (Math.random() * enemy.getMonsterField().getMonsterCards().size());
                enemy.getHand().add(enemy.getMonsterField().getMonsterCards().get(random));
                enemy.getMonsterField().remove(enemy.getMonsterField().getMonsterCards().get(random));
            }catch (Exception e){
                System.out.println("Monster field does not have 2 cards");
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.willDetail());
    }

    public String getWillName() {
        String willName = "Darkness";
        return willName;
    }

    public String getBattleCryName() {
        String battleCryName = "Fear";
        return battleCryName;
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        enemy.getMonsterField().getMonsterCards().forEach(card -> card.decreaseAP(200));
        friend.getMonsterField().getMonsterCards().forEach(card -> card.increaseAP(200));
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
        String willDetail = "Reduce all enemy monsters' AP by 200 and increase all friendly monsters' AP by 200";
        return willDetail;
    }

    @Override
    public String battleCryDetail() {
        String battleCryDetail = "Return two random enemy cards from field to hand";
        return battleCryDetail;
    }
}
