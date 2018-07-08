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
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.web.PromptData;
import javafx.stage.Screen;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
        StringBuilder name = new StringBuilder("mrNobody");

        Button register = ((Button)root.lookup("#registerButton"));
        String buttonCss = "-fx-border-radius: 60; -fx-background-radius: 20; -fx-min-width: 40px; -fx-min-height: 35px; -fx-font-weight: bold;";
        register.setEffect(Graphics.glow);

        EventHandler<MouseEvent> onButtonEnter = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                register.setStyle("-fx-background-color: rgba(137,137,137,0.17);" + buttonCss);
                register.setTextFill(Color.rgb(45,45,45));
            }
        };
        EventHandler<MouseEvent> enterGame = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                name.replace(0, 8, ((TextField)root.lookup("#textfield")).getText());
                user.setName(name.toString());
                PromptData greetings = new PromptData("Welcome " + name.toString(), "");
                System.out.println(name.toString());
                try {
                    game();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        EventHandler<MouseEvent> onButtonExit = new EventHandler<MouseEvent>() {
            @Override
            public void handle (MouseEvent event) {
                register.setStyle("-fx-background-color: rgba(115,115,115,0.75);" + buttonCss);
                register.setTextFill(Color.ALICEBLUE);
            }
        };
        register.addEventHandler(MouseEvent.MOUSE_ENTERED, onButtonEnter);
        register.addEventHandler(MouseEvent.MOUSE_EXITED, onButtonExit);
        register.addEventHandler(MouseEvent.MOUSE_CLICKED, enterGame);
        return name.toString();
    }

    public void game() throws IOException{
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/MainMenu.fxml"));
        Scene scene = Graphics.getInstance().getMainScene();
//        Image image = null;
//        try {
//            image = new Image(getClass()
//                    .getResource("../../Files").toURI().toString());
//        } catch (URISyntaxException e) {
//            e.printStackTrace();
//        }
//        BackgroundImage backgroundImage = new BackgroundImage(image,
//                BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER,
//                new BackgroundSize(scene.getWidth(), scene.getHeight(), false, false, true, true));
//        Background background = new Background(backgroundImage);
//        ((Pane)root.lookup("#pane")).setBackground(background);
        scene.setRoot(root);
    }

    private String availableAction(String action, String previousResult){
        switch (action){
            case "Help":
                previousResult = help();
                break;
            case "Again":
                if (previousResult == null){
                    System.out.println("no previous instruction available");
                    break;
                }else {
                    System.out.println(previousResult);
                }
                break;
            case "Exit":
                endGame();
                return "";
            case "Edit Inventory":
                inventoryControl.mainThread();
                break;
            case "Enter Shop":
                shopControl.mainController();
                break;
            case "Battle":
                battleControl.startBattle(user);
                try {
                    gameDetailController.saveGame();
                }catch (IOException e){
                    System.out.println(e.toString());
                }
                break;
            default:
                System.out.println("invalid input");
                previousResult = "invalid input";
                break;
        }
        return previousResult;
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
    // TODO needs to be completed
    private void endGame (){
        System.out.println("Do you realy want to quit the game?(I kinda have to :(, No)");
        Scanner scan = new Scanner(System.in);
        if ("No".equals( scan.nextLine())) {
            return;
        }
        System.out.println("Do you want to save the game?(Yes, No, reset)");
        switch (scan.next()){
            case "Yes":
                try{
                    gameDetailController.saveGame();
                }catch (IOException e){
                    System.out.println("file output problem");
                }
                break;
            case "No":
                break;
            case "reset":
                resetGame();
            default:
                System.out.println("invalid input");
                break;
        }
        System.exit(0);
    }

    /**
     * restarting the data of the game via copying the initial game data which is saved in "initial" folder
     * in "save" folder which is the source of the data in our game
     */
    private void resetGame(){
        String path1 = "/home/gilgamesh/Desktop/Programs/Java/Project/projectAp/src/Files/initial/";
        String path2 = "/home/gilgamesh/Desktop/Programs/Java/Project/projectAp/src/Files/save/";
        try {
            Files.copy(Paths.get(path1 + "backPack.txt"), new FileOutputStream(path2 + "backPack.txt"));
            Files.copy(Paths.get(path1 + "deck.txt"), new FileOutputStream(path2 + "deck.txt"));
            Files.copy(Paths.get(path1 + "inventory.txt"), new FileOutputStream(path2 + "inventory.txt"));
            Files.copy(Paths.get(path1 + "shop.txt"), new FileOutputStream(path2 + "shop.txt"));
            Files.copy(Paths.get(path1 + "UserInfo.txt"), new FileOutputStream(path2 + "UserInfo.txt"));
            System.out.println("the data of the game has been reset,\nrestart the game to notice the change.");
        }catch (IOException e){
            System.out.println("File not found!");
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