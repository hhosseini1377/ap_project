package View.ShopView;
import Control.ShopControl;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
import Modules.ItemAndAmulet.Amulet;
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

public class AmuletShopView {
    private ShopControl shopControl;
    private ArrayList<Amulet> availableAmulets = new ArrayList<>();
    private ArrayList<ImageView> amuletImages = new ArrayList<>();
    private HashMap<ImageView,Amulet> amuletImageViewHashMap = new HashMap<>();
    private Group amuletShopGroup = new Group();
    private Scene amuletShopScene = new Scene(amuletShopGroup);

    public AmuletShopView(ShopControl shopControl){
        this.shopControl =shopControl;
    }

    public void amuletShopEntrance() {
        amuletShopScene.setRoot(amuletShopGroup);
        availableAmulets = new ArrayList<>();
        amuletImages = new ArrayList<>();

        ImageView backGround = new ImageView(new Image("Files/Images/BackGround/cardShopBackGround.jpg"));
        backGround.fitWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));
        backGround.fitHeightProperty().bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 1));
        amuletShopGroup.getChildren().add(backGround);

        ImageView amuletShopIcon = new ImageView(new Image("Files/Images/ShopImages/amuletShopIcon.png"));

        ImageView exitIcon = new ImageView(new Image("Files/Images/ShopImages/Exit.png"));
        exitIcon.setPreserveRatio(true);
        exitIcon.setFitWidth(60);
        exitIcon.setLayoutX(Graphics.getInstance().getStage().getWidth() - 65);
        amuletShopGroup.getChildren().add(exitIcon);

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

        for (Amulet amulet : shopControl.getAmuletShop().getAmulets()) {
            if (!availableAmulets.contains(amulet)) {
                availableAmulets.add(amulet);
                amuletImageViewHashMap.put(amulet.getAmuletImage(), amulet);
            }
        }

        for(Amulet amulet : availableAmulets)
            amuletImages.add(amulet.getAmuletImage());

        for (ImageView imageView : amuletImages) {
            imageView.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent event) {
                    buyAmulet(amuletImageViewHashMap.get(imageView));
                }
            });
        }

        VBox vBox1 = new VBox(50);
        VBox vBox2 = new VBox(50);
        VBox vBox3 = new VBox(50);
        VBox vBox4 = new VBox(50);

        for (int counter = 0; counter < amuletImages.size(); counter++) {
            amuletImages.get(counter).setFitWidth(Graphics.SCREEN_WIDTH / 12);
            amuletImages.get(counter).setFitHeight(Graphics.SCREEN_WIDTH / 12);

            if (counter % 4 == 0)
                vBox1.getChildren().add(amuletImages.get(counter));
            else if (counter % 4 == 1)
                vBox2.getChildren().add(amuletImages.get(counter));
            else if (counter % 4 == 2)
                vBox3.getChildren().add(amuletImages.get(counter));
            else if (counter % 4 == 3)
                vBox4.getChildren().add(amuletImages.get(counter));
        }

        HBox itemHBox = new HBox(40);
        itemHBox.setAlignment(Pos.CENTER);
        itemHBox.setMinSize(Graphics.SCREEN_WIDTH, Graphics.SCREEN_HEIGHT);
        itemHBox.getChildren().addAll(vBox1, vBox2, vBox3, vBox4);

        VBox screenVBox = new VBox();
        screenVBox.getChildren().addAll(amuletShopIcon, itemHBox);
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
        amuletShopGroup.getChildren().add(coinHBox);

        amuletShopGroup.getChildren().addAll(screenVBox);

        Graphics.getInstance().getStage().setScene(amuletShopScene);

    }


    private void buyAmulet(Amulet amulet){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        amuletShopScene.setRoot(gridPane);

        gridPane.setStyle("-fx-background-image: url(/Files/Images/BackGround/cardShopBackGround.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");

        ImageView form = new ImageView(new Image("Files/Images/BackGround/DialogueBg.jpeg"));

        Text text = new Text(amulet.toString());
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
                if(shopControl.getUser().canBuy(amulet.getGillCost()))
                    canBuy(amulet.getName());
                else
                    cantBuy(amulet.getName());
            }
        });

        backButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                amuletShopEntrance();
            }
        });


        gridPane.getChildren().addAll(form, vbox);
    }

    private void canBuy(String amuletName){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        amuletShopScene.setRoot(gridPane);

        gridPane.setStyle("-fx-background-image: url(/Files/Images/BackGround/cardShopBackGround.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");


        ImageView form = new ImageView(new Image("Files/Images/BackGround/DialogueBg.jpeg"));
        Text text = new Text("Are you sure you want to buy " + amuletName + " from the Shop?");
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
                amuletShopEntrance();
            }
        });

        yesButton.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                shopControl.buyAmulet(amuletName,1);
                amuletShopEntrance();
            }
        });
    }

    private void cantBuy(String amuletName){
        GridPane gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);
        amuletShopScene.setRoot(gridPane);

        gridPane.setStyle("-fx-background-image: url(/Files/Images/BackGround/cardShopBackGround.jpg); -fx-background-size: stretch; -fx-background-repeat: no-repeat");

        Text text = new Text("You don't have enough gills to buy " + amuletName);
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
                amuletShopEntrance();
            }
        });
    }
}
