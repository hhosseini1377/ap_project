package Modules.Graphic;

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
    private Scene battle;
    private Scene shop;
    private Scene Inventory;
    private Scene mainScene;
    private Stage stage;
    public final static Effect reflection = new Reflection(1, 0.5, 0.7, 0);
    public final static Effect glow = new Glow(0.7);
    public final static Media Start = new Media(new File("./src/Files/Music/13_westWorld.mp3").toURI().toString());
    public final static MediaPlayer startMusicPlayer = new MediaPlayer(Start);

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

    public Scene getBattle () {
        return battle;
    }

    public void setBattle (Scene battle) {
        this.battle = battle;
    }

    public Scene getShop () {
        return shop;
    }

    public void setShop (Scene shop) {
        this.shop = shop;
    }

    public Scene getInventory () {
        return Inventory;
    }

    public void setInventory (Scene inventory) {
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
