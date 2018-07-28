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
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class ItemShopView {
    private ShopControl shopControl;
    private ArrayList<Item> availableItems = new ArrayList<>();
    private ArrayList<ImageView> itemImages = new ArrayList<>();
    private HashMap<ImageView,Item> itemImageViewHashMap = new HashMap<>();
    private Group itemShopGroup = new Group();
    private Scene itemShopScene = new Scene(itemShopGroup);

    public ItemShopView(ShopControl shopControl){
        this.shopControl =shopControl;
    }

    public void itemShopEntrance() {
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
        exitIcon.setLayoutX(Graphics.getInstance().getStage().getWidth() - 65);
        itemShopGroup.getChildren().add(exitIcon);

        exitIcon.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                try {
                    Menu.getInstance().mainMenu();
                } catch (Exception e) {
                    System.out.println(e);
                }
            }
        });

        for (Item item : shopControl.getItemShop().getItems()) {
            if (!availableItems.contains(item)) {
                availableItems.add(item);
                itemImageViewHashMap.put(item.getItemImage(), item);
            }
        }

        for(Item item : availableItems)
            itemImages.add(item.getItemImage());

            for (ImageView imageView : itemImages) {
                imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        buyItem(itemImageViewHashMap.get(imageView));
                    }
                });
            }

            VBox vBox1 = new VBox(50);
            VBox vBox2 = new VBox(50);
            VBox vBox3 = new VBox(50);
            VBox vBox4 = new VBox(50);

            for (int counter = 0; counter < itemImages.size(); counter++) {
                itemImages.get(counter).setPreserveRatio(true);
                itemImages.get(counter).setFitWidth(Graphics.SCREEN_WIDTH / 12);

                if (counter % 4 == 0)
                    vBox1.getChildren().add(itemImages.get(counter));
                else if (counter % 4 == 1)
                    vBox2.getChildren().add(itemImages.get(counter));
                else if (counter % 4 == 2)
                    vBox3.getChildren().add(itemImages.get(counter));
                else if (counter % 4 == 3)
                    vBox4.getChildren().add(itemImages.get(counter));
            }

            HBox itemHBox = new HBox(40);
            itemHBox.setAlignment(Pos.CENTER);
            itemHBox.setMinSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
            itemHBox.getChildren().addAll(vBox1, vBox2, vBox3, vBox4);

            VBox screenVBox = new VBox();
            screenVBox.getChildren().addAll(itemShopIcon, itemHBox);
            screenVBox.setAlignment(Pos.CENTER);
            screenVBox.setLayoutX(0);
            screenVBox.setLayoutY(50);

            Text remainGills = new Text(Integer.toString(shopControl.getUser().getGills()));

            ImageView coinImage = new ImageView(new Image(new File("./src/Files/Images/ShopImages/coin.png").toURI().toString()));
            coinImage.setFitWidth(60);
            coinImage.setPreserveRatio(true);

            HBox coinHBox = new HBox();
            coinHBox.setAlignment(Pos.CENTER);
            coinHBox.getChildren().addAll(coinImage, remainGills);
            coinHBox.setLayoutX(15);
            itemShopGroup.getChildren().add(coinHBox);

            itemShopGroup.getChildren().addAll(screenVBox);

            Graphics.getInstance().getStage().setScene(itemShopScene);

        }


    private void buyItem(Item item){
            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            itemShopScene.setRoot(gridPane);

            gridPane.setStyle("-fx-background-image: url(/Files/Images/BackGround/cardShopBackGround.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");

            ImageView form = new ImageView(new Image("Files/Images/BackGround/DialogueBg.png"));

            Text text = new Text(item.toString());
            text.setFill(Color.WHITE);

            Button buyButton = new Button("Buy");
            buyButton.setStyle("-fx-font-family: Purisa; -fx-font-weight: bold; -fx-background-color: #cea57f;");
            Button backButton = new Button("Back");
            backButton.setStyle("-fx-font-family: Purisa; -fx-font-weight: bold; -fx-background-color: #cea57f;");
            HBox buttonHBox = new HBox(100);
            buttonHBox.getChildren().addAll(buyButton,backButton);

            VBox vbox = new VBox(50);
            vbox.getChildren().addAll(text, buttonHBox);
            vbox.setAlignment(Pos.CENTER);

        buyButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(shopControl.getUser().canBuy(item.getGillCost()))
                    canBuy(item.getName());
                else
                    cantBuy(item.getName());
            }
        });

            backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    itemShopEntrance();
                }
            });


            gridPane.getChildren().addAll(form, vbox);
        }

        private void canBuy(String itemName){
            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            itemShopScene.setRoot(gridPane);

            gridPane.setStyle("-fx-background-image: url(/Files/Images/BackGround/cardShopBackGround.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");


            ImageView form = new ImageView(new Image("Files/Images/BackGround/DialogueBg.png"));
            Text text = new Text("Are you sure you want to buy " + itemName + " from the Shop?");
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
                    itemShopEntrance();
                }
            });

            yesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    shopControl.buyItem(itemName,1);
                    itemShopEntrance();
                }
            });
        }

        private void cantBuy(String itemName){
            GridPane gridPane = new GridPane();
            gridPane.setAlignment(Pos.CENTER);
            itemShopScene.setRoot(gridPane);

            gridPane.setStyle("-fx-background-image: url(/Files/Images/BackGround/cardShopBackGround.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");

            Text text = new Text("You don't have enough gills to buy " + itemName);
            text.setFill(Color.WHITE);

            ImageView form = new ImageView(new Image("Files/Images/BackGround/DialogueBg.png"));
            Button returnButton = new Button("return to Card Shop");
            returnButton.setStyle("-fx-font-family: Purisa; -fx-font-weight: bold; -fx-background-color: #cea57f;");
            VBox vBox = new VBox(50);
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(text,returnButton);

            gridPane.getChildren().addAll(form,vBox);

            returnButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    itemShopEntrance();
                }
            });
        }
}
