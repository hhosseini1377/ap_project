package View.InventoryView;

import Modules.BattleGround.Deck;
import Modules.Card.Card;
import Modules.Graphic.Graphics;
import Modules.User.Inventory.CardInventory;
import Modules.User.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.util.ArrayList;

public class CardInventoryView {
    private CardInventory inventory;
    private Deck deck;
    private int bigCardIndex = 0;

    public CardInventoryView (CardInventory inventory, Deck deck) {
        this.inventory = inventory;
        this.deck = deck;
    }

    public void viewInventory(){

        ArrayList<Card> nonEquipped = inventory.getCards();
        ArrayList<Card> equipped = deck.getCards();

        HBox row1  = HBoxConstructor(nonEquipped , Graphics.SCREEN_WIDTH * 2 / 18, 20);
        HBox row2 =  HBoxConstructor(equipped , Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT * 7 / 12);



        ImageView previousButtonUpper = new ImageView(new Image("Files/Images/viewInventory/previousButton.png"));
        previousButtonUpper.setX(Graphics.SCREEN_WIDTH / 36);
        previousButtonUpper.setY(Graphics.SCREEN_HEIGHT * 3 / 12);
        previousButtonUpper.setFitHeight(Graphics.SCREEN_HEIGHT / 12);
        previousButtonUpper.setFitWidth(Graphics.SCREEN_WIDTH / 18);
        ImageView nextButtonUpper = new ImageView(new Image("Files/Images/viewInventory/nextButton.png"));
        nextButtonUpper.setX(Graphics.SCREEN_WIDTH * 33 / 36);
        nextButtonUpper.setY(Graphics.SCREEN_HEIGHT * 3 / 12);
        nextButtonUpper.setFitHeight(Graphics.SCREEN_HEIGHT / 12);
        nextButtonUpper.setFitWidth(Graphics.SCREEN_WIDTH / 18);

        ImageView previousButtonLower = new ImageView(new Image("Files/Images/viewInventory/previousButton.png"));
        previousButtonLower.setX(Graphics.SCREEN_WIDTH  / 36);
        previousButtonLower.setY(Graphics.SCREEN_HEIGHT * 8 / 12);
        previousButtonLower.setFitHeight(Graphics.SCREEN_HEIGHT / 12);
        previousButtonLower.setFitWidth(Graphics.SCREEN_WIDTH /18);
        ImageView nextButtonLower = new ImageView(new Image("Files/Images/viewInventory/nextButton.png"));
        nextButtonLower.setX(Graphics.SCREEN_WIDTH * 33 / 36);
        nextButtonLower.setY(Graphics.SCREEN_HEIGHT * 8 / 12);
        nextButtonLower.setFitHeight(Graphics.SCREEN_HEIGHT / 12);
        nextButtonLower.setFitWidth(Graphics.SCREEN_WIDTH / 18);


        Pane fullScreen = new Pane(row1, row2, previousButtonLower, previousButtonUpper, nextButtonLower, nextButtonUpper);
        fullScreen.setPrefSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        fullScreen.setStyle("-fx-background-image: url(Files/Images/BackGround/cardShopBackGround.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat;");

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        Pane paneBigCardNonEquipped = new Pane();
        paneBigCardNonEquipped.setPrefSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        paneBigCardNonEquipped.setStyle("-fx-background-image: url(Files/Images/viewInventory/backgroundBigCard.jpg);-fx-background-size: stretch; -fx-background-repeat: no-repeat; ");

        Pane paneBigCardEquipped = new Pane();
        paneBigCardEquipped.setPrefSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        paneBigCardEquipped.setStyle("-fx-background-image: url(Files/Images/viewInventory/backgroundBigCard.jpg);-fx-background-size: stretch; -fx-background-repeat: no-repeat; ");


        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1), mainEvent -> {

            previousButtonLower.setOnMouseClicked(event -> row2.setLayoutX(row2.getLayoutX() - 20));
            nextButtonLower.setOnMouseClicked(event -> row2.setLayoutX(row2.getLayoutX() + 20));

            previousButtonUpper.setOnMouseClicked(event -> row1.setLayoutX(row1.getLayoutX() - 20));
            nextButtonUpper.setOnMouseClicked(event -> row1.setLayoutX(row1.getLayoutX() + 20));

        });


            for (int i = 0; i < row1.getChildren().size(); i++) {
                new CardsHandler(nonEquipped.get(i), row1, row2, paneBigCardNonEquipped, paneBigCardEquipped, fullScreen, i, true, deck);
            }
            for (int i = 0; i < row2.getChildren().size(); i++) {
                new CardsHandler(equipped.get(i), row1, row2, paneBigCardNonEquipped, paneBigCardEquipped, fullScreen, i, false, deck);
            }


        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        Graphics.getInstance().setCardInventoryScene(new Scene(fullScreen));

        Graphics.getInstance().getStage().setScene(Graphics.getInstance().getCardInventoryScene());
    }

    public HBox HBoxConstructor (ArrayList<Card> cards, double x, double y){
        HBox row = new HBox(Graphics.SCREEN_WIDTH / 18);
        row.setLayoutX(x);
        row.setLayoutY(y);
        row.setPrefSize(Graphics.SCREEN_WIDTH * 14 / 18, Graphics.SCREEN_HEIGHT * 3 / 12);

        for (Card card: cards) {
            if (!row.getChildren().contains(card.getCardView().getMainVBox()))
            row.getChildren().add(card.getCardView().getMainVBox());
        }

        return row;
    }
}


