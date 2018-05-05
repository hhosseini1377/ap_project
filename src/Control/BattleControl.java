package Control;

import Modules.Enemies.Goblins.Goblins;
import Modules.Enemies.Ogres.Ogres;
import Modules.User.User;
import Modules.Warrior.Warrior;

public class BattleControl {
    private int turn;
    private Warrior[] warrior;
    // TODO needs to add computer playing warrior as well

    public void startBattle(User user){
        warrior = new Warrior[2];
        if (!user.getDeck().isAcceptable()){
            System.out.println("deck not acceptable!!\nfix it and try again");
            return;
        }
        warrior[1] = new Warrior(user.getDeck());
        switch (user.getLevel()){
            case 1:
                warrior[0] = new Goblins();
                break;
            case 2:
                warrior[0] = new Ogres();
                break;
            case 3:
                warrior[0] = new Goblins();
                break;
            case 4:
                warrior[0] = new Goblins();
                break;
        }
        turn = 1;
        battle();
    }

    private void battle(){
        while (true){

        }
    }

    private void help(){

    }

    private void endBattle(){

    }
}
