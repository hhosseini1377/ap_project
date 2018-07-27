package Main;

import Control.GameControll.GameControl;
import Modules.Card.Card;
import Modules.Card.Monsters.DragonBreed.BlueDragon;
import Modules.Card.Monsters.DragonBreed.VolcanicDragon;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
import Modules.ItemAndAmulet.Item;
import Modules.ItemAndAmulet.Items.GreaterRestorative;
import Modules.ItemAndAmulet.Items.LargeHPPotion;
import Modules.User.Inventory.CardInventory;
import View.InventoryView.CardInventoryView;
import View.InventoryView.ItemInventoryView;
import View.ShopView.CardShopView;
import View.ShopView.CardView;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application{

    @Override
    public void start (Stage primaryStage) throws Exception{
        GameControl gameControl = new GameControl("./src/Files/save/");
        Graphics.getInstance().setStage(primaryStage);
//        Menu.getInstance().startScreen(gameControl);
        ItemInventoryView itemInventoryView = new ItemInventoryView();

        ArrayList<Item> nonEquipped = new ArrayList<>();
        GreaterRestorative greaterRestorative = new GreaterRestorative();
        ArrayList<Item> equipped = new ArrayList<>();
        LargeHPPotion largeHPPotion = new LargeHPPotion();

        nonEquipped.add(greaterRestorative);
        equipped.add(largeHPPotion);

        itemInventoryView.viewItemInventory(nonEquipped, equipped);

        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

    private boolean menu(){
        return true;
    }
}