package View.InventoryView;

import Modules.Graphic.Graphics;
import Modules.ItemAndAmulet.Item;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

public class ItemInventoryView {

    int itemIndex = 0;

    public void viewItemInventory (ArrayList<Item> nonEquipped, ArrayList<Item> equipped){

        HBox row1  = HBoxConstructor(nonEquipped , Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT * 2.5 / 12);
        HBox row2 =  HBoxConstructor(equipped , Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT * 7.5 / 12);


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

        //////////////////////////////////////////////////////////////////////////////////////

        Button returnButtonEquipped = new Button("Return");
        returnButtonEquipped.setPrefSize(Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT / 12);
        returnButtonEquipped.setStyle("-fx-background-color: #69443c; -fx-text-fill: cornsilk");
        returnButtonEquipped.setLayoutX(Graphics.SCREEN_WIDTH * 5 / 18);
        returnButtonEquipped.setLayoutY(Graphics.SCREEN_HEIGHT * 19 / 24);
        Button returnButtonNonEquipped = new Button("Return");
        returnButtonNonEquipped.setPrefSize(Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT / 12);
        returnButtonNonEquipped.setStyle("-fx-background-color: #69443c; -fx-text-fill: cornsilk");
        returnButtonNonEquipped.setLayoutX(Graphics.SCREEN_WIDTH * 5 / 18);
        returnButtonNonEquipped.setLayoutY(Graphics.SCREEN_HEIGHT * 19 / 24);

        Button unEquipButton = new Button("unEquip");
        unEquipButton.setPrefSize(Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT / 12);
        unEquipButton.setStyle("-fx-background-color: #69443c; -fx-text-fill: cornsilk");
        unEquipButton.setLayoutX(Graphics.SCREEN_WIDTH * 10 / 18);
        unEquipButton.setLayoutY(Graphics.SCREEN_HEIGHT * 19 / 24);
        Button equipButton = new Button("Equip");
        equipButton.setPrefSize(Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT / 12);
        equipButton.setStyle("-fx-background-color: #69443c; -fx-text-fill: cornsilk");
        equipButton.setLayoutX(Graphics.SCREEN_WIDTH * 10 / 18);
        equipButton.setLayoutY(Graphics.SCREEN_HEIGHT * 19 / 24);

        Pane paneDetailNonEquipped = new Pane();
        paneDetailNonEquipped.setPrefSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        paneDetailNonEquipped.setStyle("-fx-background-image: url(Files/Images/viewInventory/backgroundBigCard.jpg);-fx-background-size: stretch; -fx-background-repeat: no-repeat; ");

        Pane paneDetialEquipped = new Pane();
        paneDetialEquipped.setPrefSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        paneDetialEquipped.setStyle("-fx-background-image: url(Files/Images/viewInventory/backgroundBigCard.jpg);-fx-background-size: stretch; -fx-background-repeat: no-repeat; ");

        Pane detailPaneNonEquipped = new Pane();
        detailPaneNonEquipped.setLayoutX(Graphics.SCREEN_WIDTH * 4 / 18);
        detailPaneNonEquipped.setLayoutY(Graphics.SCREEN_HEIGHT * 3 / 12);
        detailPaneNonEquipped.setPrefSize(Graphics.SCREEN_WIDTH * 10 / 18, Graphics.SCREEN_HEIGHT * 6 / 12);
        detailPaneNonEquipped.setStyle("-fx-background-image: url(Files/Images/BackGround/DialogueBg.jpeg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");
        Pane detailPaneEquipped = new Pane();
        detailPaneEquipped.setLayoutX(Graphics.SCREEN_WIDTH * 4 / 18);
        detailPaneEquipped.setLayoutY(Graphics.SCREEN_HEIGHT * 3 / 12);
        detailPaneEquipped.setPrefSize(Graphics.SCREEN_WIDTH * 10 / 18, Graphics.SCREEN_HEIGHT * 6 / 12);
        detailPaneEquipped.setStyle("-fx-background-image: url(Files/Images/BackGround/DialogueBg.jpeg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");

        Text detailTextNonEquipped = new Text();
        detailTextNonEquipped.setX(Graphics.SCREEN_WIDTH * 5 / 18);
        detailTextNonEquipped.setY(Graphics.SCREEN_HEIGHT * 4 /12);
        detailTextNonEquipped.setStyle("-fx-text-fill: cornsilk; -fx-font-size: 20");
        Text detailTextEquipped = new Text();
        detailTextEquipped.setX(Graphics.SCREEN_WIDTH * 5 / 18);
        detailTextEquipped.setY(Graphics.SCREEN_HEIGHT * 4 /12);
        detailTextEquipped.setStyle("-fx-text-fill: cornsilk; -fx-font-size: 20");

        paneDetailNonEquipped.getChildren().addAll(equipButton, returnButtonNonEquipped, detailPaneNonEquipped, detailTextNonEquipped);
        paneDetialEquipped.getChildren().addAll(unEquipButton, returnButtonEquipped, detailPaneEquipped, detailTextEquipped);

        Timeline timeline = new Timeline();
        KeyFrame keyFrame =  new KeyFrame(Duration.millis(10), mainEvent -> {

            previousButtonLower.setOnMouseClicked(event -> row2.setLayoutX(row2.getLayoutX() - 20));
            nextButtonLower.setOnMouseClicked(event -> row2.setLayoutX(row2.getLayoutX() + 20));

            previousButtonUpper.setOnMouseClicked(event -> row1.setLayoutX(row1.getLayoutX() - 20));
            nextButtonUpper.setOnMouseClicked(event -> row1.setLayoutX(row1.getLayoutX() + 20));

            for (int i = 0; i < row1.getChildren().size(); i++) {
                int finalI = i;
                row1.getChildren().get(i).setOnMouseClicked(event -> {

                    detailTextNonEquipped.setStyle("-fx-text-fill: cornsilk; -fx-font-size: 20");
                    detailTextNonEquipped.setText(nonEquipped.get(finalI).toString());

                    fullScreen.getChildren().add(paneDetailNonEquipped);
                    itemIndex = finalI;
                });
            }
            for (int i = 0; i < row2.getChildren().size(); i++) {
                int finalI = i;
                row2.getChildren().get(i).setOnMouseClicked(event -> {

                    detailTextEquipped.setText(equipped.get(finalI).toString());
                    detailTextEquipped.setStyle("-fx-text-fill: cornsilk; -fx-font-size: 20");

                    fullScreen.getChildren().add(paneDetialEquipped);
                    itemIndex = finalI;
                });
            }

            returnButtonEquipped.setOnMouseClicked(event -> fullScreen.getChildren().remove(paneDetialEquipped));
            returnButtonNonEquipped.setOnMouseClicked(event -> fullScreen.getChildren().remove(paneDetailNonEquipped));

            equipButton.setOnMouseClicked(event -> {
                equipped.add(nonEquipped.get(itemIndex));
                nonEquipped.remove(itemIndex);

                row2.getChildren().add(row1.getChildren().get(itemIndex));

                fullScreen.getChildren().remove(paneDetailNonEquipped);
            });
            unEquipButton.setOnMouseClicked(event -> {
                nonEquipped.add(equipped.get(itemIndex));
                equipped.remove(itemIndex);

                row1.getChildren().add(row2.getChildren().get(itemIndex));

                fullScreen.getChildren().remove(paneDetialEquipped);
            });



        });
        timeline.getKeyFrames().add(keyFrame);
        timeline.play();

        Graphics.getInstance().setItemInventoryScene(new Scene(fullScreen));
        Graphics.getInstance().getStage().setScene(Graphics.getInstance().getItemInventoryScene());
    }

    public HBox HBoxConstructor (ArrayList<Item> items, double x, double y){
        HBox row = new HBox(Graphics.SCREEN_WIDTH / 18);
        row.setLayoutX(x);
        row.setLayoutY(y);
        row.setPrefSize(Graphics.SCREEN_WIDTH * 14 / 18, Graphics.SCREEN_HEIGHT * 3 / 12);

        for (Item item: items) {
            item.getItemImage().setFitWidth(Graphics.SCREEN_WIDTH * 2 / 18);
            item.getItemImage().setFitHeight(Graphics.SCREEN_HEIGHT * 2 / 12);
            row.getChildren().add(item.getItemImage());
        }

        return row;
    }
}
