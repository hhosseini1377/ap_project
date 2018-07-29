package View.BattleGroundView;

import Control.MultiPlayer.MultiBattleControl;
import Modules.Card.Card;
import Modules.Card.Commanders.Commander;
import Modules.Card.Monsters.Monster;
import Modules.Graphic.Graphics;
import View.ShopView.CardView;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;

public class MonsterFieldView {
    private HBox[] fieldView;
    private VBox commanderBox;
    private Commander commander;
    private EventHandler<MouseEvent> cardInfo;

    public HBox[] getFieldView () {
        return fieldView;
    }

    public void setFieldView (HBox[] fieldView) {
        this.fieldView = fieldView;
    }

    public VBox getCommanderBox () {
        return commanderBox;
    }

    public void setCommanderBox (VBox commanderBox) {
        this.commanderBox = commanderBox;
        EventHandler<MouseEvent> command = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                command();
            }
        };
        commanderBox.addEventHandler(MouseEvent.MOUSE_CLICKED, command);
//        commanderBox.setOnMouseEntered(event -> commanderBox.setStyle(""));
    }

    public void setCommander (Commander commander) {
        this.commander = commander;
    }

    public void addToField (Card card, int slot) {
        card.setCardView(new CardView(120, 180, card.getCardImage(), card, 0, 0));
        fieldView[slot].getChildren().add(card.getCardView().getMainVBox());
        //adding effects and event handlers to card view
        card.getCardView().getMainVBox().setOnMouseEntered(event -> card.getCardView().getMainVBox().setEffect(new Glow(.4)));
        card.getCardView().getMainVBox().setOnMouseExited(event -> card.getCardView().getMainVBox().setEffect(null));
        cardInfo = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                    if (!card.getFriend().getMonsterField().isEnemy())
                        CardInfo(card);
                    else
                        EnemyCardInfo(card);
                }
            }
        };
        card.getCardView().getMainVBox().addEventHandler(MouseEvent.MOUSE_CLICKED, cardInfo);
    }

    public void removeFromField (Card card, int slot) {
        card.getCardView().getMainVBox().removeEventHandler(MouseEvent.MOUSE_CLICKED, cardInfo);
        fieldView[slot].getChildren().remove(card.getCardView().getMainVBox());
        card.setCardView(new CardView(Graphics.SCREEN_WIDTH * 3 / 18, Graphics.SCREEN_HEIGHT * 5 / 12, card.getCardImage(), card, 0, 0, false));
    }


    public void update (Card card, int slot) {
        fieldView[slot].getChildren().remove(0);
        card.setCardView(new CardView(120, 180, card.getCardImage(), card, 0, 0));
        fieldView[slot].getChildren().add(card.getCardView().getMainVBox());
    }


    private void CardInfo (Card card) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../../Files/Resources/CardInfoPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        GridPane cardBox = (GridPane) root.lookup("#dialogBox");
        cardBox.getChildren().add(card.getCardViewBig().getMainVBox());
        ((AnchorPane) Graphics.getInstance().getStage().getScene().getRoot()).getChildren().add(root);
        Button okButton = new Button();
        okButton.setStyle("-fx-background-color: #69443c; " +
                "-fx-background-radius: 10px; " +
                "-fx-font-size: 23; -fx-font-family: Purisa;" +
                "-fx-font-weight: bold;");
        okButton.setText("OK");
        okButton.setTextFill(Color.CORNSILK);
        Button moveToField = new Button("Attack");
        moveToField.setStyle("-fx-background-color: #69443c; " +
                "-fx-background-radius: 10px; " +
                "-fx-font-size: 23; -fx-font-family: Purisa;" +
                "-fx-font-weight: bold;");
        moveToField.setTextFill(Color.CORNSILK);

        HBox bHold = new HBox(okButton, moveToField);
        bHold.setSpacing(50);
        Effect glow = new Glow(.4);
        ((AnchorPane) Graphics.getInstance().getBattle().getRoot()).getChildren().add(bHold);
        bHold.setLayoutX(Graphics.SCREEN_WIDTH / 2 - 130);
        bHold.setLayoutY(Graphics.SCREEN_HEIGHT - 60);

        Parent finalRoot = root;
        EventHandler<MouseEvent> okHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED))
                    removeCardInfo(bHold, finalRoot);
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
                    okButton.setEffect(glow);
                if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
                    okButton.setEffect(null);
            }
        };
        EventHandler<MouseEvent> moveHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                    if (((Monster) card).canAttack())
                        new AttackHandler(card.getEnemy()
                                .getMonsterField()
                                .getMonsterFieldView().getFieldView(), card);
                    else
                        Graphics.getInstance().notifyMessage("Can not attack now!", "notify");
                    removeCardInfo(bHold, finalRoot);
                }
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
                    moveToField.setEffect(glow);
                if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
                    moveToField.setEffect(null);
            }
        };
        okButton.addEventHandler(MouseEvent.ANY, okHandler);
        moveToField.addEventHandler(MouseEvent.ANY, moveHandler);
    }

    private void EnemyCardInfo (Card card) {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../../Files/Resources/CardInfoPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        GridPane cardBox = (GridPane) root.lookup("#dialogBox");
        cardBox.getChildren().add(card.getCardViewBig().getMainVBox());
        ((AnchorPane) Graphics.getInstance().getStage().getScene().getRoot()).getChildren().add(root);
        Button okButton = new Button();
        okButton.setStyle("-fx-background-color: #69443c; " +
                "-fx-background-radius: 10px; " +
                "-fx-font-size: 23; -fx-font-family: Purisa;" +
                "-fx-font-weight: bold;");
        okButton.setText("OK");
        okButton.setTextFill(Color.CORNSILK);

        HBox bHold = new HBox(okButton);
        bHold.setSpacing(50);
        Effect glow = new Glow(.4);
        ((AnchorPane) Graphics.getInstance().getBattle().getRoot()).getChildren().add(bHold);
        bHold.setLayoutX(Graphics.SCREEN_WIDTH / 2 - 30);
        bHold.setLayoutY(Graphics.SCREEN_HEIGHT - 60);

        Parent finalRoot = root;
        EventHandler<MouseEvent> okHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED))
                    removeCardInfo(bHold, finalRoot);
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
                    okButton.setEffect(glow);
                if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
                    okButton.setEffect(null);
            }
        };
        okButton.addEventHandler(MouseEvent.ANY, okHandler);
    }

    private void removeCardInfo (HBox bHold, Parent finalRoot) {
        ((AnchorPane) Graphics.getInstance().getBattle().getRoot()).getChildren()
                .removeAll(bHold, finalRoot);
    }

    private void command () {
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../../Files/Resources/CardInfoPage.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        GridPane cardBox = (GridPane) root.lookup("#dialogBox");
        cardBox.getChildren().add(commander.getCardViewBig().getMainVBox());
        ((AnchorPane) Graphics.getInstance().getStage().getScene().getRoot()).getChildren().add(root);
        Button okButton = new Button();
        okButton.setStyle("-fx-background-color: #69443c; " +
                "-fx-background-radius: 10px; " +
                "-fx-font-size: 23; -fx-font-family: Purisa;" +
                "-fx-font-weight: bold;");
        okButton.setText("OK");
        okButton.setTextFill(Color.CORNSILK);

        HBox bHold = new HBox(okButton);
        bHold.setSpacing(50);
        Effect glow = new Glow(.4);
        ((AnchorPane) Graphics.getInstance().getBattle().getRoot()).getChildren().add(bHold);
        bHold.setLayoutX(Graphics.SCREEN_WIDTH / 2 - 30);
        bHold.setLayoutY(Graphics.SCREEN_HEIGHT - 60);

        Parent finalRoot = root;
        EventHandler<MouseEvent> okHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED))
                    removeCardInfo(bHold, finalRoot);
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
                    okButton.setEffect(glow);
                if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
                    okButton.setEffect(null);
            }
        };
        okButton.addEventHandler(MouseEvent.ANY, okHandler);
    }
}

    class AttackHandler {
        private HBox[] field;
        private VBox commanderBox;
        private ArrayList<EventHandler<MouseEvent>> onClicks = new ArrayList<>();

        AttackHandler (HBox[] enemyField, Card card) {
            this.field = enemyField;
            for (int i = 0; i < 5; i++) {
                HBox slot = (HBox) enemyField[i];
                int finalI = i;
                EventHandler<MouseEvent> onClick = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle (MouseEvent event) {
                        if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                            slot.setStyle("-fx-background-color: rgba(36,33,22,0.92);");
                        } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
                            slot.setStyle("-fx-background-color: rgba(28,26,18,0.74);");
                        } else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                            removeEventHandlers();
                            Card enemySelectedCard = card.getEnemy().getMonsterField().getSlot(finalI + 1);
                            MultiBattleControl.detail.actions.add("attack:"+card.getName()+":"+enemySelectedCard.getName());
                            ((Monster) enemySelectedCard).decreaseHP(((Monster) card).getAP());
                            ((Monster) card).decreaseHP(((Monster) enemySelectedCard).getAP());
                        }
                    }
                };
                onClicks.add(onClick);
                slot.addEventHandler(MouseEvent.ANY, onClick);
            }
            commanderBox = card.getEnemy().getMonsterField().getMonsterFieldView().getCommanderBox();
            EventHandler<MouseEvent> onClick = new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent event) {
                    if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                        removeEventHandlers();
                        Card enemySelectedCard = card.getEnemy().getCommander();
                        MultiBattleControl.detail.actions.add("attackCom:"+card.getName());
                        ((Monster) enemySelectedCard).decreaseHP(((Monster) card).getAP());
                        ((Monster) card).decreaseHP(((Monster) enemySelectedCard).getAP());
                    }
                }
            };
            onClicks.add(onClick);
            commanderBox.addEventHandler(MouseEvent.ANY, onClick);
        }

        private void removeEventHandlers () {
            for (int i = 0; i < 5; i++) {
                HBox slot = (HBox) field[i];
                slot.removeEventHandler(MouseEvent.ANY, onClicks.get(i));
            }
            commanderBox.removeEventHandler(MouseEvent.ANY, onClicks.get(5));
        }
}