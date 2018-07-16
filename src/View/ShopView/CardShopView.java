package View.ShopView;

import Control.ShopControl;
import Modules.Card.Card;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;

import Modules.Card.Monsters.DragonBreed.BlueDragon;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Graphic.Graphics;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class CardShopView {
    private ShopControl shopControl;
    private ArrayList<VBox> cardImages = new ArrayList<>();
    private Group cardShopGroup = new Group();
    private Scene cardShopScene = new Scene(cardShopGroup);



    public CardShopView(ShopControl shopControl){
        this.shopControl = shopControl;
    }

    public void shopEntrance(){
        ImageView backGround = new ImageView(new Image("Files/Images/CardShopBackground.jpg"));
        backGround.fitWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));
        backGround.fitHeightProperty().bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 1));
        cardShopGroup.getChildren().add(backGround);

        ImageView cardShopIcon = new ImageView(new Image("Files/Images/ShopImages/cardShopIcon.png"));

        for (Card card : shopControl.getCardShop().getCards()) {
            cardImages.add(new CardView(Graphics.getInstance().getStage().getWidth()/7, Graphics.getInstance().getStage().getWidth() * 1.5 / 7, new Image("Files/Images/Battle/goblin.png"), card, 0, 0).getVBox());
        }

        VBox vBox1 = new VBox(50);
        VBox vBox2 = new VBox(50);
        VBox vBox3 = new VBox(50);
        VBox vBox4 = new VBox(50);
        int counter = 0;

        for (VBox vBox : cardImages) {
            if (counter % 4 == 0)
                    vBox1.getChildren().add(vBox);
                else if (counter % 4 == 1)
                    vBox2.getChildren().add(vBox);
                else if (counter % 4 == 2)
                    vBox3.getChildren().add(vBox);
                else if (counter % 4 == 3)
                    vBox4.getChildren().add(vBox);
                counter++;
            }

        HBox hBox = new HBox(50);
        hBox.getChildren().addAll(vBox1,vBox2,vBox3,vBox4);

        VBox screenVBox = new VBox();
        screenVBox.getChildren().addAll(cardShopIcon,hBox);
        screenVBox.setAlignment(Pos.CENTER);
        screenVBox.setLayoutX(250);

        Text remaineGills = new Text()

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