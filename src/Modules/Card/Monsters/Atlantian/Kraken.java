package Modules.Card.Monsters.Atlantian;

import Modules.Card.Card;
import Modules.Card.Monsters.General;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Card.Spell.Spell;
import Modules.Warrior.Warrior;

public class Kraken extends General{
    private String willName = "Titan's Fall";
    private String willDetail = "Deal 400 damage to all enemy monster cards and player";
    private String battleCryName = "Titan's Presence";
    private String battleCryDetail = " Return one random enemy monster card " +
            "from field to hand and reduce all" +
            "enemy monsters’ AP by 200";

    public Kraken(){
        name = "Kraken";
        initialHP = 1800;
        initialAP = 2200;
        HP = initialHP;
        AP = initialAP;
        manaPoint = 8;
        gillCost = manaPoint * 700;
        isNimble = false;
        offenseType = true;
        monsterKind = MonsterKind.GENERAL;
        monsterTribe = MonsterTribe.ATLANTIAN;
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        enemy.getMonsterField().getMonsterCards().forEach(card -> card.decreaseHP(400));
        enemy.getCommander().decreaseHP(400);
        System.out.println(this.getName() + " has cast a spell:\n" + this.willDetail());
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        int random = (int)(Math.random() * enemy.getMonsterField().getMonsterCards().size());
        try{
            enemy.getHand().add(enemy.getMonsterField().getMonsterCards().get(random));
            enemy.getMonsterField().remove(enemy.getMonsterField().getMonsterCards().get(random));
        }catch (Exception e){
            System.out.println("there was no monster in the field");
        }
        enemy.getMonsterField().getMonsterCards().forEach(card -> card.decreaseAP(200));
        System.out.println(this.getName() + " has cast a spell:\n" + this.battleCryDetail());
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
