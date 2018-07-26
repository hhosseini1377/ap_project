package View.BattleGroundView;

import Modules.BattleGround.Fields.MonsterField;
import Modules.Card.Card;
import Modules.Card.Monsters.Monster;
import Modules.Card.Spell.Spell;
import Modules.Graphic.Graphics;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;

public class HandView {
    private HBox hand;

    public HBox getHand () {
        return hand;
    }

    public void setHand (HBox hand) {
        this.hand = hand;
    }

    public void addToHand(Card card, boolean isOpponent){
//        if (hand.getChildren().contains(new ImageView(card.getCardImage()))) {
//            card.setCardImage(card.renew().getCardImage());
//        }
        ImageView cardImageView = null;
        if (isOpponent){
            cardImageView = new ImageView(Graphics.CARD_BACK);
        }else
            cardImageView = new ImageView(card.getCardImage());
        ImageView finalCardImageView = cardImageView;
        cardImageView.setOnMouseEntered(event -> finalCardImageView.setEffect(new Glow(.4)));
        cardImageView.setOnMouseExited(event -> finalCardImageView.setEffect(null));
        EventHandler<MouseEvent> clickOnCard = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                    CardInfo(card);
                }
            }
        };
        if (!isOpponent) {
            cardImageView.addEventHandler(MouseEvent.MOUSE_CLICKED, clickOnCard);
        }
        cardImageView.setFitWidth(50);
        cardImageView.setFitHeight(80);
        hand.getChildren().add(cardImageView);
        int size = hand.getChildren().size();
        double rotation = (isOpponent?1:-1)*30;
        for (Node node: hand.getChildren()){
            node.setRotate(rotation);
            double trans = (isOpponent?-1:1) * (rotation >= 0 ? rotation : -rotation);
            node.setTranslateY(trans*20.0/30);
            node.setTranslateX(-rotation*10.0/30);
            rotation = rotation + (isOpponent?-1:1) * 60.0/(size-1);
        }
    }

    public void removeFromHand(Card card, boolean isOpponent){
        if (doesContain(card.getCardImage()) || isOpponent){
            ImageView cardImageView;
            if (isOpponent)
                cardImageView = getView(Graphics.CARD_BACK);
            else
                cardImageView = getView(card.getCardImage());
            hand.getChildren().remove(cardImageView);
            int size = hand.getChildren().size();
            double rotation = (isOpponent?1:-1)*30;
            for (Node node: hand.getChildren()){
                node.setRotate(rotation);
                double trans = (isOpponent?-1:1) * (rotation >= 0 ? rotation : -rotation);
                node.setTranslateY(trans*20.0/30);
                node.setTranslateX(-rotation*10.0/30);
                rotation = rotation + (isOpponent?-1:1) * 60.0/(size-1);
            }
        }
    }

    private boolean doesContain(Image image){
        for(Node node: hand.getChildren()){
            if (((ImageView) node).getImage() == image){
                return true;
            }
        }
        return false;
    }

    private ImageView getView(Image image){
        for(Node node: hand.getChildren()){
            if (((ImageView) node).getImage() == image){
                return (ImageView) node;
            }
        }
        return null;
    }

    private void CardInfo(Card card){
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
        Button okButton = new Button();
        okButton.setStyle("-fx-background-color: #69443c; " +
                "-fx-background-radius: 10px; " +
                "-fx-font-size: 23; -fx-font-family: Purisa;" +
                "-fx-font-weight: bold;");
        okButton.setText("OK");
        okButton.setTextFill(Color.CORNSILK);
        Button moveToField = new Button("Move To Field");
        moveToField.setStyle("-fx-background-color: #69443c; " +
                "-fx-background-radius: 10px; " +
                "-fx-font-size: 23; -fx-font-family: Purisa;" +
                "-fx-font-weight: bold;");
        moveToField.setTextFill(Color.CORNSILK);

        HBox bHold = new HBox(okButton, moveToField);
        bHold.setSpacing(50);
        Effect glow = new Glow(.4);
        ((AnchorPane) Graphics.getInstance().getBattle().getRoot()).getChildren().add(bHold);
        bHold.setLayoutX(Graphics.SCREEN_WIDTH/2 - 170);
        bHold.setLayoutY(Graphics.SCREEN_HEIGHT - 70);

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
                    moveToField(card);
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

    private void removeCardInfo(HBox bHold, Parent finalRoot){
        ((AnchorPane) Graphics.getInstance().getBattle().getRoot()).getChildren()
                .removeAll(bHold, finalRoot);
    }

    private void moveToField(Card card){
        if (card instanceof Monster){
            if (card.getFriend().getManaPoint() >= card.getManaPoint()) {
                    new MonsterFieldSlotController(card.getFriend()
                            .getMonsterField()
                            .getMonsterFieldView()
                            .getFieldView(), card);
            }else
                Graphics.getInstance().notifyMessage("not enough MP", "notify");
        }else{
            card.getFriend().getSpellField().add((Spell) card, -1);
        }
    }
}

class MonsterFieldSlotController{
    private HBox field;
    private ArrayList<EventHandler<MouseEvent>> onClicks = new ArrayList<>();

    MonsterFieldSlotController(HBox field, Card card){
        if (card.getFriend().getManaPoint() >= card.getManaPoint()) {
            this.field = field;
            for (int i = 0; i < 5; i++) {
                HBox slot = (HBox) field.getChildren().get(i);
                int finalI = i;
                EventHandler<MouseEvent> onClick = new EventHandler<MouseEvent>() {
                    @Override
                    public void handle (MouseEvent event) {
                        if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                            slot.setStyle("-fx-background-color: rgba(36,33,22,0.92);");
                        } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
                            slot.setStyle("-fx-background-color: rgba(28,26,18,0.74);");
                        } else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                            if (card.getFriend().getMonsterField().add((Monster) card, finalI + 1)) {
                                //removing event handlers
                                removeEventHandlers();
                                //reducing the necessary MP
                                card.getFriend().setManaPoint(card.getFriend().getManaPoint() - card.getManaPoint());
                                //removing from hand
                                card.getFriend().getHand().remove(card);
                            }
                        }
                    }
                };
                onClicks.add(onClick);
                slot.addEventHandler(MouseEvent.ANY, onClick);
            }

        }else
            Graphics.getInstance().notifyMessage("not enough MP", "notify");
    }

    private void removeEventHandlers(){
        for (int i = 0; i < 5; i++){
            HBox slot = (HBox) field.getChildren().get(i);
            slot.removeEventHandler(MouseEvent.ANY, onClicks.get(i));
        }
    }
}