package Modules.Card.Monsters.Demonic;

import Modules.Card.Card;
import Modules.Card.Monsters.General;
import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;

import java.util.ArrayList;

public class OgreWarchief extends General{
    private String willName = "War Stomp";
    private String battleCryName = "Last Order";

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
    public void will(ArrayList<Card> cards) {
        cards.forEach(card -> ((Monster) card).increaseAP(300));
    }

    @Override
    public void battleCry(ArrayList<Card> cards) {
        cards.forEach(card -> ((Monster) card).decreaseHP(400));
    }
}