class CardsHandler{
    int index;

    CardsHandler(Card card, HBox row1, HBox row2, Pane paneBigCardNonEquipped, Pane paneBigCardEquipped, Pane fullScreen, int index, boolean is1, Deck deck){

        Button returnButtonEquipped = new Button("Return");
        returnButtonEquipped.setPrefSize(Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT / 12);
        returnButtonEquipped.setStyle("-fx-background-color: #69443c; -fx-text-fill: cornsilk");
        returnButtonEquipped.setLayoutX(Graphics.SCREEN_WIDTH * 13 / 36);
        returnButtonEquipped.setLayoutY(Graphics.SCREEN_HEIGHT * 21 / 24);
        Button returnButtonNonEquipped = new Button("Return");
        returnButtonNonEquipped.setPrefSize(Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT / 12);
        returnButtonNonEquipped.setStyle("-fx-background-color: #69443c; -fx-text-fill: cornsilk");
        returnButtonNonEquipped.setLayoutX(Graphics.SCREEN_WIDTH * 13 / 36);
        returnButtonNonEquipped.setLayoutY(Graphics.SCREEN_HEIGHT * 21 / 24);

        Button unEquipButton = new Button("unEquip");
        unEquipButton.setPrefSize(Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT / 12);
        unEquipButton.setStyle("-fx-background-color: #69443c; -fx-text-fill: cornsilk");
        unEquipButton.setLayoutX(Graphics.SCREEN_WIDTH * 19 / 36);
        unEquipButton.setLayoutY(Graphics.SCREEN_HEIGHT * 21 / 24);
        Button equipButton = new Button("Equip");
        equipButton.setPrefSize(Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT / 12);
        equipButton.setStyle("-fx-background-color: #69443c; -fx-text-fill: cornsilk");
        equipButton.setLayoutX(Graphics.SCREEN_WIDTH * 19 / 36);
        equipButton.setLayoutY(Graphics.SCREEN_HEIGHT * 21 / 24);



        paneBigCardNonEquipped.getChildren().addAll(returnButtonNonEquipped, equipButton);
        paneBigCardEquipped.getChildren().addAll(returnButtonEquipped,unEquipButton);

        if (is1) {
            int finalI = index;
            row1.getChildren().get(index).setOnMouseClicked(event -> {
                card.getCardViewBig().getMainVBox().setLayoutX(Graphics.SCREEN_WIDTH * 6 / 18);
                card.getCardViewBig().getMainVBox().setLayoutY(0);

                paneBigCardNonEquipped.getChildren().addAll(card.getCardViewBig().getMainVBox());
                fullScreen.getChildren().add(paneBigCardNonEquipped);
                this.index = finalI;
            });
        }
        else {
            int finalI = index;
            row2.getChildren().get(index).setOnMouseClicked(event -> {
                card.getCardViewBig().getMainVBox().setLayoutX(Graphics.SCREEN_WIDTH * 6 / 18);
                card.getCardViewBig().getMainVBox().setLayoutY(0);

                paneBigCardEquipped.getChildren().addAll(card.getCardViewBig().getMainVBox());
                fullScreen.getChildren().add(paneBigCardEquipped);
                this.index = finalI;
            });
        }

        returnButtonNonEquipped.setOnMouseClicked(event -> {
            fullScreen.getChildren().remove(paneBigCardNonEquipped);
            paneBigCardNonEquipped.getChildren().remove(paneBigCardNonEquipped.getChildren().size() - 1);
        });
        returnButtonEquipped.setOnMouseClicked(event -> {
            fullScreen.getChildren().remove(paneBigCardEquipped);
            paneBigCardEquipped.getChildren().remove(paneBigCardEquipped.getChildren().size() - 1);
        });

        equipButton.setOnMouseClicked(event -> {

            row2.getChildren().add(row1.getChildren().get(index));

            fullScreen.getChildren().remove(paneBigCardNonEquipped);
            paneBigCardNonEquipped.getChildren().remove(paneBigCardNonEquipped.getChildren().size() - 1);

            deck.add(card, 1);
        });
        unEquipButton.setOnMouseClicked(event -> {
            row1.getChildren().add(row2.getChildren().get(index));

            fullScreen.getChildren().remove(paneBigCardEquipped);
            paneBigCardEquipped.getChildren().remove(paneBigCardEquipped.getChildren().size() - 1);

            deck.remove(card);
        });

    }
    }
