package Modules.ItemAndAmulet.Items;

import Control.GameControll.GameControl;
import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;

import java.io.IOException;

public class MysticHourglass extends Item{
    GameControl gameControl;

    public MysticHourglass(GameControl gameControl){
        this.gameControl = gameControl;
        gillCost = 10000;
        name = "Mystic Hourglass";
    }

    @Override
    public boolean equals(Item other) {
        return super.equals(other);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String spelldetail() {
        String detail = "returns the time to before the battle started";
        return detail;
    }

    @Override
    public void castSpell (Warrior player) {

    }

    public void castSpell() {
        try {
            gameControl.reloadGame();
        }catch (IOException e){
            System.out.println("problem in input and output when reloading");
        }
    }

}
