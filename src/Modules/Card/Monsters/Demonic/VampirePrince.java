package Modules.Card.Monsters.Demonic;

import Modules.BattleGround.Fields.MonsterField;
import Modules.BattleGround.Hand;
import Modules.Card.Card;
import Modules.Card.Monsters.General;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;

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

    public void will(ArrayList<Card> cards, MonsterField monsterField, Hand hand) {
        int random;
        for (int i = 0; i < 2; i++) {
            random = (int) (Math.random() * cards.size());
            monsterField.remove((Monster) cards.get(random));
            hand.add(cards.get(random));
            cards.remove(random);
        }
    }

    public String getWillName() {
        return willName;
    }

    public String getBattleCryName() {
        return battleCryName;
    }

    public void battleCry(ArrayList<Card> enemyCards, ArrayList<Card> friendlyCards) {
        enemyCards.forEach(card -> ((Monster) card).decreaseAP(200));
        friendlyCards.forEach(card -> ((Monster) card).increaseAP(200));
    }
}
