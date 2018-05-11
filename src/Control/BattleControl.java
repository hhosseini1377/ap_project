package Control;

import Modules.Card.Card;
import Modules.Card.Monsters.*;
import Modules.Enemies.Goblins.Goblins;
import Modules.Enemies.Lucifer.Lucifer;
import Modules.Enemies.Ogres.Ogres;
import Modules.Enemies.Vampires.Vampires;
import Modules.User.User;
import Modules.Warrior.Warrior;

import java.util.Scanner;

public class BattleControl {
    private int turn;
    private Warrior[] warrior;
    // TODO needs to add computer playing warrior as well

    public void startBattle(User user){
        warrior = new Warrior[2];

        //TODO needs to be handled with exception
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
                warrior[0] = new Vampires();
                break;
            case 4:
                warrior[0] = new Lucifer();
                break;
        }
        turn = 0;
        System.out.println("Battle against" + warrior[0].getName() + "started!");
        battle();
    }

    /**
     * the bulk of the battle
     * checking user's inputs and deciding according to them
     */
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
            checkEndOfTheGame();
            action = scan.next();
            switch (action){
                case "Help":
                    help();
                    break;
                case "Exit":
                    endBattle();
                    break;
                case "Use":
                    try{
                        if (turn != 1)
                            throw new TurnException();
                    }catch (TurnException e){
                        break;
                    }
                    useCard(scan.nextInt());
                    break;
            }
        }
    }

    private void useCard(int slotNum){
        Monster monster = warrior[1].getMonsterField().getSlot(slotNum);
        System.out.println("Using " + monster.getName() + "\nHP: " +
                monster.getHP() + " AP: " + monster.getAP() +
                "\nIs Sleeping: " + monster.isSleeping() + "\nCan Attack: " +
                monster.canAttack());
        if (monster instanceof SpellCaster){
            System.out.println("Can Cast: " + ((SpellCaster) monster).CanCast());
        }
        if (monster instanceof Hero){
            System.out.println("Can Cast: " + ((Hero) monster).CanCast());
        }
        Scanner scan = new Scanner(System.in);

        while(true){
            switch (scan.next()){
                case "Help":
                    System.out.println("1. Attack # EnemyMonsterSlot / Player : To attack the card on that slot of enemy MonsterField\n" +
                            "2. Info: To get full information on card\n" +
                            "3. Exit: To go back to Play Menu\n");
                    break;
                case "Info":
                    break;
                case "Exit":
                    return;
                case "Attack":
                    monster.attack(warrior[0], scan);
                    return;
                case "Cast":
                    if (monster instanceof SpellCaster) {
                        ((SpellCaster) monster).castSpell(warrior[0].getMonsterField().getMonsterCards(),
                                warrior[1].getMonsterField().getMonsterCards());
                    }else if (monster instanceof Hero) {
                        ((Hero) monster).castSpell(warrior[0].getMonsterField().getMonsterCards(),
                                warrior[1].getMonsterField().getMonsterCards());
                    }else{
                        System.out.println("this warrior is neither a spell caster nor a hero");
                    }
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }
    }

//    /**
//     * attacks the targeted monster
//     */
//    public void attack(Warrior enemy, Monster monster, Scanner scan){
//        Monster targeted;
//        //checks if there are any defensive cards in the enemy field and if there was the defender will stop the combatant
//        if (enemy.getMonsterField().containDefensiveCard()){
//            targeted = enemy.getMonsterField().getDefensiveCard();
//            monster.attack(targeted);
//            return;
//        }
//        String target = scan.next();
//        if ((target.equals("Commander"))) {
//            targeted = enemy.getCommander();
//            monster.attack(enemy.getCommander());
//        }
//        else{
//            targeted = enemy.getMonsterField().getSlot(Integer.parseInt(target));
//            monster.attack(targeted);
//        }
//        System.out.println(monster.getName() + " clashed with " + targeted.getName());
//    }

    private void help(){
        System.out.println("1.\n" +
                " Use #SlotNum : To use a specific card which is on the Monster Field\n" +
                "2.\n" +
                " Set HandIndex to #SlotNum : To set a card which is on the hand , in the field\n" +
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

    private void checkEndOfTheGame(){
        if (warrior[0].getCommander().isDead())
            win();
        if (warrior[1].getCommander().isDead())
            lose();
    }

    private void endBattle(){

    }

    private void lose(){
        System.out.println("Unfortunately the demons were too strong!!" +
                "\nRegain power and challenge them again brave hero!");
    }

    private void win(){
        System.out.println("Congratulations\n" +
                "With your skills and bravery " + warrior[0].getName() + " has been utterly defeated!!" +
                "\nIt was right to request help from you brave warrior!");
    }
}

class TurnException extends Exception{
    TurnException(){
        System.out.println("not your turn\ntry again when its your turn");
    }
}