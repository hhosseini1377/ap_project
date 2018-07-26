package Control.GameControll;

import Control.BattleControl;
import Control.InventoryControl;
import Control.ShopControl;
import Modules.BattleGround.Deck;
import Modules.Card.Card;
import Modules.Card.Monsters.Normal;
import Modules.Graphic.Graphics;
import Modules.Graphic.Menu;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Screen;

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
        Menu.getInstance().setGameControl(this);
        String userName = Menu.getInstance().getNameFromUser();
        user = new User(cardInventory, itemInventory, amuletInventory, deck, gills, level, userName, backPack);
        fileReader.close();
        inventoryControl = new InventoryControl(user);
        shopControl = new ShopControl(cardShop, itemShop, amuletShop, user, inventoryControl);
        battleControl = new BattleControl();
    }

    public void startBattle(int level){
        try {
            loadXML();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Parent root = Graphics.getInstance().getBattle().getRoot();
        assert root != null;
        Graphics.MAP_MUSIC_PLAYER.stop();
        Graphics.BATTLE_MUSIC_PLAYER.setCycleCount(-1);
        Graphics.BATTLE_MUSIC_PLAYER.play();
        Graphics.getInstance().setMusicPlayer(Graphics.BATTLE_MUSIC_PLAYER);


        playerPartControlSize();
        battleControl.startBattle(getUser(), level);
    }

    private void loadXML() throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("../../Files/Resources/Battle.fxml"));
        Graphics.getInstance().setBattle(new Scene(root));
        Graphics.getInstance().getStage().setScene(Graphics.getInstance().getBattle());
        Graphics.getInstance().getStage().setFullScreen(true);
    }

    private void playerPartControlSize(){
        double width = Graphics.SCREEN_WIDTH;
        double height = Graphics.SCREEN_HEIGHT;
        Parent root = Graphics.getInstance().getBattle().getRoot();
        VBox playerPart1 = (VBox) root.lookup("#playerPart1");
        VBox playerPart2 = (VBox) root.lookup("#playerPart2");

        //fixing the size of parts according to the page
        playerPart1.setMinHeight(height/2 - 20);
        playerPart1.setMaxHeight(height/2 - 20);
        playerPart2.setMinHeight(height/2 - 50);
        playerPart2.setMaxHeight(height/2 - 50);
        playerPart1.minWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));
        playerPart1.maxWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));
        playerPart2.minWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));
        playerPart2.maxWidthProperty().bind(Bindings.divide(Graphics.getInstance().getStage().widthProperty(), 1));

        //fixing size of fields
        HBox field1 = (HBox) root.lookup("#fieldP1");
        HBox field2 = (HBox) root.lookup("#fieldP2");
        HBox detail1 = (HBox) root.lookup("#detailP1");
        HBox detail2 = (HBox) root.lookup("#detailP2");
        field1.minHeightProperty().bind(Bindings.divide(playerPart1.minHeightProperty(), 3/2));
        detail1.minHeightProperty().bind(Bindings.divide(playerPart1.minHeightProperty(), 3));
        field2.minHeightProperty().bind(Bindings.divide(playerPart1.minHeightProperty(), 2));
        detail2.minHeightProperty().bind(Bindings.divide(playerPart1.minHeightProperty(), 2));

        //fixing size of hands
        HBox hand1 = (HBox) root.lookup("#handP1");
        hand1.minWidthProperty().bind(Bindings.divide(playerPart1.minWidthProperty(), 1.2));
        hand1.maxWidthProperty().bind(Bindings.divide(playerPart1.minWidthProperty(), 1.2));
        HBox hand2 = (HBox) root.lookup("#handP2");
        hand2.minWidthProperty().bind(Bindings.divide(playerPart2.minWidthProperty(), 1.2));
        hand2.maxWidthProperty().bind(Bindings.divide(playerPart2.minWidthProperty(), 1.2));

        //fixing images
        VBox pic1 = (VBox) root.lookup("#picContP1");
        VBox frame1 = (VBox) root.lookup("#frameContP1");
        VBox pic2 = (VBox) root.lookup("#picContP2");
        VBox frame2 = (VBox) root.lookup("#frameContP2");
        pic1.setLayoutY(60);
        frame1.setLayoutY(60);
        pic1.setLayoutX(width/2 - 40);
        frame1.setLayoutX(width/2 - 40);
        pic2.setLayoutY(height - 160);
        frame2.setLayoutY(height - 160);
        pic2.setLayoutX(width/2 - 40);
        frame2.setLayoutX(width/2 - 40);
    }

    public void shopEntrance(){
        shopControl.enterShop();
    }

    public void game() throws IOException{
        Menu.getInstance().mainMenu();
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

    public void saveGame() throws IOException {
        gameDetailController.saveGame();
    }

    /**
     * ends the game
     * checks if file data needs to be reset cause of completing the game
     * saves the necessary data
     */
    public void endGame (){
        Menu.getInstance().endGameScreen();
    }

    /**
     * restarting the data of the game via copying the initial game data which is saved in "initial" folder
     * in "save" folder which is the source of the data in our game
     */
    public void resetGame(){
        String path1 = "./src/Files/initial/";
        String path2 = "./src/Files/save/";
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
