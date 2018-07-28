package Modules.Graphic;

import Control.GameControll.GameControl;
import Modules.Card.Card;
import Modules.Graphic.MenuClasses.Hero;
import Modules.Graphic.MenuClasses.MenuItems;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Scanner;

public class Menu {
    private GameControl gameControl;
    private static Menu menu = new Menu();
    private Hero hero;

    private Menu(){
    }

    public static Menu getInstance(){
        return menu;
    }

    public GameControl getGameControl () {
        return gameControl;
    }

    public void setGameControl (GameControl gameControl) {
        this.gameControl = gameControl;
    }

    public void startBattle(int level){
        gameControl.startBattle(level);
    }

    public void shopEntrance(){
        gameControl.shopEntrance();
    }

    /**first page that initialize with the beginning of the game
     *
     * @param gameControl to initialize the game as the effects go on
     */
    public void startScreen (GameControl gameControl) throws IOException{
        loadScreenXML();
        Graphics.getInstance().getStage().setWidth(Screen.getPrimary().getVisualBounds().getWidth());
        Graphics.getInstance().getStage().setHeight(Screen.getPrimary().getVisualBounds().getHeight());
        Graphics.START_MUSIC_PLAYER.setCycleCount(Animation.INDEFINITE);
        Graphics.START_MUSIC_PLAYER.play();
        Graphics.getInstance().setMusicPlayer(Graphics.START_MUSIC_PLAYER);

//        ImageCursor imageCursor = new ImageCursor(Graphics.CURSOR_IMAGE, 30, 30);
//        Graphics.getInstance().getMainScene().setCursor(imageCursor);

        Parent root = Graphics.getInstance().getMainScene().getRoot();
        Text text = (Text)root.lookup("#beginText");
        text.setEffect(Graphics.REFLECTION);
        Timeline fadeAway = new Timeline(new KeyFrame(Duration.millis(.2), event -> {
            text.setOpacity(text.getOpacity() + OpaciyChange.opacityChange);
            if (text.getOpacity() < 0.01 && OpaciyChange.opacityChange < 0){
                text.setText(OpaciyChange.text[OpaciyChange.turn]);
                OpaciyChange.turn++;
                if (OpaciyChange.turn == 1)
                    text.setFont(Font.font("Nazli", 60));
                if (OpaciyChange.turn == 2) {
                    text.setEffect(null);
                    text.setFont(Font.font("Nazli", 50));
                }else if(OpaciyChange.turn == 3){
                    try {
                        gameControl.startGame();
                    }catch (IOException e){
                        System.out.println("problem in input of files");
                    }
                }
                OpaciyChange.opacityChange = -OpaciyChange.opacityChange;
            }else if(text.getOpacity() > 0.99 && OpaciyChange.opacityChange > 0){
                OpaciyChange.opacityChange = -OpaciyChange.opacityChange;
            }
        }));
        fadeAway.setCycleCount(5000);
        fadeAway.play();
    }

    /**
     * just for cleaning the code of the main method(startScreen)
     */
    private void loadScreenXML() throws IOException {
        Stage primaryStage = Graphics.getInstance().getStage();
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/startScreen.fxml"));
        Graphics.getInstance().setMainScene(new Scene(root, Graphics.SCREEN_WIDTH,
                Graphics.SCREEN_HEIGHT));
        primaryStage.setScene(Graphics.getInstance().getMainScene());
        primaryStage.setTitle("PELOPONNESIAN WAR");
        primaryStage.setFullScreen(true);
        primaryStage.setFullScreenExitHint("");
        primaryStage.show();
    }


    /**
     * for getting name of user in register page
     * @return returns a default name
     */
    public String getNameFromUser()throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/RegisterPage.fxml"));
        Graphics.getInstance().getStage().getScene().setRoot(root);
        StringBuilder name = new StringBuilder("MrNobody");

        Button register = ((Button)root.lookup("#registerButton"));
        String buttonCss = "-fx-border-radius: 60;" +
                " -fx-background-radius: 20;" +
                " -fx-min-width: 40px;" +
                " -fx-min-height: 35px;" +
                " -fx-font-weight: bold;";
        register.setEffect(Graphics.GLOW);

