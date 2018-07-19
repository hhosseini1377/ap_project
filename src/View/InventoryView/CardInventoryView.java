package View.InventoryView;

import Modules.Card.Card;
import Modules.Graphic.Graphics;
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
    public void viewInventory(ArrayList<Card> nonEquipped, ArrayList<Card> equipped){

        HBox row1  = HBoxConstructor(nonEquipped , Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT * 2 / 12);
        HBox row2 =  HBoxConstructor(equipped , Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT * 7 / 12);



        ImageView previousButtonUpper = new ImageView(new Image("Files/Images/viewInventory/previousButton.png"));
        previousButtonUpper.setX(Graphics.SCREEN_WIDTH / 36);
        previousButtonUpper.setY(Graphics.SCREEN_HEIGHT * 3 / 12);
        ImageView nextButtonUpper = new ImageView(new Image("Files/Images/viewInventory/nextButton.png"));
        nextButtonUpper.setX(Graphics.SCREEN_WIDTH * 33 / 36);
        nextButtonUpper.setY(Graphics.SCREEN_HEIGHT * 3 / 12);

        ImageView previousButtonLower = new ImageView(new Image("Files/Images/viewInventory/previousButton.png"));
        previousButtonLower.setX(Graphics.SCREEN_WIDTH  / 36);
        previousButtonLower.setY(Graphics.SCREEN_HEIGHT * 8 / 12);
        ImageView nextButtonLower = new ImageView(new Image("Files/Images/viewInventory/nextButton.png"));
        nextButtonLower.setX(Graphics.SCREEN_WIDTH * 33 / 36);
        nextButtonLower.setY(Graphics.SCREEN_HEIGHT * 8 / 12);



        Pane fullScreen = new Pane(row1, row2, previousButtonLower, previousButtonUpper, nextButtonLower, nextButtonUpper);
        fullScreen.setPrefSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        fullScreen.setStyle("-fx-background-image: url(Files/Images/CardShopBackground.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");


        Timeline timeline = new Timeline();
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1), moveEvent -> {

            previousButtonLower.setOnMouseClicked(event -> row2.setLayoutX(row2.getLayoutX() - 20));
            nextButtonLower.setOnMouseClicked(event -> row2.setLayoutX(row2.getLayoutX() + 20));

            previousButtonUpper.setOnMouseClicked(event -> row1.setLayoutX(row1.getLayoutX() - 20));
            nextButtonUpper.setOnMouseClicked(event -> row1.setLayoutX(row1.getLayoutX() + 20));

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

            Pane paneBigCard = new Pane();
            paneBigCard.setPrefSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
            paneBigCard.setStyle("-fx-background-image: url(Files/Images/viewInventory/backgroundBigCard.jpg);-fx-background-size: stretch; -fx-background-repeat: no-repeat; ");

            Button returnButton = new Button("Return");
            returnButton.setPrefSize(Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT / 12);
            returnButton.setStyle("-fx-background-color: #69443c; -fx-text-fill: cornsilk");
            returnButton.setLayoutX(Graphics.SCREEN_WIDTH * 13 / 36);
            returnButton.setLayoutY(Graphics.SCREEN_HEIGHT * 31 / 36);
            Button equipButton = new Button("Equip");
            equipButton.setPrefSize(Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT / 12);
            equipButton.setStyle("-fx-background-color: #69443c; -fx-text-fill: cornsilk");
            equipButton.setLayoutX(Graphics.SCREEN_WIDTH * 19 / 36);
            equipButton.setLayoutY(Graphics.SCREEN_HEIGHT * 31 / 36);
            paneBigCard.getChildren().addAll(returnButton, equipButton);

            for (int i = 0; i < row1.getChildren().size(); i++) {
                int finalI = i;
                row1.getChildren().get(i).setOnMouseClicked(event -> {
                    nonEquipped.get(finalI).getCardViewBig().getMainVBox().setLayoutX(Graphics.SCREEN_WIDTH * 6 /18);
                    nonEquipped.get(finalI).getCardViewBig().getMainVBox().setLayoutY(Graphics.SCREEN_HEIGHT * 1 /12);

                    paneBigCard.getChildren().addAll(nonEquipped.get(finalI).getCardViewBig().getMainVBox());
                    fullScreen.getChildren().add(paneBigCard);

                });
            }
            for (int i = 0; i < row2.getChildren().size(); i++) {
                int finalI = i;
                row2.getChildren().get(i).setOnMouseClicked(event -> {
                    equipped.get(finalI).getCardViewBig().getMainVBox().setLayoutX(Graphics.SCREEN_WIDTH * 6 /18);
                    equipped.get(finalI).getCardViewBig().getMainVBox().setLayoutY(Graphics.SCREEN_HEIGHT * 1 /12);

                    paneBigCard.getChildren().addAll(equipped.get(finalI).getCardViewBig().getMainVBox());
                    fullScreen.getChildren().add(paneBigCard);
                });
            }

            returnButton.setOnMouseClicked(event -> {
                fullScreen.getChildren().remove(paneBigCard);
                paneBigCard.getChildren().remove(paneBigCard.getChildren().size() - 1);
            });

            equipButton.setOnMouseClicked(event -> {

            });

        });
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
            row.getChildren().add(card.getCardView().getMainVBox());
        }

        return row;
    }
}
