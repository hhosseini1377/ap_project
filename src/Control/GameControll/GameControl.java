package Control.GameControll;

import Control.BattleControl;
import Control.InventoryControl;
import Control.ShopControl;
import Modules.BattleGround.Deck;
import Modules.Card.Card;
import Modules.Card.Monsters.Normal;
import Modules.Graphic.Graphics;
import Modules.ItemAndAmulet.Amulet;
import Modules.ItemAndAmulet.Amulets.*;
import Modules.ItemAndAmulet.Item;
import Modules.Shop.AmuletShop;
import Modules.Shop.CardShop;
import Modules.Shop.ItemShop;
import Modules.User.Inventory.AmuletInventory;
import Modules.User.Inventory.CardInventory;
import Modules.User.Inventory.ItemInventory;
import Modules.User.User;
import Modules.Warrior.BackPack;
import javafx.beans.binding.Bindings;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class GameControl {
    private String fileDirectory;
    private ArrayList<Card> cards;
    private ArrayList<Item> items;
    private ArrayList<Amulet> amulets;
    HashMap<String, Item> itemHashMap;
    HashMap<String, Card> cardHashMap;
    HashMap<String, Amulet> amuletHashMap;
    Deck deck;
    BackPack backPack;
    User user;
    ItemInventory itemInventory;
    CardInventory cardInventory;
    AmuletInventory amuletInventory;
    ItemShop itemShop;
    CardShop cardShop;
    AmuletShop amuletShop;
    private ShopControl shopControl;
    private BattleControl battleControl;
    private GameDetailController gameDetailController;
    private InventoryControl inventoryControl;


    public GameControl(String fileDirectory){
        gameDetailController = new GameDetailController(fileDirectory, this);
        //initializing the lists
        cards = new ArrayList<>();
        items = new ArrayList<>();
        amulets = new ArrayList<>();
        //initializing the maps
        itemHashMap = new HashMap<>();
        cardHashMap = new HashMap<>();
        amuletHashMap = new HashMap<>();
        this.fileDirectory = fileDirectory;
    }

    /** initializing the game and object
     * normal monsters which will be created via file
     * other kinds of monsters who will be created through their own classes
     * items and amulets
     * spells which again will be created of their own individual classes
     * the user and its
     * @throws IOException whether the file directory is right or not
     */
    public void startGame()throws IOException{
        //readying the file reader
        BufferedReader fileReader = new BufferedReader(new FileReader(fileDirectory + "MonsterCard"));
        String line;
        //reading line by line to create normal monsters
        while((line = fileReader.readLine()) != null) {
            String parts[] = line.split("-");
            Card monster = new Normal(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]),
                    Integer.parseInt(parts[3]), Boolean.parseBoolean(parts[4]),Boolean.parseBoolean(parts[5]), parts[6]);
            cards.add(monster);
            cardHashMap.put(monster.getName(), monster);
        }
        gameDetailController.monsterStart();
        gameDetailController.spellStart();
        gameDetailController.itemStart();
        amuletHashMap.put("Iron Pendant", new IronPendant());
        amuletHashMap.put("Gold Pendant", new GoldPendant());
        amuletHashMap.put("Diamond Pendant", new DiamondPendant());
        amuletHashMap.put("Iron Ring", new IronRing());
        amuletHashMap.put("Gold Ring", new GoldRing());
        amuletHashMap.put("Diamond Ring", new DiamondRing());
        amuletHashMap.put("Demon King's Crown", new DemonCrown());
        gameDetailController.backPackStart();
        gameDetailController.deckStart();
        gameDetailController.inventoryStart();
        gameDetailController.shopStart();
        //starting the user object
        int gills;
        int level;
        fileReader = new BufferedReader(new FileReader(fileDirectory + "userInfo.txt"));
        level = Integer.parseInt(fileReader.readLine().split(":")[1]);
        gills = Integer.parseInt(fileReader.readLine().split(":")[1]);
        String userName = getNameFromUser();
        user = new User(cardInventory, itemInventory, amuletInventory, deck, gills, level, userName, backPack);
        fileReader.close();
        inventoryControl = new InventoryControl(user);
        shopControl = new ShopControl(cardShop, itemShop, amuletShop, user, inventoryControl);
        battleControl = new BattleControl();
    }

    private String getNameFromUser()throws IOException{
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
            }
        };
        EventHandler<MouseEvent> enterGame = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                name.replace(0, 8, ((TextField)root.lookup("#textfield")).getText());
                if (!name.toString().equals(""))
                    user.setName(name.toString());
                try {
                    game();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        register.addEventHandler(MouseEvent.ANY, onButton);
        register.addEventHandler(MouseEvent.MOUSE_CLICKED, enterGame);
        return name.toString();
    }

    public void game() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/MainMenu.fxml"));
        Scene scene = Graphics.getInstance().getMainScene();
        Graphics.getInstance().setMenu(scene);
        scene.setRoot(root);
        Text welcome = ((Text)root.lookup("#welcome"));
        welcome.setEffect(Graphics.SHADOW);
        welcome.setText(welcome.getText() + user.getName());

        Text[] texts = new Text[4];
        for (int i = 0; i < 4; i++) {
            texts[i] = (Text) root.lookup("#menuText" + (i+1));
            MenuItems menuItems = new MenuItems(texts[i], this);
        }
        MenuItems menuItems = new MenuItems((ImageView) root.lookup("#exitImage"), this);
        ((VBox)root.lookup("#greeting")).prefWidthProperty().
                bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1.0));
        ((VBox)root.lookup("#greeting")).prefHeightProperty().
                bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 5.0/1.4));
        ((VBox)root.lookup("#mainMenu")).prefHeightProperty().
                bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 5/2));
        ((VBox)root.lookup("#exit")).prefHeightProperty().
                bind(Bindings.divide(Graphics.getInstance().getStage().heightProperty(), 5.0/1.5));


        EventHandler<KeyEvent> click = new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent event) {

            }
        };
        EventHandler<KeyEvent> up_Down = new EventHandler<KeyEvent>() {
            @Override
            public void handle (KeyEvent event) {
                if (event.getCode() == KeyCode.UP){
                    Text text = null;
                    if (MenuItems.number != -1) {
                        text = texts[MenuItems.number];
                        text.setStyle("-fx-font-size: 30; -fx-font-family: Purisa;");
                        text.setFill(Color.CORNSILK);
                    }
                    if (MenuItems.number > 0) {
                        MenuItems.number--;
                        text = texts[MenuItems.number];
                        text.setStyle("-fx-font-size: 40; -fx-font-family: Purisa;");
                        text.setFill(Color.rgb(229, 223, 160));
                    }
                }else if(event.getCode() == KeyCode.DOWN){
                    Text text = null;
                    if (MenuItems.number != -1) {
                        text = texts[MenuItems.number];
                        text.setStyle("-fx-font-size: 30; -fx-font-family: Purisa;");
                        text.setFill(Color.CORNSILK);
                    }
                    if (MenuItems.number != 3) {
                        MenuItems.number++;
                        text = texts[MenuItems.number];
                        text.setStyle("-fx-font-size: 40; -fx-font-family: Purisa;");
                        text.setFill(Color.rgb(229, 223, 160));
                    }
                }else if(event.getCode() == KeyCode.ENTER){
                    switch (texts[MenuItems.number].getText()){
                        case "Enter Menu":
                            //TODO
                            break;
                        case "Settings":
                            //TODO
                            break;
                        case "Reset Game":
                            resetGame();
                            break;
                    }
                }
            }
        };
        Graphics.getInstance().getStage().addEventHandler(KeyEvent.KEY_PRESSED, up_Down);
    }

    /**
     * prints available options for user
     */
    private String  help(){
        System.out.println("1. Enter Shop:To enter shop and buy or sell cards and items\n" +
                "2. Battle:To enter the next battle\n" +
                "3. Edit Inventory:To edit your cards and amulets\n" +
                "4. Exit: save and exit the game");
        return "1. Enter Shop:To enter shop and buy or sell cards and items\n" +
                "2. Battle:To enter the next battle\n" +
                "3. Edit Inventory:To edit your cards and amulets\n" +
                "4. Exit: save and exit the game";
    }

    /**
     * ends the game
     * checks if file data needs to be reset cause of completing the game
     * saves the necessary data
     */
    public void endGame (){
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

        String cssButton = "-fx-background-radius: 10px;" +
                "-fx-font-family: Purisa;" +
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
                    gameDetailController.saveGame();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.exit(0);
            }
        };
        ((GridPane)root).getChildren().add(dialogueScreen);
        buttons[0].addEventHandler(MouseEvent.MOUSE_CLICKED, yesButtonClick);
        VBox finalDialogueScreen = dialogueScreen;
        buttons[1].addEventHandler(MouseEvent.MOUSE_CLICKED, event -> {((GridPane) root).getChildren().remove(finalDialogueScreen);});
    }

    /**
     * restarting the data of the game via copying the initial game data which is saved in "initial" folder
     * in "save" folder which is the source of the data in our game
     */
    public void resetGame(){
        String path1 = "/home/gilgamesh/Desktop/Programs/Java/Project/projectAp/src/Files/initial/";
        String path2 = "/home/gilgamesh/Desktop/Programs/Java/Project/projectAp/src/Files/save/";
        try {
            Files.copy(Paths.get(path1 + "backPack.txt"), new FileOutputStream(path2 + "backPack.txt"));
            Files.copy(Paths.get(path1 + "deck.txt"), new FileOutputStream(path2 + "deck.txt"));
            Files.copy(Paths.get(path1 + "inventory.txt"), new FileOutputStream(path2 + "inventory.txt"));
            Files.copy(Paths.get(path1 + "shop.txt"), new FileOutputStream(path2 + "shop.txt"));
            Files.copy(Paths.get(path1 + "userInfo.txt"), new FileOutputStream(path2 + "userInfo.txt"));
            System.out.println("the data of the game has been reset,\nrestart the game to notice the change.");
        }catch (IOException e){
            System.out.println("File not found!");
            e.printStackTrace();
        }
    }

    public String getFileDirectory() {
        return fileDirectory;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Amulet> getAmulets() {
        return amulets;
    }

    public User getUser() {
        return user;
    }

    public void reloadGame() throws IOException{
        gameDetailController.backPackStart();
        user.setBackPack(backPack);
        gameDetailController.deckStart();
        user.setDeck(deck);
    }
}

