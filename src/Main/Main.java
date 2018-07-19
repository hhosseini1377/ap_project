package Main;

import Control.GameControll.GameControl;
import Modules.Card.Card;
import Modules.Card.Monsters.DragonBreed.BlueDragon;
import Modules.Card.Monsters.DragonBreed.VolcanicDragon;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
import Modules.User.Inventory.CardInventory;
import View.InventoryView.CardInventoryView;
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
        CardInventoryView cardInventoryView = new CardInventoryView();

        BlueDragon blueDragon = new BlueDragon();
        VolcanicDragon volcanicDragon = new VolcanicDragon();

        ArrayList<Card> arrayList1 = new ArrayList<>();
        arrayList1.add(blueDragon);
        ArrayList<Card> arrayList2 = new ArrayList<>();
        arrayList2.add(volcanicDragon);




        cardInventoryView.viewInventory(arrayList1, arrayList2);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

    private boolean menu(){
        return true;
    }
}