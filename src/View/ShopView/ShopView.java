package View.ShopView;

import Modules.Graphic.Graphics;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.effect.Glow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;

public class ShopView {

    private static Group shopGroup = new Group();
    private static Scene shopScene = new Scene(shopGroup);
    private static Glow cardGlow = new Glow();
    private static Glow itemGlow = new Glow();
    private static Glow amuletGlow = new Glow();

    public void viewShop(){

            try {
                ImageView amuletShopDetailView = new ImageView(new Image(new FileInputStream("/Users/macbook/Desktop/javacodes/projectfooking/ap_project/src/Files/Images/ShopImages/amuletShopIcon.png")));
                amuletShopDetailView.setLayoutX(550);
                amuletShopDetailView.setLayoutY(120);

                ImageView cardShopDetailView = new ImageView(new Image(new FileInputStream("/Users/macbook/Desktop/javacodes/projectfooking/ap_project/src/Files/Images/ShopImages/cardShopIcon.png")));
                cardShopDetailView.setLayoutX(550);
                cardShopDetailView.setLayoutY(120);

                ImageView itemShopDetailView = new ImageView(new Image(new FileInputStream("/Users/macbook/Desktop/javacodes/projectfooking/ap_project/src/Files/Images/ShopImages/itemShopIcon.png")));
                itemShopDetailView.setLayoutX(550);
                itemShopDetailView.setLayoutY(120);


                ImageView cardShopView = new ImageView(new Image(new FileInputStream("/Users/macbook/Desktop/javacodes/projectfooking/ap_project/src/Files/Images/ShopImages/Card.png")));
                cardShopView.setFitWidth(300);
                cardShopView.setFitHeight(480);
                cardShopView.setOpacity(0.9);
                cardShopView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        cardGlow.setLevel(0.6);
                        cardShopView.setEffect(cardGlow);
                        shopGroup.getChildren().add(cardShopDetailView);
                    }
                });
                cardShopView.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        cardGlow.setLevel(0);
                        shopGroup.getChildren().remove(cardShopDetailView);
                    }
                });


                ImageView itemShopView = new ImageView(new Image(new FileInputStream("/Users/macbook/Desktop/javacodes/projectfooking/ap_project/src/Files/Images/ShopImages/Item.png")));
                itemShopView.setPreserveRatio(true);
                itemShopView.setFitWidth(240);
                itemShopView.setOpacity(0.9);
                itemShopView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        itemGlow.setLevel(0.6);
                        itemShopView.setEffect(itemGlow);
                        shopGroup.getChildren().add(itemShopDetailView);

                    }
                });
                itemShopView.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        itemGlow.setLevel(0);
                        shopGroup.getChildren().remove(itemShopDetailView);
                    }
                });


                ImageView amuletShopView = new ImageView(new Image(new FileInputStream("/Users/macbook/Desktop/javacodes/projectfooking/ap_project/src/Files/Images/ShopImages/Amulet.png")));
                amuletShopView.setPreserveRatio(true);
                amuletShopView.setFitWidth(240);
                amuletShopView.setOpacity(0.9);
                amuletShopView.setOnMouseEntered(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        amuletGlow.setLevel(0.6);
                        amuletShopView.setEffect(amuletGlow);
                        shopGroup.getChildren().add(amuletShopDetailView);
                    }
                });
                amuletShopView.setOnMouseExited(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        amuletGlow.setLevel(0);
                        shopGroup.getChildren().remove(amuletShopDetailView);
                    }
                });


                VBox vBox = new VBox();
                vBox.setSpacing(4);
                vBox.getChildren().addAll(itemShopView,amuletShopView);

                HBox hBox = new HBox();
                hBox.getChildren().addAll(cardShopView,vBox);
                hBox.setLayoutX(445);
                hBox.setLayoutY(275);
                hBox.setAlignment(Pos.CENTER);

                shopGroup.getChildren().addAll(hBox);
            }
            catch (Exception e){
                System.out.println("wrong fileInputStream");
            }

            try {
                ImageView backGround = new ImageView(new Image(new FileInputStream("/Users/macbook/Desktop/javacodes/projectfooking/ap_project/src/Files/Images/ShopImages/BackGround.jpg")));
                backGround.fitWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));
                backGround.fitHeightProperty().bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 1));
                shopGroup.getChildren().add(backGround);
            }catch (Exception e){
                System.out.println("wrong fileInputStream");
            }

            try{
                ImageView shopIconImage = new ImageView(new Image(new FileInputStream("/Users/macbook/Desktop/javacodes/projectfooking/ap_project/src/Files/Images/ShopImages/Shop.png")));
                shopIconImage.setPreserveRatio(true);
                shopIconImage.setLayoutX(550);
                shopIconImage.setLayoutY(0);
                shopIconImage.setFitWidth(300);
                shopGroup.getChildren().add(shopIconImage);
            }catch (Exception e){
                System.out.println("wrong fileInputStream");
            }

            try{
                ImageView exitIconImage = new ImageView(new Image(new FileInputStream("/Users/macbook/Desktop/javacodes/projectfooking/ap_project/src/Files/Images/ShopImages/Exit.png")));
                shopGroup.getChildren().add(exitIconImage);
                exitIconImage.setPreserveRatio(true);
                exitIconImage.setFitWidth(60);
                exitIconImage.setLayoutY(-10);
            }catch (Exception e){
                System.out.println("wrong fileInputStream");
            }

        Graphics.getInstance().setShop(shopScene);
    }

}
