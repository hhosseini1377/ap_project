package Modules.Card.Monsters;

import Modules.Card.Card;
import Modules.Warrior.Warrior;

import java.util.ArrayList;

public abstract class General extends Monster{
    private String willName;
    private String battleCryName;

    public void will(Warrior enemy, Warrior friend){

    }
    public void battleCry(Warrior enemy, Warrior friend){

    }

    public void battleCry(Card card){

    }

    public String willDetail(){
        return "will detail";
    }

    public String battleCryDetail(){
        return "battle cry detail";
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nHP: " + HP + "\nAP: " + AP +
                "\nMP cost: " + manaPoint +
                "\nIs Nimble: " + isNimble + "\nIs Defensive: " + !offenseType +
                "\nMonster Kind: " + monsterKind + "\nTribe: " + monsterTribe +
                "\nWill detail: " + willName + ", " + willDetail() +
                "\nBattle cry detail: " + battleCryName + ", " + battleCryDetail();
    }
}
