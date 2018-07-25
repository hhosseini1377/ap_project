package Control;

import Modules.Card.Card;
import Modules.Card.Monsters.*;
import Modules.Card.Spell.Spell;
import Modules.Enemies.Goblins.Goblins;
import Modules.Enemies.Lucifer.Lucifer;
import Modules.Enemies.Ogres.Ogres;
import Modules.Enemies.Vampires.Vampires;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
import Modules.ItemAndAmulet.Items.MysticHourglass;
import Modules.User.User;
import Modules.Warrior.Warrior;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

import java.io.IOException;
import java.util.Iterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BattleControl {
    private int turn;
    private Warrior[] warrior;

    public void startBattle (User user, int level) {
        warrior = new Warrior[2];

        //TODO needs to be handled with exception
        if (!user.getDeck().isAcceptable()) {
            Graphics.getInstance().notifyMessage("deck not acceptable!!\nfix it and try again", "notify");
            return;
        }
        //doesn't let the cleared level to be repeated again
        if (user.getLevel() > level){
            Menu.getInstance().goBacktoMenu();
        }

        warrior[1] = new Warrior(user.getDeck(), user.getName());
        switch (level) {
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
                Graphics.getInstance().notifyMessage("You've already completed the game", "notify");
                return;
        }

        warrior[1].setUser(user);
        warrior[0].getHand().setEnemy(true);
        warrior[0].getMonsterField().setEnemy(true);
        warrior[0].setMaxManaPoint(1);
        warrior[1].setMaxManaPoint(1);
        Iterator<Card> itr = warrior[1].getDeck().getCards().iterator();
        while (itr.hasNext()) {
            Card card = itr.next();
            card.setEnemy(warrior[0]);
            card.setFriend(warrior[1]);
        }
        itr = warrior[0].getDeck().getCards().iterator();
        while (itr.hasNext()) {
            Card card = itr.next();
            card.setEnemy(warrior[1]);
            card.setFriend(warrior[0]);
        }
        setDetails();
        Graphics.getInstance().notifyMessage("Battle against " + warrior[0].getName() + " started!", "notify");
        battle();
    }

    private void setDetails() {
        Parent root = Graphics.getInstance().getStage().getScene().getRoot();
        //setting hand view up
        warrior[1].getHand().setHandView((HBox) root.lookup("#handP2"));
        warrior[0].getHand().setHandView((HBox) root.lookup("#handP1"));

        //setting monster field view up
        warrior[1].getMonsterField().setFieldView((HBox) root.lookup("#monsterFieldP2"));
        warrior[0].getMonsterField().setFieldView((HBox) root.lookup("#monsterFieldP1"));
        Button doneButton = (Button) root.lookup("#changeTurn");
        String buttonStyle = "-fx-background-radius: 20;" +
                "-fx-border-radius: 20;" +
                "-fx-border-width: 2;" +
                "-fx-border-color: rgb(99,85,44);";
        EventHandler<MouseEvent> onButton = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)){
                    doneButton.setStyle(buttonStyle + "-fx-background-color: rgb(189,171,22);");
                }else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)){
                    doneButton.setStyle(buttonStyle + "-fx-background-color: rgba(220,215,47,0.99);");
                }else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                    if (turn % 2 == 1)
                        changeTurn();
                }
            }
        };
        doneButton.addEventHandler(MouseEvent.ANY, onButton);
        ImageView graveyard1 = (ImageView) root.lookup("#graveyardP1");
        ImageView graveyard2 = (ImageView) root.lookup("#graveyardP2");
        //setting up necessary details for graveyard objects
        warrior[0].getGraveYard().setGraveyardView(graveyard1);
        warrior[0].getGraveYard().setOwner(warrior[0]);
        warrior[1].getGraveYard().setGraveyardView(graveyard2);
        warrior[1].getGraveYard().setOwner(warrior[1]);
        EventHandler<MouseEvent> graveHandler1 = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)){
                    graveyard1.setEffect(new Glow(.4));
                }
                if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
                    graveyard1.setEffect(null);
                if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED))
                    warrior[0].getGraveYard().viewGraveyard();

            }
        };
        EventHandler<MouseEvent> graveHandler2 = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)){
                    graveyard2.setEffect(new Glow(.4));
                }
                if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
                    graveyard2.setEffect(null);
                if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED))
                    warrior[1].getGraveYard().viewGraveyard();

            }
        };
        graveyard1.addEventHandler(MouseEvent.ANY, graveHandler1);
        graveyard2.addEventHandler(MouseEvent.ANY, graveHandler2);
    }

    /**
     * the bulk of the battle
     * checking user's inputs and deciding according to them
     */
    private void battle () {
        //randomly starting the game
        int player = (int) (Math.random() * 2);
        player = 1;

        Graphics.getInstance().notifyMessage(warrior[player].getName() + " starts the battle", "notify");
        turn = player;

        //giving 5 cards to each combatant
        for (int i = 0; i < 5; i++) {
            warrior[player].getHand().add(warrior[player].getDeck().takeCard());
            warrior[(player + 1) % 2].getHand().add(warrior[(player + 1) % 2].getDeck().takeCard());
        }
    }

    /**
     * to do the necessary changes in warriors status when Done is clicked
     */
    private void changeTurn () {
        turn++;
        //increase the mana point that the warrior has
        warrior[turn % 2].setMaxManaPoint(warrior[turn % 2].getMaxManaPoint() + 1);
        //to do changes in sleeping status of monsters
        warrior[turn % 2].getMonsterField().changeTurnActions(true);
        //to do changes in sleeping status of defensive monsters
        warrior[(turn + 1) % 2].getMonsterField().changeTurnActions(false);
        //drawing a card from deck to hand
        try {
            Card drawnCard = warrior[turn % 2].getDeck().takeCard();
            if (drawnCard != null) {
                warrior[turn % 2].getHand().add(drawnCard);

                System.out.println("Turn " + turn + " started!\n" + warrior[turn % 2].getName() + "'s turn");
                if (turn % 2 == 1) {
                    System.out.println(drawnCard.getName());
                }
                //this has to be written every time we enter this menu
                if (turn % 2 == 1)
                    System.out.println("[" + warrior[turn % 2].getManaPoint() + ", "
                            + warrior[turn % 2].getMaxManaPoint() + "]");
                if (turn % 2 == 0) {
                    warrior[0].makeMove(warrior[1]);
                    changeTurn();
                }
            }
        }catch (NullPointerException e) {
            System.out.println("No more cards in the deck!!!");
            System.out.println("Turn " + turn + " started!\n" + warrior[turn % 2].getName() + "'s turn");
            if (turn % 2 == 0) {
                warrior[0].makeMove(warrior[1]);
                changeTurn();
            }
        }
    }

    /**
     * set the decided card from hand into the field
     * @param action the scanned instructions gotten from user
     */
    private void setIntoField (String[] action) {
        try {
            Card card = warrior[1].getHand().getCard(Integer.parseInt(action[1]) - 1);
            if (warrior[1].getManaPoint() >= card.getManaPoint()) {
                if (card instanceof Monster) {

                    if (warrior[1].getMonsterField().add(((Monster) card), Integer.parseInt(action[3]))) {
                        warrior[1].getHand().remove(card);
                        System.out.println(card.getName() + " was set in MonsterField slot "
                                + Integer.parseInt(action[3]) + " . " + card.getManaPoint() + " MP was used.");
                        ((Monster) card).enterField(warrior[0], warrior[1]);
                    }
                }
                if (card instanceof Spell) {
                    warrior[1].getSpellField().add(((Spell) card), Integer.parseInt(action[3]));
                    System.out.println(card.getName() + " was set in SpellField slot "
                            + Integer.parseInt(action[3]) + " . " + card.getManaPoint() + " MP was used.");
                    warrior[1].getHand().remove(card);
                }

                warrior[1].setManaPoint(warrior[1].getManaPoint() - card.getManaPoint());
            } else
                System.out.println("You do not have enough MP to do this act");
        }catch (Exception e){
            System.out.println("The index you chose is not available, try again" );
        }
    }

    private void useCard (int slotNum) {
        Monster monster = warrior[1].getMonsterField().getSlot(slotNum);
        System.out.println("Using " + monster.getName() + "\nHP: " +
                monster.getHP() + " AP: " + monster.getAP() +
                "\nIs Sleeping: " + monster.isSleeping() + "\nCan Attack: " +
                monster.canAttack());
        if (monster instanceof SpellCaster) {
            System.out.println("Can Cast: " + ((SpellCaster) monster).CanCast());
        }
        if (monster instanceof Hero) {
            System.out.println("Can Cast: " + ((Hero) monster).CanCast());
        }
        Scanner scan = new Scanner(System.in);

        while (true) {
            switch (scan.next()) {
                case "Help":
                    System.out.println("1. Attack #EnemyMonsterSlot / Player : To attack the card on that slot of enemy MonsterField\n" +
                            "2. Info: To get full information on card\n" +
                            "3. Exit: To go back to Play Menu\n");
                    break;
                case "Info":
                    System.out.println(warrior[1].getMonsterField().getSlot(slotNum).toString());
                    break;
                case "Exit":
                    return;
                case "Attack":
                    monster.attack(warrior[0], scan);
                    return;
                case "Cast":
                    if (monster instanceof SpellCaster) {
                        monster.castSpell(warrior[0], warrior[1]);
                    } else if (monster instanceof Hero) {
                        monster.castSpell(warrior[0], warrior[1]);
                    } else {
                        System.out.println("this warrior is neither a spell caster nor a hero");
                    }
                    break;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }
    }

    private void help () {
        System.out.println("1. Use #SlotNum : To use a specific card which is on the Monster Field\n" +
                "2. Set #HandIndex to #SlotNum : To set a card which is on the hand , in the field\n" +
                "3. View Hand: To view the cards in your hand\n" +
                "4. View Graveyard : To view the cards in your graveyard\n" +
                "5. View SpellField : To view the cards in both ’players spell fields\n" +
                "6. View MonsterField : To view the cards in both ’players monster fields\n" +
                "7. Info \"Card Name\": To view full information about a card\n" +
                "8. Done: To end your turn\n");
    }

    private void viewHand () {
        int index = 1;
        System.out.println("Your Status: \n[" + warrior[1].getManaPoint() + " - " + warrior[1].getMaxManaPoint() + "]");
        System.out.println("Your Hand:");
        for (Card card : warrior[1].getHand().getCards()) {
            System.out.println(index + ". " + card.getName() + ", Mana cost: " + card.getManaPoint());
            index++;
        }
    }

    private void viewGraveyard () {
        int index = 1;
        System.out.println("Your Graveyard:");
        for (Card card : warrior[1].getGraveYard().getDestroyedCards()) {
            System.out.println(index + ". " + card.getName());
            index++;
        }
        index = 1;
        System.out.println("Enemies Graveyard:");
        for (Card card : warrior[0].getGraveYard().getDestroyedCards()) {
            System.out.println(index + ". " + card.getName());
            index++;
        }
    }

    private void viewMonsterField () {
        System.out.println("Your MonsterField:");
        System.out.println("Your Commander: " + warrior[1].getCommander().getName() + " HP: " +
                warrior[1].getCommander().getHP());

        Monster monster;
        for (int i = 1; i <= 5; i++) {
            if (warrior[1].getMonsterField().getSlot(i) == null)
                System.out.println(i + ". Empty");
            else {
                monster = warrior[1].getMonsterField().getSlot(i);
                System.out.print(i + ". " + monster.getName() + "/ HP: " + monster.getHP() +  "/ AP: " +
                        monster.getAP() + "/ is Defensive: " + !monster.isOffenseType() + "/ is nimble: " + monster.isNimble());
                if (monster.getMonsterKind() == MonsterKind.SPELL_CASTER) {
                    System.out.print(" can cast: " + ((SpellCaster) monster).CanCast());
                }
                if (monster.getMonsterKind() == MonsterKind.HERO) {
                    System.out.print(" can cast: " + ((SpellCaster) monster).CanCast());
                }
                System.out.println();
            }
        }
        System.out.println("Enemies MonsterField:");
        System.out.println("Enemies Commander: " + warrior[0].getCommander().getName() + " HP: " +
                warrior[0].getCommander().getHP());

        for (int i = 1; i <= 5; i++) {
            if (warrior[0].getMonsterField().getSlot(i) == null)
                System.out.println(i + ". Empty");
            else {
                monster = warrior[0].getMonsterField().getSlot(i);
                System.out.print(i + ". " + monster.getName() + "/ HP: " + monster.getHP() + "/ AP: " +
                        monster.getAP() + "/ is Defensive: " + !monster.isOffenseType() + "/ is nimble: " + monster.isNimble());
                if (monster.getMonsterKind() == MonsterKind.SPELL_CASTER) {
                    System.out.print(" can cast: " + ((SpellCaster) monster).CanCast());
                }
                if (monster.getMonsterKind() == MonsterKind.HERO) {
                    System.out.print(" can cast: " + ((SpellCaster) monster).CanCast());
                }
                System.out.println();
            }
        }
    }

    private void viewSpellField () {
        System.out.println("Your SpellField:");
        Spell spell;
        for (int i = 0; i < 3; i++) {
            if (warrior[1].getSpellField().getSlot(i) == null)
                System.out.println((i + 1) + ". Empty");
            else {
                spell = warrior[1].getSpellField().getSlot(i);
                System.out.println((i + 1) + ". " + spell.getName());
            }
        }
        System.out.println("Enemies SpellField:");
        for (int i = 0; i < 3; i++) {
            if (warrior[0].getSpellField().getSlot(i) == null)
                System.out.println((i + 1) + ". Empty");
            else {
                spell = warrior[0].getSpellField().getSlot(i);
                System.out.println((i + 1) + ". " + spell.getName());
            }
        }
    }

    private void viewCardInfo(Matcher matcher){

        if (warrior[0].hasCard(matcher.group(1))) {
            if (warrior[0].getHand().hasCard(matcher.group(1))) {
                System.out.println(warrior[0].getHand().getCard(matcher.group(1)).toString());
            } else if (warrior[0].getGraveYard().hasCard(matcher.group(1))) {
                System.out.println(warrior[0].getGraveYard().getCard(matcher.group(1)).toString());
            } else if (warrior[0].getMonsterField().hasCard(matcher.group(1))) {
                try {
                    System.out.println(warrior[0].getMonsterField().getCard(matcher.group(1)).toString());
                } catch (NullPointerException e) {
                    System.out.println("the card on monster field is null!" );
                }
            }else{
                try {
                    System.out.println(warrior[0].getSpellField().getCard(matcher.group(1)).toString());
                }catch (NullPointerException e){
                    System.out.println("the spell on spell field in null!");
                }
            }
        } else if (warrior[1].hasCard(matcher.group(1))) {
            if (warrior[1].getHand().hasCard(matcher.group(1))) {
                System.out.println(warrior[1].getHand().getCard(matcher.group(1)).toString());
            } else if (warrior[1].getGraveYard().hasCard(matcher.group(1))) {
                System.out.println(warrior[1].getGraveYard().getCard(matcher.group(1)).toString());
            } else if (warrior[1].getMonsterField().hasCard(matcher.group(1))) {
                try {
                    System.out.println(warrior[1].getMonsterField().getCard(matcher.group(1)).toString());
                } catch (NullPointerException e) {
                    System.out.println("the card on monster field is null!" );
                }
            }else{
                try {
                    System.out.println(warrior[1].getSpellField().getCard(matcher.group(1)).toString());
                }catch (NullPointerException e){
                    System.out.println("the spell on spell field in null!");
                }
            }
        } else
            System.out.println("There is no such card named as " + matcher.group(1) + ", You have probably made" +
                    "a mistake in typing the name, please try again.");
    }

    private boolean checkEndOfTheGame () {
        if (warrior[0].getCommander().isDead() || warrior[0].hasLost()) {
            win();
            return true;
        }
        if (warrior[1].getCommander().isDead() || warrior[1].hasLost()) {
            lose();
            return true;
        }
        return false;
    }

    private void lose () {
        if (warrior[1].getBackPack().ContainsItem("Mystic HourGlass")){
            ((MysticHourglass)warrior[1].getBackPack().getItem("Mystic HourGlass")).castSpell();
            warrior[1].getBackPack().remove(warrior[1].getBackPack().getItem("Mystic HourGlass"));
            System.out.println("now the game will be reset to the time before battle");
        }
        System.out.println("Unfortunately the demons were too strong!!" +
                "\nRegain power and challenge them again brave hero!");

    }

    private void win () {
        warrior[1].getUser().setLevel(warrior[1].getUser().getLevel() + 1);
        warrior[1].getUser().setGills(warrior[1].getUser().getGills() + warrior[0].getWinPrize());
        System.out.println("Congratulations\n" +
                "With your skills and bravery " + warrior[0].getName() + " has been utterly defeated!!" +
                "\nIt was right to request help from you brave warrior!");
    }
}

class TurnException extends Exception {
    TurnException () {
        System.out.println("not your turn\ntry again when its your turn");
    }
}