package Modules.Graphic;

import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class Graphics {
    private static Graphics graphics = new Graphics();
    public static final double screenWidth = Screen.getPrimary().getVisualBounds().getWidth();
    public static final double screenHeight = Screen.getPrimary().getVisualBounds().getHeight();
    private Parent menu;
    private Group battle;
    private Group shop;
    private Group Inventory;
    private Scene mainScene;
    private Stage stage;
    public final static Effect reflection = new Reflection(1, 0.5, 0.7, 0);
    public final static Effect glow = new Glow(0.7);
    public final static Media Start = new Media(new File("./src/Files/Music/13_westWorld.mp3").toURI().toString());
    public final static MediaPlayer startPlayer = new MediaPlayer(Start);

    private Graphics(){
    }

    public static Graphics getInstance () {
        return graphics;
    }

    public Parent getMenu () {
        return menu;
    }

    public void setMenu (Parent menu) {
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
