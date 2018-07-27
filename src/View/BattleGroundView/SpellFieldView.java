package View.BattleGroundView;

import Modules.Card.Card;
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
import javafx.scene.paint.Color;

import java.io.IOException;

public class SpellFieldView {
    private HBox[] spells;
    private EventHandler<MouseEvent> cardInfo;

    public HBox[] getSpells () {
        return spells;
    }

    public void setSpells (HBox[] spells) {
        this.spells = spells;
    }

    public void addToField(Card card, int slotNum){
        card.setCardView(new CardView(120,180,card.getCardImage(),card,0,0));
        spells[slotNum].getChildren().add(card.getCardView().getMainVBox());
        //adding effects and event handlers to card view
        card.getCardView().getMainVBox().setOnMouseEntered(event -> card.getCardView().getMainVBox().setEffect(new Glow(.4)));
        card.getCardView().getMainVBox().setOnMouseExited(event -> card.getCardView().getMainVBox().setEffect(null));
        cardInfo = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                    if (!card.getFriend().getMonsterField().isEnemy())
                        CardInfo(card);
                    else
                        EnemyCardInfo(card);
                }
            }
        };
        card.getCardView().getMainVBox().addEventHandler(MouseEvent.MOUSE_CLICKED, cardInfo);
    }

    public void removeFromField(Card card, int slot){
        card.getCardView().getMainVBox().removeEventHandler(MouseEvent.MOUSE_CLICKED, cardInfo);
        spells[slot].getChildren().remove(card.getCardView().getMainVBox());
        card.setCardView(new CardView(Graphics.SCREEN_WIDTH * 3 / 18,Graphics.SCREEN_HEIGHT * 5 / 12,card.getCardImage(),card,0,0,false));
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

        HBox bHold = new HBox(okButton);
        bHold.setSpacing(50);
        Effect glow = new Glow(.4);
        ((AnchorPane) Graphics.getInstance().getBattle().getRoot()).getChildren().add(bHold);
        bHold.setLayoutX(Graphics.SCREEN_WIDTH/2 - 30);
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

    private void EnemyCardInfo(Card card){
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

        HBox bHold = new HBox(okButton);
        bHold.setSpacing(50);
        Effect glow = new Glow(.4);
        ((AnchorPane) Graphics.getInstance().getBattle().getRoot()).getChildren().add(bHold);
        bHold.setLayoutX(Graphics.SCREEN_WIDTH/2 - 30);
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

    private void removeCardInfo(HBox bHold, Parent finalRoot){
        ((AnchorPane) Graphics.getInstance().getBattle().getRoot()).getChildren()
                .removeAll(bHold, finalRoot);
    }

}
