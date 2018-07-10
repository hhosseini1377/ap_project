package Modules.Graphic;

import javafx.scene.Scene;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;

public class Graphics {
    private final static Media START = new Media(new File("./src/Files/Music/13_westWorld.mp3").toURI().toString());
    //public final static Image CURSOR_IMAGE = new Image("Files/Images/cursor1.png");
    public final static Effect REFLECTION = new Reflection(1, 0.5, 0.7, 0);
    public final static Effect GLOW = new Glow(0.7);
    public final static Effect SHADOW = new DropShadow(3, 1, 4, Color.DARKGRAY);
    public final static MediaPlayer START_MUSIC_PLAYER = new MediaPlayer(START);
    public static final double SCREEN_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    public static final double SCREEN_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

    private static Graphics graphics = new Graphics();
    private Scene menu;
    private Scene battle;
    private Scene shop;
    private Scene Inventory;
    private Scene mainScene;
    private Stage stage;


    private Graphics(){
    }

    public static Graphics getInstance () {
        return graphics;
    }

    public Scene getMenu () {
        return menu;
    }

    public void setMenu (Scene menu) {
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
