package Modules.Graphic;

import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Effect;
import javafx.scene.effect.Glow;
import javafx.scene.effect.Reflection;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Graphics {
    private final static Media START = new Media(new File("./src/Files/Music/13_westWorld.mp3").toURI().toString());
    private final static Media MAP_MUSIC = new Media(new File("./src/Files/Music/15_westWorld.mp3").toURI().toString());
    private final static Media BATTLE_MUSIC = new Media(new File("./src/Files/Music/11_westWorld.mp3").toURI().toString());
    public final static Image CURSOR_IMAGE = new Image("Files/Images/Icons/cursor1.png");
    public final static Effect REFLECTION = new Reflection(1, 0.5, 0.7, 0);
    public final static Effect GLOW = new Glow(0.7);
    public final static Effect SHADOW = new DropShadow(3, 1, 4, Color.DARKGRAY);
    public final static MediaPlayer START_MUSIC_PLAYER = new MediaPlayer(START);
    public final static MediaPlayer MAP_MUSIC_PLAYER = new MediaPlayer(MAP_MUSIC);
    public final static MediaPlayer BATTLE_MUSIC_PLAYER = new MediaPlayer(BATTLE_MUSIC);
    public static final double SCREEN_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    public static final double SCREEN_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();
    public static boolean isMute = false;
    public static final Image CARD_BACK = new Image(new File("./src/Files/Images/Battle/cardBack.jpeg").toURI().toString());


    private static Graphics graphics = new Graphics();
    private Scene menu;
    private Scene dynamicMenu;
    private Scene battle;
    private Scene shop;
    private Scene cardShopScene;
    private Scene cardInventoryScene;
    private Scene Inventory;
    private Scene mainScene;
    private Scene graveyard;
    private Stage stage;
    private MediaPlayer musicPlayer;

    private Graphics(){
    }

    public Scene getCardInventoryScene() {
        return cardInventoryScene;
    }

    public void setCardInventoryScene(Scene cardInventoryScene) {
        this.cardInventoryScene = cardInventoryScene;
    }

    public Scene getDynamicMenu () {
        return dynamicMenu;
    }

    public void setDynamicMenu (Scene dynamicMenu) {
        this.dynamicMenu = dynamicMenu;
    }

    public Font getFont(){
        Font font = Font.loadFont("Snell Roundhand Script.ttf",23);
        return font;
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

    public Scene getGraveyard () {
        return graveyard;
    }

    public void setGraveyard (Scene graveyard) {
        this.graveyard = graveyard;
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

    public Scene getCardShopScene() {
        return cardShopScene;
    }

    public void setCardShopScene(Scene cardShopScene) {
        this.cardShopScene = cardShopScene;
    }

    public MediaPlayer getMusicPlayer () {
        return musicPlayer;
    }

    public void setMusicPlayer (MediaPlayer musicPlayer) {
        this.musicPlayer = musicPlayer;
    }

    public void notifyMessage(String message, String kind){
            VBox dialogPage = null;
            try {
                dialogPage = FXMLLoader.load(getClass().getResource("../../Files/Resources/NotifyPage.fxml"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            assert dialogPage != null;
            Scene scene = Graphics.getInstance().getStage().getScene();
            ((Text) dialogPage.lookup("#dialog")).setText(message);
            ((AnchorPane) scene.getRoot()).getChildren().add(dialogPage);
            VBox box = (VBox) dialogPage.lookup("#dialogBox");
            box.maxWidthProperty().bind(Bindings.divide(dialogPage.widthProperty(), 2.2));
            box.minHeightProperty().bind(Bindings.divide(dialogPage.heightProperty(), 2));
            Button button = (Button) dialogPage.lookup("#button");
            String cssButton = "-fx-font-family: Purisa;" + "-fx-font-weight: bold;";
            VBox finalDialogPage = dialogPage;
            EventHandler<MouseEvent> onAction = new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent event) {
                    if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                        button.setStyle(cssButton + "-fx-background-color: #cea57f; -fx-font-size: 25;");
                    } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
                        button.setStyle(cssButton + "-fx-background-color:  #69443c; -fx-font-size: 23;");
                    } else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                        ((AnchorPane) scene.getRoot()).getChildren().remove(finalDialogPage);
                    }
                }
            };
            button.addEventHandler(MouseEvent.ANY, onAction);
    }

}
