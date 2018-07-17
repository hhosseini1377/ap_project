package View.ShopView;

import Control.ShopControl;
import Modules.Card.Card;
import java.util.ArrayList;
import java.util.HashMap;
import Modules.Card.Monsters.DragonBreed.BlueDragon;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class CardShopView {
    private ShopControl shopControl;
    private ArrayList<Card> availableCards = new ArrayList<>();
    private ArrayList<CardView> cardImages = new ArrayList<>();
    private Group cardShopGroup = new Group();
    private Scene cardShopScene = new Scene(cardShopGroup);
    public CardShopView(ShopControl shopControl){
        this.shopControl = shopControl;
    }

    public void shopEntrance(){
        cardShopScene.setRoot(cardShopGroup);
        availableCards = new ArrayList<>();
        cardImages = new ArrayList<>();

        ImageView backGround = new ImageView(new Image("Files/Images/CardShopBackground.jpg"));
        backGround.fitWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));
        backGround.fitHeightProperty().bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 1));
        cardShopGroup.getChildren().add(backGround);

        ImageView cardShopIcon = new ImageView(new Image("Files/Images/ShopImages/cardShopIcon.png"));

        ImageView exitIcon = new ImageView(new Image("Files/Images/ShopImages/Exit.png"));
        exitIcon.setPreserveRatio(true);
        exitIcon.setFitWidth(60);
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
            cardImages.add(new CardView(Graphics.getInstance().getStage().getWidth() / 7, Graphics.getInstance().getStage().getWidth() / 7 * 1.5, new Image("Files/Images/Battle/goblin.png"), card, 0, 0));

        }

        for(CardView cardView : cardImages){
            cardView.getVBox().setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    if(shopControl.getUser().canBuy(cardView.getCard().getGillCost()))
                        buyCard(cardView.getCard().getName());
                    else
                        cantBuy(cardView.getCard().getName());
                }
            });
        }

        VBox vBox1 = new VBox(50);
        VBox vBox2 = new VBox(50);
        VBox vBox3 = new VBox(50);
        VBox vBox4 = new VBox(50);

        for (int counter = 0;counter < availableCards.size();counter++) {
            if (counter % 4 == 0)
                    vBox1.getChildren().add(cardImages.get(counter).getVBox());
                else if (counter % 4 == 1)
                    vBox2.getChildren().add(cardImages.get(counter).getVBox());
                else if (counter % 4 == 2)
                    vBox3.getChildren().add(cardImages.get(counter).getVBox());
                else if (counter % 4 == 3)
                    vBox4.getChildren().add(cardImages.get(counter).getVBox());
            }


        HBox hBox = new HBox(50);
        hBox.getChildren().addAll(vBox1,vBox2,vBox3,vBox4);

        Text remainGills = new Text("remained gills:" + Integer.toString(shopControl.getUser().getGills()));