class CardException extends Exception{
    CardException (){
        System.out.println("card not available");
    }
}

class MenuItems{
    public static int number = 0;

    public MenuItems(Text text, GameControl gameControl){
        EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)){
                    text.setStyle("-fx-font-size: 40; -fx-font-family: Purisa;");
                    text.setFill(Color.rgb(229,223,160));
                }else if(event.getEventType().equals(MouseEvent.MOUSE_EXITED)){
                    text.setStyle("-fx-font-size: 30; -fx-font-family: Purisa;");
                    text.setFill(Color.CORNSILK);
                }else if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                    switch (text.getText()){
                        case "Enter Menu":
                            //TODO
                            break;
                        case "Settings":
                            //TODO
                            break;
                        case "Reset Game":
                            gameControl.resetGame();
                            break;
                    }
                }
            }
        };
        text.addEventHandler(MouseEvent.ANY, mouseEvent);
    }

    public MenuItems(ImageView exit, GameControl gameControl){
        EventHandler<MouseEvent> mouseEvent = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                if (event.getEventType().equals(MouseEvent.MOUSE_ENTERED)){
                    exit.setFitHeight(85);
                    exit.setFitWidth(75);
                }else if(event.getEventType().equals(MouseEvent.MOUSE_EXITED)){
                    exit.setFitHeight(80);
                    exit.setFitWidth(70);
                }else if(event.getEventType().equals(MouseEvent.MOUSE_CLICKED)){
                    gameControl.endGame();
                }
            }
        };
        exit.addEventHandler(MouseEvent.ANY, mouseEvent);
    }
}