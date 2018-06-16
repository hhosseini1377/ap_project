package Modules.Graphic;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Graphics {
    private static Graphics graphics = new Graphics();
    private Group menu;
    private Group battle;
    private Group shop;
    private Group Inventory;
    private Scene mainScene;
    private Stage stage;

    private Graphics(){
    }

    public static Graphics getInstance () {
        return graphics;
    }

    public Group getMenu () {
        return menu;
    }

    public void setMenu (Group menu) {
        this.menu = menu;
    }

    public Group getBattle () {
        return battle;
    }

    public void setBattle (Group battle) {
        this.battle = battle;
    }

    public Group getShop () {
        return shop;
    }

    public void setShop (Group shop) {
        this.shop = shop;
    }

    public Group getInventory () {
        return Inventory;
    }

    public void setInventory (Group inventory) {
        Inventory = inventory;
    }

    public Scene getMainScene () {
        return mainScene;
    }

    public void setMainScene (Scene mainScene) {
        this.mainScene = mainScene;
    }

    public Stage getStage () {
        return stage;
    }

    public void setStage (Stage stage) {
        this.stage = stage;
    }
}
