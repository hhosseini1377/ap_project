package Control;

import Modules.Card.Card;
import Modules.Card.Monsters.*;
import Modules.Card.Spell.Spell;
import Modules.Enemies.Goblins.Goblins;
import Modules.Enemies.Lucifer.Lucifer;
import Modules.Enemies.Ogres.Ogres;
import Modules.Enemies.Vampires.Vampires;
import Modules.User.User;
import Modules.Warrior.Warrior;

import java.util.Iterator;
import java.util.Scanner;

public class BattleControl {
    private int turn;
    private Warrior[] warrior;

    public void startBattle(User user){
        warrior = new Warrior[2];

        //TODO needs to be handled with exception
        if (!user.getDeck().isAcceptable()){
            System.out.println("deck not acceptable!!\nfix it and try again");
            return;
        }

        warrior[1] = new Warrior(user.getDeck(), user.getName());
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
                default:
                    System.out.println("You've already completed the game...\ngo somewhere else kido!!");
        }
        warrior[1].setUser(user);
        Iterator<Card> itr = warrior[1].getDeck().getCards().iterator();
        while (itr.hasNext()){
            Card card = itr.next();
            card.setEnemy(warrior[0]);
            card.setFriend(warrior[1]);
        }
        itr = warrior[0].getDeck().getCards().iterator();
        while (itr.hasNext()){
            Card card = itr.next();
            card.setEnemy(warrior[1]);
            card.setFriend(warrior[0]);
        }
        turn = 1;
        System.out.println("Battle against " + warrior[0].getName() + " started!");
        battle();
    }

    /**
     * the bulk of the battle
     * checking user's inputs and deciding according to them
     */
    private void battle(){
        //randomly starting the game
        int player = (int)(Math.random() * 2);
        String action[];
        Scanner scan = new Scanner(System.in);
        System.out.println(warrior[player].getName() + " starts the battle");

        //giving 5 cards to each combatant
        for (int i = 0; i < 5; i++) {
            warrior[player].getHand().add(warrior[player].getDeck().takeCard());
            warrior[(player + 1) % 2].getHand().add(warrior[(player+1) % 2].getDeck().takeCard());
        }
        System.out.print("player drew ");
        for (int i = 0; i < 4; i++)
            System.out.print(warrior[1].getHand().getCards().get(i).getName() + ", ");
        System.out.println(warrior[1].getHand().getCards().get(4).getName());

        while (true){
            if (checkEndOfTheGame()){
                return;
            }
            action = scan.nextLine().split(" ");

            switch (action[0]){
                case "Help":
                    help();
                    break;
                case "Exit":
                    endBattle();
                    return;
                case "Use":
                    try{
                        if (turn % 2 != 1)
                            throw new TurnException();
                    }catch (TurnException e){
                        break;
                    }
                    useCard(Integer.parseInt(action[1]));
                    break;
                case "Done":
                    if (turn % 2 == 1) {
                        changeTurn();
                    }else{
                        System.out.println("What the hell do you think you are doing little shit???");
                    }
                    break;
                case "Set":
                    Card card = warrior[1].getHand().getCard(Integer.parseInt(action[1]));
                    if (warrior[1].getManaPoint() >= card.getManaPoint()) {
                        if (card instanceof Monster) {
                            ((Monster) card).enterField(warrior[0], warrior[1]);

                            warrior[1].getMonsterField().add(((Monster) card), Integer.parseInt(action[3]));
                            System.out.println(card.getName() + "was set in MonsterField slot "
                                    + Integer.parseInt(action[3]) + " . "  + card.getManaPoint() + "MP was used.\n");
                            warrior[1].getHand().remove(card);
                        }
                        if (card instanceof Spell){
                            warrior[1].getSpellField().add(((Spell)card), Integer.parseInt(action[3]));
                            System.out.println(card.getName() + "was set in SpellField slot "
                                    + Integer.parseInt(action[3]) + " . " + card.getManaPoint() + "MP was used.\n");
                            warrior[1].getHand().remove(card);
                            ((Spell)card).castSpell();
                        }
                    }else
                        System.out.println("You do not have enough MP to do this act");
            }
        }
    }

    /**
     * to do the necessary changes in warriors status when Done is clicked
     */
    private void changeTurn(){
        turn++;
        warrior[turn % 2].setManaPoint(warrior[turn % 2].getManaPoint() + 1);
        //drawing a card from deck to hand
        Card drawnCard = warrior[turn % 2].getDeck().takeCard();
        warrior[turn % 2].getHand().add(drawnCard);

        System.out.println("Turn " + turn + "started!\n" + warrior[turn % 2].getName() + "'s turn");
        if (turn % 2 == 1){
            System.out.println(drawnCard.getName());
        }
        //this has to be written every time we enter this menu
        if (turn % 2 == 1)
            System.out.println("[" + warrior[turn % 2].getManaPoint() + ", "
                    + warrior[turn % 2].getMaxManaPoint() + "]");
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
                        ((SpellCaster) monster).castSpell(warrior[0], warrior[1]);
                    }else if (monster instanceof Hero) {
                        ((Hero) monster).castSpell(warrior[0], warrior[1]);
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

    private void help(){
        System.out.println("1." +
                " Use #SlotNum : To use a specific card which is on the Monster Field\n" +
                "2." +
                " Set #HandIndex to #SlotNum : To set a card which is on the hand , in the field\n" +
                "3." +
                " View Hand: To view the cards in your hand\n" +
                "4." +
                " View Graveyard : To view the cards in your graveyard\n" +
                "5." +
                " View SpellField : To view the cards in both ’players spell fields\n" +
                "6." +
                " View MonsterField : To view the cards in both ’players monster fields\n" +
                "7." +
                " Info \"Card Name\": To view full information about a card\n" +
                "8." +
                " Done: To end your turn\n");
    }

    private boolean checkEndOfTheGame(){
        if (warrior[0].getCommander().isDead()) {
            win();
            return true;
        }
        if (warrior[1].getCommander().isDead()) {
            lose();
            return true;
        }
        return false;
    }

    private void endBattle(){

    }

    private void lose(){
//        if (warrior[1].getBackPack().ContainsItem("Mystic HourGlass")){
        //TODO
//            warrior[1].getBackPack().getItem("Mystic HourGlass");
//            warrior[1].getBackPack().remove()
//        }

        System.out.println("Unfortunately the demons were too strong!!" +
                "\nRegain power and challenge them again brave hero!");

    }

    private void win(){
        warrior[1].getUser().setLevel(warrior[1].getUser().getLevel() + 1);
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