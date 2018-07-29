package Main;

import Control.GameControll.GameControl;
import Modules.Card.Card;
import Modules.Card.Monsters.DragonBreed.BlueDragon;
import Modules.Card.Monsters.DragonBreed.VolcanicDragon;
import Modules.Card.Monsters.Elven.ElvenDruid;
import Modules.Card.Monsters.Elven.ElvenSorceress;
import Modules.CustomCard.CustomSpellType;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
import Modules.ItemAndAmulet.Item;
import Modules.ItemAndAmulet.Items.LargeHPPotion;
import Modules.ItemAndAmulet.Items.LargeMPPotion;
import Modules.User.Inventory.CardInventory;
import View.Custom.MainPage;
import View.Custom.SpellCardCreating;
import View.Custom.SpellCreating;
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
        Menu.getInstance().startScreen(gameControl);
//        CardInventoryView cardInventoryView = new CardInventoryView();
//
//        BlueDragon blueDragon = new BlueDragon();
//        VolcanicDragon volcanicDragon = new VolcanicDragon();
//        ElvenDruid elvenDruid = new ElvenDruid();
//        ElvenSorceress elvenSorceress = new ElvenSorceress();
//
//        ArrayList<Card> arrayList1 = new ArrayList<>();
//        arrayList1.add(blueDragon);
//        arrayList1.add(volcanicDragon);
//        ArrayList<Card> arrayList2 = new ArrayList<>();
//        arrayList2.add(elvenDruid);
//        arrayList2.add(elvenSorceress);
//
//
//
//
//        cardInventoryView.viewInventory(arrayList1, arrayList2);

//        ItemInventoryView itemInventoryView = new ItemInventoryView();
//
//        LargeHPPotion largeHPPotion = new LargeHPPotion();
//        LargeMPPotion largeMPPotion = new LargeMPPotion();
//
//        ArrayList<Item> arrayList1  = new ArrayList<>();
//        arrayList1.add(largeHPPotion);
//        ArrayList<Item> arrayList2 = new ArrayList<>();
//        arrayList2.add(largeMPPotion);

//        itemInventoryView.viewItemInventory(arrayList1, arrayList2);

//        MainPage.showMainPage();

//        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

    private boolean menu(){
        return true;
    }
}