//        ImageView gillImage = new ImageView(new Image("Files/Images/ShopImages/gill.jpg"));
//        gillImage.setFitWidth(20);
//        gillImage.setPreserveRatio(true);

        VBox gillVBox = new VBox();
        gillVBox.getChildren().addAll(remainGills);

        VBox screenVBox = new VBox();
        screenVBox.getChildren().addAll(remainGills,cardShopIcon,hBox);
        screenVBox.setAlignment(Pos.CENTER);
        screenVBox.setLayoutX(250);
        screenVBox.setLayoutY(50);


        ScrollBar sb = new ScrollBar();
        sb.setOrientation(Orientation.VERTICAL);
        sb.setLayoutX(0);
        sb.setPrefHeight(Graphics.getInstance().getStage().getHeight());
        sb.setMax(9200);
        sb.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                screenVBox.setLayoutY(-newValue.doubleValue());
            }
        });

        cardShopGroup.getChildren().addAll(sb,screenVBox);

        Graphics.getInstance().getStage().setScene(cardShopScene);
    }


    //this method calls when we want to check if user wants to buy the card

    private void buyCard(String cardName){

        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        cardShopScene.setRoot(gridPane);
        cardShopScene.setFill(Color.BLACK);

        gridPane.setStyle("-fx-background-image: url(/Files/Images/CardShopBackground.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");


        ImageView form = new ImageView(new Image("Files/Images/BackGround/DialogueBg.jpeg"));
        Text text = new Text("Are you sure you want to buy " + cardName + " from the Shop?");
//        text.setFont(Font.font(16));
        text.setFill(Color.WHITE);
        Button yesButton = new Button("Yes");
        Button noButton = new Button("No");
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

        gridPane.setStyle("-fx-background-image: url(/Files/Images/CardShopBackground.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");


        Text text = new Text("You don't have enough gills to buy " + cartName);
        ImageView form = new ImageView(new Image("Files/Images/BackGround/DialogueBg.jpeg"));
        Button returnButton = new Button("return to Card Shop");
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





    /**
     * entrance Print
     * @param availableShopCards Comes from shopcontroller
     */
    public void PrintCardShopDetails(ArrayList<Card> availableShopCards,HashMap<String,Integer> numberOfCards) {
        int CardNumber = 1;
        System.out.println("\nAvailable Cards:");
        for (Card card : availableShopCards) {
            System.out.println(CardNumber++ + ". " +  card.getName() + " " +card.getGillCost());
        }
        System.out.println("\nInventory Cards:");
        numberOfCards.forEach((key,value) -> {
            System.out.println(key + " " + value);
        });
    }

    public void PrintCardShopHelpDetails() {
        System.out.println("1. Buy \"Card Name\" - #NumberToBuy: To buy a certain number of a card from shop");
        System.out.println("2. Sell \"Card Name\" - #NumberToSell: To sell a certain number of a card from inventory");
        System.out.println("3. Info \"Card Name\" To get more information about a card");
        System.out.println("4. Edit Deck: To edit Deck and remove and add cards to it");
        System.out.println("5. Exit: To return to shop menu");
    }

    public void printBuyCardsDetails(boolean canbuy, int NumberOfBuy, String cardName) {
        if (canbuy){
            System.out.println("Successfully bought " + NumberOfBuy + " of " + cardName + "s");
        } else {
            System.out.println("Not enough Gill!");
        }
    }

    public void printSellCardDetails(boolean canSell,int NumberOfSell,String cardName){
        if(canSell){
            System.out.println("Successfully sold " + NumberOfSell + " of " + cardName + "s");
        }
        else{
            System.out.println("Not enough Cards!");
        }
    }

    public void printInfoOfMonsterCard(String cardName,int defaultHP,int defaultAP,int MPcost,MonsterKind monsterKind,MonsterTribe monsterTribe,boolean isDefencive,boolean isNimble,String Details){
        System.out.println(cardName + " Info:");
        System.out.println("Name: " + cardName);
        System.out.println("HP: " + defaultHP);
        System.out.println("AP: " + defaultAP);
        System.out.println("Card Type: " + monsterKind);
        System.out.println("Card Tribe: " + monsterTribe);
        System.out.println(Details);
    }


    public void printInfoOfSpellCard(String cardName,int MPCost,String details){
        System.out.println(cardName + "Info: ");
        System.out.println("Name: " + cardName);
        System.out.println("MPCost: " + MPCost);
        System.out.println(details);
    }

    public void printEditDeckDetails(HashMap<String,Integer> numberOfCardsInInventory,ArrayList<Card> deckCards){
        //prints deck cards
        int numberOfCards =0;
        System.out.println("Deck: ");
        for (Card card:deckCards)
            System.out.println("Slot " + numberOfCards++ +": " + card.getName());
        //prints inventory cards
        System.out.println("Inventory Cards:");
        numberOfCardsInInventory.forEach((key,value) -> {
            System.out.println("Card name:" + key + " Number of Cards: " + value);
        });
        //TODO its incomplete
    }

    public void viewInventory(){

        HBox[] cardRows = new HBox[2];
        cardRows[0] = new HBox();
        cardRows[0].setSpacing(Graphics.SCREEN_WIDTH / 18);
        cardRows[0].setPrefSize(Graphics.SCREEN_WIDTH * 14 / 18, Graphics.SCREEN_HEIGHT * 3 / 12);
        cardRows[1] = new HBox();
        cardRows[1].setSpacing(Graphics.SCREEN_WIDTH / 18);
        cardRows[1].setPrefSize(Graphics.SCREEN_WIDTH * 14 / 18, Graphics.SCREEN_HEIGHT * 3 / 12);

        VBox cardRowColumn = new VBox();
        cardRowColumn.setLayoutX(Graphics.SCREEN_WIDTH  * 2 / 12);
        cardRowColumn.setLayoutY(Graphics.SCREEN_HEIGHT * 2 / 12);
        cardRowColumn.setSpacing(Graphics.SCREEN_HEIGHT * 2 / 12);
        cardRowColumn.setPrefSize(Graphics.SCREEN_WIDTH * 14 / 18, Graphics.SCREEN_HEIGHT * 8 / 12);

        cardRowColumn.getChildren().addAll(cardRows[0], cardRows[1]);

        VBox fullScreen = new VBox(cardRowColumn);
        fullScreen.setAlignment(Pos.CENTER);
        fullScreen.setPrefSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        //fullScreen.setStyle("-fx-background-image: url(Files/Images/CardShopBackground.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");

        BlueDragon blueDragon = new BlueDragon();
        CardView testCard = null;

        testCard = new CardView(Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT * 3 / 12, new Image("Files/Images/Blue-Eyes-White-Dragon.jpg"), blueDragon, 0, 0);

        cardRows[0].getChildren().add(testCard.getVBox());
        cardRows[0].getChildren().add(testCard.getVBox());


        Graphics.getInstance().setCardShopScene(new Scene(fullScreen));

        Graphics.getInstance().getStage().setScene(Graphics.getInstance().getCardShopScene());
    }

}