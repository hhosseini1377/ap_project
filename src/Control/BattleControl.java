package Control;

import Modules.Enemies.Goblins.Goblins;
import Modules.Enemies.Ogres.Ogres;
import Modules.User.User;
import Modules.Warrior.Warrior;

import java.util.Scanner;

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
        turn = 0;
        System.out.println("Battle against" + warrior[0].getName() + "started!");
        battle();
    }

    private void battle(){
        //randomly starting the game
        int player = (int)(Math.random() * 2);
        String action;
//        String previousAction = null;
        Scanner scan = new Scanner(System.in);
        System.out.println(warrior[player].getName() + "starts the battle");

        //giving 5 cards to each combatant
        for (int i = 0; i < 5; i++) {
            warrior[player].getHand().add(warrior[player].getDeck().takeCard());
            warrior[(player + 1) % 2].getHand().add(warrior[player].getDeck().takeCard());
        }
        System.out.print("player drew ");
        for (int i = 0; i < 4; i++)
            System.out.print(warrior[1].getHand().getCards().get(i) + ", ");
        System.out.println(warrior[1].getHand().getCards().get(4));

        while (true){
            action = scan.next();
            switch (action){
                case "Help":
                    help();
                    break;
                case "Exit":
                    endBattle();
                    break;
                case "Use":
            }
        }
    }

    private void useCard(int slotNum){

    }


    private void help(){
        System.out.println("1.\n" +
                " Use # SlotNum : To use a specific card which is on the Monster Field\n" +
                "2.\n" +
                " Set HandIndex to # SlotNum : To set a card which is on the hand , in the field\n" +
                "3.\n" +
                " View Hand: To view the cards in your hand\n" +
                "4.\n" +
                " View Graveyard : To view the cards in your graveyard\n" +
                "5.\n" +
                " View SpellField : To view the cards in both ’players spell fields\n" +
                "6.\n" +
                " View MonsterField : To view the cards in both ’players monster fields\n" +
                "7.\n" +
                " Info \"Card Name\": To view full information about a card\n" +
                "8.\n" +
                " Done: To end your turn\n");

    }

    private void endBattle(){

    }
}
