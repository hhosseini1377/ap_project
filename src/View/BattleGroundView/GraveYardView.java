package View.BattleGroundView;

import Modules.Card.Card;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;

public class GraveYardView {
    private ImageView graveyardView;

    public ImageView getGraveyardView () {
        return graveyardView;
    }

    public void setGraveyardView (ImageView graveyardView) {
        this.graveyardView = graveyardView;
    }

    public void viewGraveyard(Warrior owner){
        Parent root = null;
        try {
            root = FXMLLoader.load(getClass().getResource("../../Files/Resources/Graveyard.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert root != null;
        //changing scene
        Graphics.getInstance().setGraveyard(new Scene(root));
        Graphics.getInstance().getStage().setScene(Graphics.getInstance().getGraveyard());
        //setting up the back button
        Button back = (Button) root.lookup("#backButton");
        EventHandler<MouseEvent> backHandler = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                    Graphics.getInstance().getStage().setScene(Graphics.getInstance().getBattle());
                    Graphics.getInstance().getStage().setFullScreen(true);
                }
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED))
                    back.setEffect(new Glow(.5));
                if (event.getEventType().equals(MouseEvent.MOUSE_EXITED))
                    back.setEffect(null);
            }
        };
        back.addEventHandler(MouseEvent.ANY, backHandler);
        Graphics.getInstance().getStage().setFullScreen(true);

        //resizing the card holder vbox according to the page
        HBox cardHolder = (HBox) root.lookup("#cardHolder");
        cardHolder.setMinSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT - 50);
        cardHolder.setMaxSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT - 50);

        int cardNum = owner.getGraveYard().getDestroyedCards().size();
        VBox holderCol1 = new VBox(40);
        VBox holderCol2 = new VBox(40);
        VBox holderCol3 = new VBox(40);
        VBox holderCol4 = new VBox(40);
        for (int i = 0; i < cardNum; i++){
            Card card = owner.getGraveYard().getDestroyedCards().get(i);
            card.getCardView().getMainVBox().setOnMouseEntered(event -> card.getCardView().getMainVBox().setEffect(new Glow(.4)));
            card.getCardView().getMainVBox().setOnMouseExited(event -> card.getCardView().getMainVBox().setEffect(null));
            EventHandler<MouseEvent> cardInfo = new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent event) {
                    if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                            CardInfo(card);
                    }
                }
            };
            card.getCardView().getMainVBox().addEventHandler(MouseEvent.MOUSE_CLICKED, cardInfo);
            switch (i%4){
                case 0:
                    holderCol1.getChildren().add(card
                            .getCardView().getMainVBox());
                    break;
                case 1:
                    holderCol2.getChildren().add(card
                            .getCardView().getMainVBox());
                    break;
                case 2:
                    holderCol3.getChildren().add(card
                            .getCardView().getMainVBox());
                    break;
                case 3:
                    holderCol4.getChildren().add(card
                            .getCardView().getMainVBox());
                    break;
            }
        }
        cardHolder.getChildren().addAll(holderCol1, holderCol2, holderCol3, holderCol4);
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
        ((AnchorPane) Graphics.getInstance().getGraveyard().getRoot()).getChildren().add(bHold);
        bHold.setLayoutX(Graphics.SCREEN_WIDTH/2 - 30);
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
        okButton.addEventHandler(MouseEvent.ANY, okHandler);
    }

    private void removeCardInfo(HBox bHold, Parent finalRoot){
        ((AnchorPane) Graphics.getInstance().getGraveyard().getRoot()).getChildren()
                .removeAll(bHold, finalRoot);
    }
}
