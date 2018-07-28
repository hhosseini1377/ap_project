package Control.MultiPlayer;
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
import javafx.scene.Scene;
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

public class MultiBattleControl {
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
        try {
            Parent spellRoot = FXMLLoader.load(getClass().getResource("../Files/Resources/SpellField.fxml"));
            HBox[] fspellField = new HBox[3];
            HBox[] espellField = new HBox[3];
            for(int i = 0; i < 3; i++){
                fspellField[i] = (HBox) ((HBox) spellRoot.lookup("#friendField")).getChildren().get(i);
                espellField[i] = (HBox) ((HBox) spellRoot.lookup("#enemyField")).getChildren().get(i);
            }
            Graphics.getInstance().setFspellField(fspellField);
            Graphics.getInstance().setEspellField(espellField);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = Graphics.getInstance().getStage().getScene().getRoot();

        //saving monster hbox
        HBox[] fmonsters = new HBox[5];
        HBox[] emonsters = new HBox[5];
        for (int i = 0; i < 5; i++){
            emonsters[i] = (HBox) ((HBox)root.lookup("#monsterFieldP1")).getChildren().get(i);
            fmonsters[i] = (HBox) ((HBox)root.lookup("#monsterFieldP2")).getChildren().get(i);
        }
        Graphics.getInstance().setFmonsterField(fmonsters);
        Graphics.getInstance().setEmonsterField(emonsters);

        ImageView spellField = (ImageView) root.lookup("#spellField");
        spellField.setOnMouseClicked(event -> {spellFieldScreen();});

        //setting hand view up
        warrior[1].getHand().setHandView((HBox) root.lookup("#handP2"));
        warrior[0].getHand().setHandView((HBox) root.lookup("#handP1"));

        //setting monster field view up
        warrior[1].getMonsterField().setFieldView((HBox) root.lookup("#monsterFieldP2"));
        warrior[0].getMonsterField().setFieldView((HBox) root.lookup("#monsterFieldP1"));

        warrior[0].getMonsterField().getMonsterFieldView().setCommander(warrior[0].getCommander());
        warrior[1].getMonsterField().getMonsterFieldView().setCommander(warrior[1].getCommander());

        warrior[0].getMonsterField().getMonsterFieldView().setCommanderBox((VBox) root.lookup("#frameContP1"));
        warrior[1].getMonsterField().getMonsterFieldView().setCommanderBox((VBox) root.lookup("#frameContP2"));

        //setting spell field view up
        warrior[1].getSpellField().setView(Graphics.getInstance().getFspellField());
        warrior[0].getSpellField().setView(Graphics.getInstance().getEspellField());

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
        if (!checkEndOfTheGame()) {
            turn++;
            //increase the mana point that the warrior has
            warrior[turn % 2].setMaxManaPoint(warrior[turn % 2].getMaxManaPoint() + 1);
            //to do changes in sleeping status of monsters
            warrior[turn % 2].getMonsterField().changeTurnActions(true);
            warrior[turn % 2].getSpellField().changeTurnActions(true);
            //to do changes in sleeping status of defensive monsters
            warrior[(turn + 1) % 2].getMonsterField().changeTurnActions(false);
            warrior[(turn + 1) % 2].getSpellField().changeTurnActions(false);
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
            } catch (NullPointerException e) {
                System.out.println("No more cards in the deck!!!");
                System.out.println("Turn " + turn + " started!\n" + warrior[turn % 2].getName() + "'s turn");
                if (turn % 2 == 0) {
                    warrior[0].makeMove(warrior[1]);
                    changeTurn();
                }
            }
        }
    }

    private void spellFieldScreen(){
        Parent root = Graphics.getInstance().getBattle().getRoot();
        if (Graphics.isMonsterField){
            for (int i = 0; i < 5; i++){
                ((HBox)root.lookup("#monsterFieldP1")).getChildren().remove(0);
                ((HBox)root.lookup("#monsterFieldP2")).getChildren().remove(0);
            }
            for (int i = 0; i < 3; i++){
                ((HBox) root.lookup("#monsterFieldP1")).getChildren()
                        .add(Graphics.getInstance().getEspellField()[i]);
                ((HBox) root.lookup("#monsterFieldP2")).getChildren()
                        .add(Graphics.getInstance().getFspellField()[i]);
            }
            Graphics.isMonsterField = false;
        }else{
            for (int i = 0; i < 3; i++){
                ((HBox) root.lookup("#monsterFieldP1")).getChildren()
                        .remove(0);
                ((HBox) root.lookup("#monsterFieldP2")).getChildren()
                        .remove(0);
            }
            for (int i = 0; i < 5; i++){
                ((HBox) root.lookup("#monsterFieldP1")).getChildren()
                        .add(Graphics.getInstance().getEmonsterField()[i]);
                ((HBox) root.lookup("#monsterFieldP2")).getChildren()
                        .add(Graphics.getInstance().getFmonsterField()[i]);
            }
            Graphics.isMonsterField = true;
        }
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
            Graphics.getInstance().notifyMessage("now the game will be reset to the time before battle", "notify");
        }
        Menu.getInstance().goBacktoMenu();
        Graphics.getInstance().notifyMessage("Unfortunately the demons were too strong!!" +
                "\nRegain power and challenge them\nagain brave hero!", "notify");

    }

    private void win () {
        warrior[1].getUser().setLevel(warrior[1].getUser().getLevel() + 1);
        warrior[1].getUser().setGills(warrior[1].getUser().getGills() + warrior[0].getWinPrize());
        Menu.getInstance().goBacktoMenu();
        Graphics.getInstance().notifyMessage("Congratulations\n" +
                "With your skills and bravery " + warrior[0].getName() + "\nhas been utterly defeated!!" +
                "\nIt was right to request help from\nyou brave warrior!", "notify");
    }
}

class TurnException extends Exception {
    TurnException () {
        Graphics.getInstance().notifyMessage("not your turn\ntry again when its your turn", "notify");
    }
}