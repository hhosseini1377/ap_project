package View.ShopView;

import Control.ShopControl;
import Modules.Graphic.Graphics;
import Modules.ItemAndAmulet.Item;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.util.ArrayList;

public class ItemShopView {

    private Group itemShopGroup = new Group();
    private Scene itemShopScene = new Scene(itemShopGroup);
    private ShopControl shopControl;
    private ArrayList<Item> availableItems = new ArrayList<>();
    private ArrayList<ImageView> itemImages = new ArrayList<>();

    public ItemShopView(ShopControl shopControl){
        this.shopControl = shopControl;
    }

    public void itemShopEntrance(){
        availableItems = new ArrayList<>();
        itemImages = new ArrayList<>();

        VBox vBox1 = new VBox(50);
        VBox vBox2 = new VBox(50);
        VBox vBox3 = new VBox(50);
        VBox vBox4 = new VBox(50);

        for(Item item : shopControl.getItemShop().getItems())
            if(!availableItems.contains(item))
                availableItems.add(item);

        for(Item item : availableItems)
            itemImages.add(item.getItemImage());

        System.out.println(itemImages.size());

        for(int counter = 0;counter<itemImages.size();counter++){
            if(counter% 4 == 0)
                vBox1.getChildren().add(itemImages.get(counter));
            else if(counter% 4 == 1)
                vBox1.getChildren().add(itemImages.get(counter));
            else if(counter% 4 == 2)
                vBox1.getChildren().add(itemImages.get(counter));
            else if(counter% 4 == 3)
                vBox1.getChildren().add(itemImages.get(counter));
        }
        HBox itemHBox = new HBox(50);
        itemHBox.getChildren().addAll(vBox1,vBox2,vBox3,vBox4);

        ImageView itemShopIcon = new ImageView(new Image("Files/Images/ShopImages/itemShopIcon.png"));

        VBox itemShopVBox = new VBox(50);
        itemShopVBox.getChildren().addAll(itemShopIcon,itemHBox);

        itemShopGroup.getChildren().add(itemShopVBox);


        Graphics.getInstance().getStage().setScene(itemShopScene);
        Graphics.getInstance().getStage().setFullScreen(true);
    }
}