        EventHandler<MouseEvent> onButton = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if(event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                    register.setStyle("-fx-background-color: rgba(137,137,137,0.17);" + buttonCss);
                    register.setTextFill(Color.rgb(45, 45, 45));
                }else if(event.getEventType().equals(MouseEvent.MOUSE_EXITED)){
                    register.setStyle("-fx-background-color: rgba(115,115,115,0.75);" + buttonCss);
                    register.setTextFill(Color.ALICEBLUE);
                }
                else if (event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                    name.replace(0, 8, ((TextField)root.lookup("#textfield")).getText());
                    if (!name.toString().equals(""))
                        gameControl.getUser().setName(name.toString());
                    try {
                        gameControl.game();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        register.addEventHandler(MouseEvent.ANY, onButton);
        return name.toString();
    }

    /**
     * the main menu from which you can decide going to battle or saving the game or other stuff
     */
    public void mainMenu() throws IOException {
        loadMenuXML();
        Graphics.getInstance().getStage().addEventHandler(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.HOME) {
                goBacktoMenu();
            }
        });
        Parent root = Graphics.getInstance().getMenu().getRoot();

        Text welcome = ((Text) root.lookup("#welcome"));
        welcome.setEffect(Graphics.SHADOW);
        welcome.setText(welcome.getText() + gameControl.getUser().getName());

