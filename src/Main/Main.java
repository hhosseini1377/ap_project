package Main;

import Control.GameControll.GameControl;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
import View.ShopView.CardShopView;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application{

    @Override
    public void start (Stage primaryStage) throws Exception{
        GameControl gameControl = new GameControl("./src/Files/save/");
        Graphics.getInstance().setStage(primaryStage);

 Menu.getInstance().startScreen(gameControl);
//        CardShopView cardShopView =   new CardShopView();
//        cardShopView.viewInventory();
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);

    }

    private boolean menu(){
        return true;
    }
}