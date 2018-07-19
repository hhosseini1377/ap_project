package View.ShopView;

import Control.ShopControl;
import Modules.Card.Card;
import java.util.ArrayList;
import Modules.Card.Monsters.DragonBreed.BlueDragon;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollBar;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class CardShopView {
    private ShopControl shopControl;
    private ArrayList<Card> availableCards = new ArrayList<>();
    private ArrayList<CardView> cardImages = new ArrayList<>();
    private Group cardShopGroup = new Group();
    private Scene cardShopScene = new Scene(cardShopGroup);
    private Glow cardGlow = new Glow(0.5);
    public CardShopView(ShopControl shopControl){
        this.shopControl = shopControl;
    }

    public void shopEntrance(){
        cardShopScene.setRoot(cardShopGroup);
        availableCards = new ArrayList<>();
        cardImages = new ArrayList<>();

        ImageView backGround = new ImageView(new Image("Files/Images/BackGround/cardShopBackGround.jpg"));
        backGround.fitWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));
        backGround.fitHeightProperty().bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 1));
        cardShopGroup.getChildren().add(backGround);

        ImageView cardShopIcon = new ImageView(new Image("Files/Images/ShopImages/cardShopIcon.png"));

        ImageView exitIcon = new ImageView(new Image("Files/Images/ShopImages/Exit.png"));
        exitIcon.setPreserveRatio(true);
        exitIcon.setFitWidth(60);
        exitIcon.setLayoutX(Graphics.getInstance().getStage().getWidth()-65);
        exitIcon.setLayoutY(-5);
        cardShopGroup.getChildren().add(exitIcon);

        exitIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Menu.getInstance().mainMenu();
                }
                catch (Exception e){
                    System.out.println(e);
                }
            }
        });

        for (Card card : shopControl.getCardShop().getCards()) {
            if(!availableCards.contains(card))
                availableCards.add(card);
        }

        for (Card card : availableCards) {
            cardImages.add(new CardView(Graphics.getInstance().getStage().getWidth() / 7, Graphics.getInstance().getStage().getWidth() / 7 * 1.5, new Image("Files/Images/Battle/goblin.png"), card, 0, 0,false));

        }

        for(CardView cardView : cardImages){
//            cardView.getMainVBox().setOnMouseClicked(new EventHandler<MouseEvent>() {
//                @Override
//                public void handle(MouseEvent event) {
//                    buyCard(cardView.getCard());
//                }
//            });

            cardView.getMainVBox().setOnMouseEntered(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    cardView.getMainVBox().setEffect(cardGlow);
                }
            });

            cardView.getMainVBox().setOnMouseExited(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    cardView.getMainVBox().setEffect(null );
                }
            });
        }

        VBox vBox1 = new VBox(50);
        VBox vBox2 = new VBox(50);
        VBox vBox3 = new VBox(50);
        VBox vBox4 = new VBox(50);

        for (int counter = 0;counter < availableCards.size();counter++) {
            if (counter % 4 == 0)
                    vBox1.getChildren().add(cardImages.get(counter).getMainVBox());
                else if (counter % 4 == 1)
                    vBox2.getChildren().add(cardImages.get(counter).getMainVBox());
                else if (counter % 4 == 2)
                    vBox3.getChildren().add(cardImages.get(counter).getMainVBox());
                else if (counter % 4 == 3)
                    vBox4.getChildren().add(cardImages.get(counter).getMainVBox());
            }


        HBox hBox = new HBox(50);
        hBox.getChildren().addAll(vBox1,vBox2,vBox3,vBox4);

        Text remainGills = new Text(Integer.toString(shopControl.getUser().getGills()));

        ImageView coinImage = new ImageView(new Image("Files/Images/ShopImages/coinIcon.png"));
        coinImage.setFitWidth(60);
        coinImage.setPreserveRatio(true);

        HBox coinHBox = new HBox();
        coinHBox.setAlignment(Pos.CENTER);
        coinHBox.getChildren().addAll(coinImage,remainGills);
        coinHBox.setLayoutX(15);
        coinHBox.setLayoutY(-8);
        cardShopGroup.getChildren().add(coinHBox);

        VBox screenVBox = new VBox();
        screenVBox.getChildren().addAll(cardShopIcon,hBox);
        screenVBox.setAlignment(Pos.CENTER);
        screenVBox.setLayoutX(250);
        screenVBox.setLayoutY(50);

        ScrollBar sb = new ScrollBar();
        sb.setOrientation(Orientation.VERTICAL);
        sb.setLayoutX(0);
        sb.setPrefHeight(Graphics.getInstance().getStage().getHeight());
        sb.setMax(3000);
        sb.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                screenVBox.setLayoutY(-newValue.doubleValue());
            }
        });

        cardShopGroup.getChildren().addAll(sb,screenVBox);

        Graphics.getInstance().getStage().setScene(cardShopScene);
    }

    private void buyCard(Card card){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        cardShopScene.setRoot(gridPane);

        gridPane.setStyle("-fx-background-image: url(/Files/Images/BackGround/cardShopBackGround.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");


        Text text = new Text(card.toString());
        text.setFill(Color.WHITE);

        ImageView form = new ImageView(new Image("Files/Images/BackGround/DialogueBg.jpeg"));
        form.setPreserveRatio(true);
        form.setFitWidth(700);

        Button buyButton = new Button("Buy");
        buyButton.setStyle("-fx-font-family: Purisa; -fx-font-weight: bold; -fx-background-color: #cea57f;");
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-family: Purisa; -fx-font-weight: bold; -fx-background-color: #cea57f;");

        HBox hBox = new HBox(50);
        hBox.getChildren().addAll(buyButton,backButton);
        hBox.setAlignment(Pos.CENTER);

        VBox detailVBox = new VBox(100);
        detailVBox.getChildren().addAll(text,hBox);
        detailVBox.setAlignment(Pos.CENTER);

        gridPane.getChildren().addAll(form,detailVBox);

        buyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(shopControl.getUser().canBuy(card.getGillCost()))
                    canBuy(card.getName());
                else
                    cantBuy(card.getName());
            }
        });

        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                cardShopGroup.getChildren().removeAll(form,detailVBox);
                shopEntrance();

            }
        });
    }

    //this method calls when we want to check if user wants to buy the card
    private void canBuy(String cardName){

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        cardShopScene.setRoot(gridPane);

        gridPane.setStyle("-fx-background-image: url(/Files/Images/BackGround/cardShopBackGround.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");


        ImageView form = new ImageView(new Image("Files/Images/BackGround/DialogueBg.jpeg"));
        Text text = new Text("Are you sure you want to buy " + cardName + " from the Shop?");
        text.setFill(Color.WHITE);
        Button yesButton = new Button("Yes");
        yesButton.setStyle("-fx-font-family: Purisa; -fx-font-weight: bold; -fx-background-color: #cea57f;");
        Button noButton = new Button("No");
        noButton.setStyle("-fx-font-family: Purisa; -fx-font-weight: bold; -fx-background-color: #cea57f;");
        HBox askHBox = new HBox(50);
        askHBox.getChildren().addAll(yesButton,noButton);
        askHBox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(100);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text,askHBox);

        gridPane.getChildren().addAll(form,vBox);

        noButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                shopEntrance();
            }
        });

        yesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                shopControl.buyCard(cardName,1);
                shopEntrance();
            }
        });

    }

    //this method calls when user cant buy the card
    private void cantBuy(String cartName){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        cardShopScene.setRoot(gridPane);

        gridPane.setStyle("-fx-background-image: url(/Files/Images/BackGround/cardShopBackGround.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");

        Text text = new Text("You don't have enough gills to buy " + cartName);
        text.setFill(Color.WHITE);

        ImageView form = new ImageView(new Image("Files/Images/BackGround/DialogueBg.jpeg"));
        Button returnButton = new Button("return to Card Shop");
        returnButton.setStyle("-fx-font-family: Purisa; -fx-font-weight: bold; -fx-background-color: #cea57f;");
        VBox vBox = new VBox(50);
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(text,returnButton);

        gridPane.getChildren().addAll(form,vBox);

        returnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                shopEntrance();
            }
        });
    }


}
