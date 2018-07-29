package View.InventoryView;

import Modules.BattleGround.Deck;
import Modules.Card.Card;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
import Modules.User.Inventory.CardInventory;
import Modules.User.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class CardInventoryView {
    private User user;
    private ArrayList<CardEventHandler> cardEvents = new ArrayList<>();
    private ArrayList<HBox> cardBoxes = new ArrayList<>();
    private int index = 0;

    public CardInventoryView (User user) {
        this.user = user;
    }

    public void viewInventory() throws IOException {
        cardBoxes.clear();
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/CardInventory.fxml"));
        Graphics.getInstance().getStage().setScene(new Scene(root));
        Graphics.getInstance().getStage().setFullScreen(true);

        HBox cardBox = null;
        for (int i = 0; i < user.getCardInventory().getCards().size(); i++){
            setCardHandler(user.getCardInventory().getCards().get(i));
            if (i % 4 == 0){
                cardBox = new HBox(50);
                cardBox.setAlignment(Pos.CENTER);
            }
            cardBox.getChildren().add(user.getCardInventory().getCards().get(i).getCardView().getMainVBox());
            if (i % 4 == 3){
                cardBoxes.add(cardBox);
            }
        }
        if (!cardBoxes.contains(cardBox))
            cardBoxes.add(cardBox);
        for (int i = 0; i < 2; i++){
            ((VBox)root.lookup("#vBox")).getChildren().add(cardBoxes.get(i));
        }

        addHandlers(root);
    }

    private void addHandlers(Parent root){
        ImageView up = (ImageView) root.lookup("#up");
        ImageView down = (ImageView) root.lookup("#down");
        Button change = (Button) root.lookup("#change");
        VBox cardHolder = ((VBox)root.lookup("#vBox"));

        EventHandler<MouseEvent> upHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                    up.setFitHeight(55);
                } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
                    up.setFitHeight(50);
                } else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                    for (int i = 0; i < 2; i++){
                        if (cardHolder.getChildren().size() >= 1)
                            cardHolder.getChildren().remove(0);
                    }
                    index--;
                    for (int i = 0; i < 2; i++){
                        if (index >= 0);
                            cardHolder.getChildren().add(cardBoxes.get(index + i));
                    }
                }
            }
        };
        up.addEventHandler(MouseEvent.ANY, upHandler);

        EventHandler<MouseEvent> downHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                    down.setFitHeight(55);
                } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
                    down.setFitHeight(50);
                } else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                    for (int i = 0; i < 2; i++){
                        if (cardHolder.getChildren().size() >= 1)
                            cardHolder.getChildren().remove(0);
                    }
                    index++;
                    for (int i = 0; i < 2; i++){
                        if (cardBoxes.size() > index + i);
                            cardHolder.getChildren().add(cardBoxes.get(index + i));
                    }
                }
            }
        };
        down.addEventHandler(MouseEvent.ANY, downHandler);

        EventHandler<MouseEvent> changeHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                    change.setEffect(new Glow(.4));
                } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
                    change.setEffect(null);
                } else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                    removeHandler();
                    Menu.getInstance().goBacktoMenu();
                }
            }
        };
        change.addEventHandler(MouseEvent.ANY, changeHandler);
    }

    private void setCardHandler(Card card){
        cardEvents.add(new CardEventHandler(card));
    }

    private void removeCardInfo(HBox bHold, Parent finalRoot){
        ((AnchorPane) Graphics.getInstance().getStage().getScene().getRoot()).getChildren()
                .removeAll(bHold, finalRoot);
    }

    private void removeHandler(){
        for (int i = 0; i < user.getCardInventory().getCards().size(); i++){
            cardEvents.get(i).remove();
        }
    }

    class CardEventHandler{
        private EventHandler<MouseEvent> okHandler;
        private EventHandler<MouseEvent> moveHandler;
        private EventHandler<MouseEvent> diseqiupHandler;
        private EventHandler<MouseEvent> cardHandler;

        private Button okButton;
        private Button equip;
        private Button disequip;

        private Card card;

        EventHandler<MouseEvent> getEvent(){
            return cardHandler;
        }

        CardEventHandler(Card card){
            cardHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                    card.getCardView().getMainVBox().setEffect(new Glow(0.4));
                } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
                    card.getCardView().getMainVBox().setEffect(null);
                } else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                    cardInfo(card);
                }
            }
            };
            this.card = card;
            card.getCardView().getMainVBox().addEventHandler(MouseEvent.ANY, cardHandler);
        }

        private void cardInfo(Card card){
            Parent root = null;
            try {
                root = FXMLLoader.load(getClass().getResource("../../Files/Resources/CardInfoPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert root!=null;
            GridPane cardBox = (GridPane) root.lookup("#dialogBox");
            cardBox.getChildren().add(card.getCardViewBig().getMainVBox());
            ((AnchorPane)Graphics.getInstance().getStage().getScene().getRoot()).getChildren().add(root);
            okButton = new Button();
            okButton.setStyle("-fx-background-color: #69443c; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-font-size: 23; -fx-font-family: Purisa;" +
                    "-fx-font-weight: bold;");
            okButton.setText("OK");
            okButton.setTextFill(Color.CORNSILK);
            equip = new Button("Equip");
            equip.setStyle("-fx-background-color: #69443c; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-font-size: 23; -fx-font-family: Purisa;" +
                    "-fx-font-weight: bold;");
            equip.setTextFill(Color.CORNSILK);

            disequip = new Button("disequip");
            disequip.setStyle("-fx-background-color: #69443c; " +
                    "-fx-background-radius: 10px; " +
                    "-fx-font-size: 23; -fx-font-family: Purisa;" +
                    "-fx-font-weight: bold;");
            disequip.setTextFill(Color.CORNSILK);

            HBox bHold = null;
            if (user.getDeck().getNumberOfCards(card) == user.getCardInventory().getNumberOfCards(card)) {
                bHold = new HBox(okButton, disequip);
                bHold.setLayoutX(Graphics.SCREEN_WIDTH/2 - 100);
                bHold.setLayoutY(Graphics.SCREEN_HEIGHT - 60);
            }
            else if (user.getDeck().contains(card.getName())){
                bHold = new HBox(okButton, equip, disequip);
                bHold.setLayoutX(Graphics.SCREEN_WIDTH/2 - 160);
                bHold.setLayoutY(Graphics.SCREEN_HEIGHT - 60);
            }else {
                bHold = new HBox(okButton, equip);
                bHold.setLayoutX(Graphics.SCREEN_WIDTH/2 - 100);
                bHold.setLayoutY(Graphics.SCREEN_HEIGHT - 60);
            }
            bHold.setSpacing(50);
            Effect glow = new Glow(.4);
            ((AnchorPane) Graphics.getInstance().getStage().getScene().getRoot()).getChildren().add(bHold);

            Parent finalRoot = root;
            HBox finalBHold = bHold;
            okHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent event) {
                    if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED))
                        removeCardInfo(finalBHold, finalRoot);
                    if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
                        okButton.setEffect(glow);
                    if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
                        okButton.setEffect(null);
                }
            };
            HBox finalBHold1 = bHold;
            moveHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent event) {
                    if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                        user.getDeck().add(card, 1);
                        removeCardInfo(finalBHold1, finalRoot);
                    }
                    if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
                        equip.setEffect(glow);
                    if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
                        equip.setEffect(null);
                }
            };

            diseqiupHandler = new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent event) {
                    if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                        user.getDeck().remove(card);
                        removeCardInfo(finalBHold1, finalRoot);
                    }
                    if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
                        disequip.setEffect(glow);
                    if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
                        disequip.setEffect(null);
                }
            };

            okButton.addEventHandler(MouseEvent.ANY, okHandler);
            equip.addEventHandler(MouseEvent.ANY, moveHandler);
            disequip.addEventHandler(MouseEvent.ANY, diseqiupHandler);
        }

        public void remove(){
            card.getCardView().getMainVBox().removeEventHandler(MouseEvent.ANY, cardHandler);
        }
    }
}
