package Modules.Card.Monsters.Demonic;

import Modules.BattleGround.Fields.MonsterField;
import Modules.BattleGround.Hand;
import Modules.Card.Card;
import Modules.Card.Monsters.General;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Warrior.Warrior;

import java.util.ArrayList;

public class VampirePrince extends General{
    private String willName = "Fear";
    private String battleCryName = "Darkness";

    public VampirePrince(){
        name = "VampirePrince";
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
                enemy.getMonsterField().remove((Monster) enemy.getMonsterField().getMonsterCards().get(random));
            }catch (Exception e){
                System.out.println("Monster field does not have 2 cards");
            }
        }
    }

    public String getWillName() {
        return willName;
    }

    public String getBattleCryName() {
        return battleCryName;
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        enemy.getMonsterField().getMonsterCards().forEach(card -> ((Monster) card).decreaseAP(200));
        friend.getMonsterField().getMonsterCards().forEach(card -> ((Monster) card).increaseAP(200));
    }
}