        addEventHandler();
    }

    public void goBacktoMenu(){
        Graphics.getInstance().getMusicPlayer().stop();
        Graphics.START_MUSIC_PLAYER.play();
        Graphics.getInstance().getStage().setScene(Graphics.getInstance().getMenu());
    }

    /**
     * for loading the fxml code from resources
     */
    private void loadMenuXML() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/MainMenu.fxml"));
        Scene scene = Graphics.getInstance().getMainScene();
        Graphics.getInstance().getStage().setScene(scene);
        Graphics.getInstance().setMenu(scene);
        scene.setRoot(root);
    }

    /**
     * adds event handling to menu items
     */
    private void addEventHandler(){
        Parent root = Graphics.getInstance().getMenu().getRoot();
        Text[] texts = new Text[7];
        for (int i = 0; i < 7; i++) {
            texts[i] = (Text) root.lookup("#menuText" + (i+1));
            new MenuItems(texts[i], gameControl);
        }
        new MenuItems((ImageView) root.lookup("#exitImage"), gameControl);
        ((VBox)root.lookup("#greeting")).maxWidthProperty().
                bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1.0));
        ((VBox)root.lookup("#greeting")).maxHeightProperty().
                bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 5.0/1.4));
        ((VBox)root.lookup("#mainMenu")).maxHeightProperty().
                bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 5/2));
        ((VBox)root.lookup("#exit")).maxHeightProperty().
                bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 5.0/1.5));

        ((VBox)root.lookup("#mainMenu")).minHeightProperty().
                bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 5/2));
        ((VBox)root.lookup("#exit")).minHeightProperty().
                bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 5.0/1.5));

        EventHandler<KeyEvent> up_Down = new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent event) {
                if (event.getCode() == KeyCode.UP){
                    Text text = null;
                    if (MenuItems.number != -1) {
                        text = texts[MenuItems.number];
                        text.setStyle("-fx-font-size: 20; -fx-font-family: Purisa;");
                        text.setFill(Color.CORNSILK);
                    }
                    if (MenuItems.number > 0) {
                        MenuItems.number--;
                        text = texts[MenuItems.number];
                        text.setStyle("-fx-font-size: 25; -fx-font-family: Purisa;");
                        text.setFill(Color.rgb(229, 223, 160));
                    }
                }else if(event.getCode() == KeyCode.DOWN){
                    Text text = null;
                    if (MenuItems.number != -1) {
                        text = texts[MenuItems.number];
                        text.setStyle("-fx-font-size: 20; -fx-font-family: Purisa;");
                        text.setFill(Color.CORNSILK);
                    }
                    if (MenuItems.number != 6) {
                        MenuItems.number++;
                        text = texts[MenuItems.number];
                        text.setStyle("-fx-font-size: 25; -fx-font-family: Purisa;");
                        text.setFill(Color.rgb(229, 223, 160));
                    }
                }else if(event.getCode() == KeyCode.ENTER){
                    switch (texts[MenuItems.number].getText()){
                        case "Single Player":
                            try {
                                dynamicMenu();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "MultiPlayer":
                            gameControl.getMultiPlayerBattleControl().multiPlayerEntrance();
                        case "Custom Game":

                            break;
                        case "Save Game":
                            try {
                                saveGame();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "Settings":
                            try {
                                settings();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                        case "Reset Game":
                            gameControl.resetGame();
                            break;
                        case "Inventory":
                            try {
                                gameControl.getInventory().mainThread();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            break;
                    }
                }
            }
        };
        Graphics.getInstance().getStage().getScene().addEventHandler(KeyEvent.KEY_PRESSED, up_Down);
    }

    public void saveGame() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/Save.fxml"));
        Graphics.getInstance().getStage().setScene(new Scene(root));

        String line;
        BufferedReader fileReader = new BufferedReader(new FileReader("./src/Files/save/SaveCtrl"));
        fileReader.readLine();
        fileReader.readLine();
        while((line = fileReader.readLine()) != null){
            Text text = new Text(line);
            new textStyler(text);
            ((VBox) root.lookup("#saveBox")).getChildren().add(text);
        }

        EventHandler<MouseEvent> newSave = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {

            }
        };

        Button newSaveBtn = (Button) root.lookup("#register");
        newSaveBtn.addEventHandler(MouseEvent.ANY, newSave);
    }

    /**
     * it's for when the user wants to exit the game, in which you will assure that he wants that
     */
    public void endGameScreen(){
        Parent root = Graphics.getInstance().getMenu().getRoot();
        VBox dialogueScreen = null;
        try {
            dialogueScreen = FXMLLoader.load(getClass().getResource("../../Files/Resources/ExitBox.fxml"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        assert dialogueScreen != null;
        dialogueScreen.minWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));
        dialogueScreen.minHeightProperty().bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 1));

        VBox box = (VBox) dialogueScreen.lookup("#dialogBox");
        box.maxWidthProperty().bind(Bindings.divide(dialogueScreen.widthProperty(), 2.2));
        box.minHeightProperty().bind(Bindings.divide(dialogueScreen.heightProperty(), 2));

        VBox questionHolder = (VBox) dialogueScreen.lookup("#questionHolder");
        questionHolder.minHeightProperty().bind(Bindings.divide(box.minHeightProperty(), 1.7));

        String cssButton = "-fx-font-family: Purisa;" +
                "-fx-font-weight: bold;";
        Button[] buttons = new Button[2];
        buttons[0] = (Button) dialogueScreen.lookup("#Button1");
        buttons[1] = (Button) dialogueScreen.lookup("#Button2");
        for (int i = 0; i < 2; i++) {
            Button button = buttons[i];
            EventHandler<MouseEvent> onButton = new EventHandler<MouseEvent>() {
                @Override
                public void handle (MouseEvent event) {
                    if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)) {
                        button.setStyle(cssButton + "-fx-background-color: #cea57f; -fx-font-size: 25;");
                    } else if (event.getEventType().equals(MouseEvent.MOUSE_EXITED)) {
                        button.setStyle(cssButton + "-fx-background-color:  #69443c; -fx-font-size: 23;");
                    }
                }
            };
            button.addEventHandler(MouseEvent.ANY, onButton);
        }
        EventHandler<MouseEvent> yesButtonClick = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                try {
                    gameControl.saveGame("s1");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        };
        buttons[0].addEventHandler(MouseEvent.MOUSE_CLICKED, yesButtonClick);
        VBox finalDialogueScreen = dialogueScreen;
        buttons[1].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {((GridPane) root).getChildren().remove(finalDialogueScreen);});
        ((GridPane)root).getChildren().add(dialogueScreen);
    }

    /**
     * dynamic menu or the game map in which you will decide your next battle
     */
    public void dynamicMenu() throws IOException {
        dynamicMenuStart();
        hero = new Hero(gameControl);
        Group root = (Group) Graphics.getInstance().getDynamicMenu().getRoot();
        root.getChildren().add(hero.getView());
        walkOperation(root);
    }

    /**
     * initializing the game map
     * @throws IOException
     */
    private void dynamicMenuStart() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/DynamicMenu.fxml"));
        Graphics.START_MUSIC_PLAYER.stop();
        Graphics.MAP_MUSIC_PLAYER.setCycleCount(Animation.INDEFINITE);
        Graphics.MAP_MUSIC_PLAYER.play();
        String mapUrl = "url(Files/Images/Map/ProjectMap" + gameControl.getUser().getLevel() + ".png)";
        ((GridPane) root.lookup("#gridPane")).setStyle("-fx-background-image: " + mapUrl + ";-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center;" +
                "-fx-background-size: stretch;");
        Graphics.getInstance().setMusicPlayer(Graphics.MAP_MUSIC_PLAYER);
        Graphics.getInstance().setDynamicMenu(new Scene(root));
        ((GridPane)root.lookup("#gridPane")).minWidthProperty().bind(Bindings.divide(
                Graphics.getInstance().getStage().widthProperty(), 1));
        ((GridPane)root.lookup("#gridPane")).minHeightProperty().bind(Bindings.divide(
                Graphics.getInstance().getStage().heightProperty(), 1));
        Graphics.getInstance().getStage().setScene(Graphics.getInstance().getDynamicMenu());
        Graphics.getInstance().getStage().setFullScreen(true);
    }

    /**
     * for handling the sprites walking
     */
    private void walkOperation(Group root){
        EventHandler<KeyEvent> walkEvent = new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent event) {
                if (event.getCode() == KeyCode.DOWN ||
                        event.getCode() == KeyCode.UP ||
                        event.getCode() == KeyCode.LEFT ||
                        event.getCode() == KeyCode.RIGHT) {
                    hero.moveHero();
                    root.getChildren().remove(hero.getView());
                    if (event.getCode() == KeyCode.DOWN) {
                        if (hero.getDirection() == 2) {
                            //if the direction is the same as the key the walking posture will be added
                            hero.setStateOfWalk(hero.getStateOfWalk() + 1);
                        } else {
                            hero.setDirection(2);
                            hero.setStateOfWalk(0);
                        }
                    } else if (event.getCode() == KeyCode.LEFT) {
                        if (hero.getDirection() == 1) {
                            //if the direction is the same as the key the walking posture will be added
                            hero.setStateOfWalk(hero.getStateOfWalk() + 1);
                        } else {
                            hero.setDirection(1);
                            hero.setStateOfWalk(0);
                        }
                    } else if (event.getCode() == KeyCode.UP) {
                        if (hero.getDirection() == 0) {
                            //if the direction is the same as the key the walking posture will be added
                            hero.setStateOfWalk(hero.getStateOfWalk() + 1);
                        } else {
                            hero.setDirection(0);
                            hero.setStateOfWalk(0);
                        }
                    } else if (event.getCode() == KeyCode.RIGHT) {
                        if (hero.getDirection() == 3) {
                            //if the direction is the same as the key the walking posture will be added
                            hero.setStateOfWalk(hero.getStateOfWalk() + 1);
                        } else {
                            hero.setDirection(3);
                            hero.setStateOfWalk(0);
                        }
                    }
                    root.getChildren().add(hero.getView());
                }
            }
        };
        Graphics.getInstance().getDynamicMenu().addEventHandler(KeyEvent.ANY, walkEvent);
    }

    public void castleMap() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/Castle.fxml"));
        String mapUrl = "url(Files/Images/Map/Castle" + gameControl.getUser().getLevel() + ".png)";
        ((AnchorPane) root).setStyle("-fx-background-image: " + mapUrl + ";-fx-background-repeat: no-repeat;" +
                "-fx-background-position: center;" +
                "-fx-background-size: stretch;");

        Graphics.getInstance().getStage().setScene(new Scene(root));
        hero = new Hero();
        ((AnchorPane) root).getChildren().add(hero.getView());
        walkOperation((AnchorPane)root);
    }
    /**
     * for handling the sprites walking
     */
    private void walkOperation(AnchorPane root){
        EventHandler<KeyEvent> walkEvent = new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent event) {
                if (event.getCode() == KeyCode.DOWN ||
                        event.getCode() == KeyCode.UP ||
                        event.getCode() == KeyCode.LEFT ||
                        event.getCode() == KeyCode.RIGHT) {
                    hero.moveHero();
                    root.getChildren().remove(hero.getView());
                    if (event.getCode() == KeyCode.DOWN) {
                        if (hero.getDirection() == 2) {
                            //if the direction is the same as the key the walking posture will be added
                            hero.setStateOfWalk(hero.getStateOfWalk() + 1);
                        } else {
                            hero.setDirection(2);
                            hero.setStateOfWalk(0);
                        }
                    } else if (event.getCode() == KeyCode.LEFT) {
                        if (hero.getDirection() == 1) {
                            //if the direction is the same as the key the walking posture will be added
                            hero.setStateOfWalk(hero.getStateOfWalk() + 1);
                        } else {
                            hero.setDirection(1);
                            hero.setStateOfWalk(0);
                        }
                    } else if (event.getCode() == KeyCode.UP) {
                        if (hero.getDirection() == 0) {
                            //if the direction is the same as the key the walking posture will be added
                            hero.setStateOfWalk(hero.getStateOfWalk() + 1);
                        } else {
                            hero.setDirection(0);
                            hero.setStateOfWalk(0);
                        }
                    } else if (event.getCode() == KeyCode.RIGHT) {
                        if (hero.getDirection() == 3) {
                            //if the direction is the same as the key the walking posture will be added
                            hero.setStateOfWalk(hero.getStateOfWalk() + 1);
                        } else {
                            hero.setDirection(3);
                            hero.setStateOfWalk(0);
                        }
                    }
                    try {
                        root.getChildren().add(hero.getView());
                    }catch (Exception e){
                        System.out.println("duplicate children");
                    }
                }
            }
        };
        Graphics.getInstance().getStage().getScene().addEventHandler(KeyEvent.ANY, walkEvent);
    }


    public void settings() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/Settings.fxml"));
        ImageView exit = (ImageView) root.lookup("#exit");
        Graphics.getInstance().getStage().setScene(new Scene(root));
        new MenuItems((Text) root.lookup("#sound"), gameControl);
        EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)){
                    exit.setFitHeight(110);
                    exit.setFitWidth(110);
                }else if(event.getEventType().equals(MouseEvent.MOUSE_EXITED)){
                    exit.setFitHeight(100);
                    exit.setFitWidth(100);
                }else if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                    goBacktoMenu();
                }
            }
        };
        exit.addEventHandler(MouseEvent.ANY, mouseEvent);
    }

    class textStyler{

        textStyler(Text text){
            text.setStyle("-fx-font-size: 20; -fx-font-family: Purisa;");
            text.setFill(Color.CORNSILK);
            EventHandler<MouseEvent> onMouse = event -> {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)){
                    text.setStyle("-fx-font-size: 25; -fx-font-family: Purisa;");
                    text.setFill(Color.rgb(229,223,160));
                }else if(event.getEventType().equals(MouseEvent.MOUSE_EXITED)){
                    text.setStyle("-fx-font-size: 20; -fx-font-family: Purisa;");
                    text.setFill(Color.CORNSILK);
                }else if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)) {
                    try {
                        gameControl.saveGame(text.getText());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            };
            text.addEventHandler(MouseEvent.ANY, onMouse);
        }
    }
}

class OpaciyChange{
    static double opacityChange = -0.001;
    final static String[] text = {"with High Performance...", "Represented by:" +
            "\nSajad the Gilga\nHossein the Haja\nKasra the Nigga", ""};
    static int turn = 0;
}
