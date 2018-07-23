package View.ShopView;
import Control.ShopControl;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
import Modules.ItemAndAmulet.Item;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemShopView {
    private ShopControl shopControl;
    private ArrayList<Item> availableItems = new ArrayList<>();
    private ArrayList<ImageView> itemImages = new ArrayList<>();
    private Group itemShopGroup = new Group();
    private Scene itemShopScene = new Scene(itemShopGroup);

    public ItemShopView(ShopControl shopControl){
        this.shopControl =shopControl;
    }

    public void itemShopEntrance(){
        itemShopScene.setRoot(itemShopGroup);
        availableItems = new ArrayList<>();
        itemImages = new ArrayList<>();

        ImageView backGround = new ImageView(new Image("Files/Images/BackGround/cardShopBackGround.jpg"));
        backGround.fitWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));
        backGround.fitHeightProperty().bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 1));
        itemShopGroup.getChildren().add(backGround);

        ImageView itemShopIcon = new ImageView(new Image("Files/Images/ShopImages/itemShopIcon.png"));

        ImageView exitIcon = new ImageView(new Image("Files/Images/ShopImages/Exit.png"));
        exitIcon.setPreserveRatio(true);
        exitIcon.setFitWidth(60);
        exitIcon.setLayoutX(Graphics.getInstance().getStage().getWidth()-65);
        itemShopGroup.getChildren().add(exitIcon);

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

        for (Item item : shopControl.getItemShop().getItems()) {
            if(!availableItems.contains(item))
                availableItems.add(item);
        }
        System.out.println(availableItems.size());

        for(Item item:availableItems)
            itemImages.add(item.getItemImage());

        for(ImageView imageView:itemImages){
            imageView.setFitWidth(Graphics.SCREEN_WIDTH/5);
            imageView.setPreserveRatio(true);
        }

        itemShopGroup.getChildren().add(itemImages.get(0));


        VBox vBox1 = new VBox(50);
        VBox vBox2 = new VBox(50);
        VBox vBox3 = new VBox(50);
        VBox vBox4 = new VBox(50);
        for(int counter=0;counter<itemImages.size();counter++){
            if(counter % 4 == 0)
                vBox1.getChildren().add(itemImages.get(counter));
            else if(counter % 4 == 1)
                vBox2.getChildren().add(itemImages.get(counter));
            else if(counter % 4 == 2)
                vBox3.getChildren().add(itemImages.get(counter));
            else if(counter % 4 == 3)
                vBox4.getChildren().add(itemImages.get(counter));
        }

        HBox itemHBox = new HBox(40);
        itemHBox.setAlignment(Pos.CENTER);
        itemHBox.setMinSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        itemHBox.getChildren().addAll(vBox1,vBox2,vBox3,vBox4);

        VBox screenVBox = new VBox();
        screenVBox.getChildren().addAll(itemShopIcon,itemHBox);
        screenVBox.setAlignment(Pos.CENTER);
        screenVBox.setLayoutX(0);
        screenVBox.setLayoutY(50);

        Text remainGills = new Text(Integer.toString(shopControl.getUser().getGills()));

        ImageView coinImage = new ImageView(new Image(new File("./src/Files/Images/ShopImages/coin.png").toURI().toString()));
        coinImage.setFitWidth(60);
        coinImage.setPreserveRatio(true);

        HBox coinHBox = new HBox();
        coinHBox.setAlignment(Pos.CENTER);
        coinHBox.getChildren().addAll(coinImage,remainGills);
        coinHBox.setLayoutX(15);
        itemShopGroup.getChildren().add(coinHBox);

        itemShopGroup.getChildren().addAll(screenVBox);

        Graphics.getInstance().getStage().setScene(itemShopScene);

    }


    public void printItemShopdetails(HashMap<String,Integer> numberOfItemsInInventory, ArrayList<Item> availableShopItems){
        int numberOfCards=1;
        //prints shop amulets
        System.out.println("Shop List: ");
        for(Item item:availableShopItems)
            System.out.println(numberOfCards++ + ". " + item.getName() +  " " + item.getGillCost());
        //prints Inventory amulets
        System.out.println("Item Inventory: ");
        numberOfItemsInInventory.forEach((key,value) -> {
            System.out.println(value + " " + key);
        });
    }

    public void printHelpDetails(){
        System.out.println("1. Buy \"Item Name\" - #NumberToBuy: To buy a certain number of an item from shop");
        System.out.println("2. Sell \"Item Name\" - #NumberToSell: To sell a certain number of an item from inventory");
        System.out.println("3. Info \"Item Name\" To get more information about an item");
        System.out.println("4. Exit: To return to shop menu");
    }

    public void printBuyItemDetails(boolean canBuy,int numberToBuy,String itemName) {
        if (canBuy) {
            System.out.println("Successfully bought " + numberToBuy + " of " + itemName + "s");
        }
        else {
            System.out.println("Not enough Gill!");
        }
    }

    public void printSellItemDetails(boolean canSell,int numberToSell,String itemName){
        if(canSell){
            System.out.println("Successfully sold " + numberToSell + " of " + itemName + "s");
        }
        else{
            System.out.println("Not enough Cards!");
        }
    }

    public void printInfoOfItem(String itemName,String details){
        System.out.println(itemName + " Info:");
        System.out.println(details);
    }
}
