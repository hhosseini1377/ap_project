package Modules.Card.Monsters;

import Modules.Card.Card;
import Modules.Warrior.Warrior;

import java.util.ArrayList;

public abstract class General extends Monster{
    public void will(Warrior enemy, Warrior friend){

    }
    public void battleCry(Warrior enemy, Warrior friend){

    }

    public String willDetail(){
        return "will detail";
    }

    public String battleCryDetail(){
        return "battle cry detail";
    }
}
