package View.ShopView;

import Modules.Card.Card;

import java.util.ArrayList;
import java.util.HashMap;

import Modules.Card.Monsters.DragonBreed.BlueDragon;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Graphic.Graphics;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CardShopView {
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
        fullScreen.setStyle("-fx-background-image: url(Files/Images/CardShopBackground.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");

        BlueDragon blueDragon = new BlueDragon();
        CardView testCard = null;

        testCard = new CardView(Graphics.SCREEN_WIDTH * 2 / 18, Graphics.SCREEN_HEIGHT * 3 / 12, new Image("Files/Images/Blue-Eyes-White-Dragon.jpg"), blueDragon, 0, 0);

        cardRows[0].getChildren().add(testCard.getVBox());
        cardRows[0].getChildren().add(testCard.getVBox());


        Graphics.getInstance().setCardShopScene(new Scene(fullScreen));

        Graphics.getInstance().getStage().setScene(Graphics.getInstance().getCardShopScene());
    }

}