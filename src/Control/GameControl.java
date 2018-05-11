package Control;

import Modules.BattleGround.Deck;
import Modules.Card.Card;
import Modules.Card.Monsters.Normal;
import Modules.Card.Spell.*;
import Modules.ItemAndAmulet.Amulet;
import Modules.ItemAndAmulet.Item;
import Modules.Shop.AmuletShop;
import Modules.Shop.CardShop;
import Modules.Shop.ItemShop;
import Modules.User.Inventory.AmuletInventory;
import Modules.User.Inventory.CardInventory;
import Modules.User.Inventory.ItemInventory;
import Modules.User.User;
import Modules.Warrior.BackPack;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import javax.imageio.IIOException;
import java.io.*;
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
    private HashMap<String, Item> itemHashMap;
    private HashMap<String, Card> cardHashMap;
    private HashMap<String, Amulet> amuletHashMap;
    private Deck deck;
    private BackPack backPack;
    private User user;
    private ItemInventory itemInventory;
    private CardInventory cardInventory;
    private AmuletInventory amuletInventory;
    private ItemShop itemShop;
    private CardShop cardShop;
    private AmuletShop amuletShop;
    private ShopControl shopControl;
    private BattleControl battleControl;


    public GameControl(String fileDirectory){
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
     * @throws IOException
     */
    public void startGame()throws IOException{
        //readying the file reader
        BufferedReader fileReader = new BufferedReader(new FileReader(fileDirectory + "MonsterCard"));
        String line;
        //reading line by line to create normal monsters
        while((line = fileReader.readLine()) != null) {
            String parts[] = line.split("-");
            Card monster = new Normal(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Boolean.parseBoolean(parts[4]),Boolean.parseBoolean(parts[5]), parts[6]);
            cards.add(monster);
            cardHashMap.put(monster.getName(), monster);
        }

        //TODO instantiate other types of monster cards
        //TODO instantiate spells
        cardHashMap.put("Blood Feast", new BloodFeast());
        cardHashMap.put("First Aid Kit", new FirstAidKit());
        cardHashMap.put("Healing War", new HealingWard());
        cardHashMap.put("Greater Purge", new GreaterPurge());
        cardHashMap.put("Poisonous Cauldron", new PoisonousCauldron());
        cardHashMap.put("Throwing Knives", new ThrowingKnives());
        cardHashMap.put("War Drum", new WarDrum());
        //TODO instantiate commanders
        //TODO instantiate items
        //TODO instantiate amulets

        backPackStart();
        deckStart();
        inventoryStart();
        shopStart();
        //starting the user object
        int gills;
        int level;
        fileReader = new BufferedReader(new FileReader(fileDirectory + "userInfo.txt"));
        level = Integer.parseInt(fileReader.readLine().split(":")[1]);
        gills = Integer.parseInt(fileReader.readLine().split(":")[1]);
        user = new User(cardInventory, itemInventory, amuletInventory, deck, gills, level, "player1", backPack);
        fileReader.close();
    }
    //readying the backPack
    private void backPackStart() throws IOException{
        String line;
        BufferedReader fileReader = new BufferedReader(new FileReader(fileDirectory + "backPack.txt"));
        this.backPack = new BackPack();
        while((line = fileReader.readLine()) != null){
            String parts[] = line.split(" -");
            if (itemHashMap.containsKey(parts[0]))
                this.backPack.add(itemHashMap.get(parts[0]), Integer.parseInt(parts[1]));
            else
                this.backPack.add(amuletHashMap.get(parts[0]));
        }
    }
    //readying the deck
    private void deckStart()throws IOException{
        String line;
        BufferedReader fileReader = new BufferedReader(new FileReader(fileDirectory + "deck.txt"));
        this.deck = new Deck();
        while((line = fileReader.readLine()) != null){
            String parts[] = line.split(" -");
            try {
                if (!cardHashMap.containsKey(parts[0]))
                    throw new CardException("card not available");
                this.deck.add(cardHashMap.get(parts[0]), Integer.parseInt(parts[1]));
            }catch (CardException e){
                System.out.println("");
            }
        }
    }
    //readying the inventory
    private void inventoryStart()throws IOException{
        itemInventory = new ItemInventory(backPack);
        amuletInventory = new AmuletInventory(backPack);
        cardInventory = new CardInventory(deck);
        String inventoryName = "items:";
        String line;
        BufferedReader fileReader = new BufferedReader(new FileReader(fileDirectory + "inventory.txt"));
        while((line = fileReader.readLine()) != null){
            String parts[] = line.split(" -");
            if (parts.length != 1){
                if (inventoryName.equals("items:")){
                    for (int i = 0; i < Integer.parseInt(parts[1]); i++)
                        itemInventory.add(itemHashMap.get(parts[0]));
                }
                if (inventoryName.equals("amulets:")){
                    for (int i = 0; i < Integer.parseInt(parts[1]); i++)
                        amuletInventory.add(amuletHashMap.get(parts[0]));
                }
                if (inventoryName.equals("cards:")){
                    for (int i = 0; i < Integer.parseInt(parts[1]); i++)
                        cardInventory.add(cardHashMap.get(parts[0]));
                }
            }else{
                inventoryName = line;
            }
        }

    }
    //readying the shops
    private void shopStart()throws IOException{
        itemShop = new ItemShop();
        amuletShop = new AmuletShop();
        cardShop = new CardShop();
        String shopName = "items:";
        String line;
        BufferedReader fileReader = new BufferedReader(new FileReader(fileDirectory + "shop.txt"));
        while((line = fileReader.readLine()) != null){
            String parts[] = line.split(" -");
            if (parts.length != 1){
                if (shopName.equals("items:")){
                    //we have indefinite numbers of items in shop
                        itemShop.addItem(itemHashMap.get(parts[0]));
                }
                if (shopName.equals("amulets:")){
                    //from every amulet we have one
                        amuletShop.addAmulet(amuletHashMap.get(parts[0]));
                }
                if (shopName.equals("cards:")){
                    for (int i = 0; i < Integer.parseInt(parts[1]); i++)
                        cardShop.addCard(cardHashMap.get(parts[0]));
                }
            }else{
                shopName = line;
            }
        }
    }

    public void game(){
        Scanner scan = new Scanner(System.in);
        String action;
        String previousResult = null;
        while (true) {
            action = scan.next();
            previousResult = availableAction(action, previousResult);
        }
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
            case "Enter Shop":
                shopControl.mainController();
                // TODO needs to be completed
                break;
            case "Battle":
                battleControl.startBattle(user);
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
                "3. Exit: save and exit the game");
        return "1. Enter Shop:To enter shop and buy or sell cards and items\n" +
                "2. Battle:To enter the next battle\n" +
                "3. Exit: save and exit the game";
//        System.out.println("2. Edit Inventory: To edit your amulet or deck");
//        System.out.println("3. Next: To go to deck and amulet customization");
    }

    /**
     * saves the game when endGame() method is called
     * @throws IOException
     */
    // TODO needs to be completed
    private void saveGame() throws IOException{
        FileWriter fileWriter = null;
        saveBackPack(fileWriter);
        saveDeck(fileWriter);
        saveInventory(fileWriter);
        saveShop(fileWriter);
        fileWriter = new FileWriter(fileDirectory + "userInfo.txt", false);
        fileWriter.write("level:" + user.getLevel());
        fileWriter.write("gills:" + user.getGills());
    }

    private void saveBackPack(FileWriter fileWriter) throws IOException{
        fileWriter = new FileWriter(fileDirectory + "backPack.txt", false);
        for (Item item:backPack.getItems()) {
            fileWriter.write(item.getName() + " -" + backPack.getNumberOfItems(item.getName()) + "\n");
        }
        if (backPack.getAmulet() != null) {
            fileWriter.write(backPack.getAmulet().getName());
        }
        fileWriter.close();
    }

    private void saveDeck(FileWriter fileWriter) throws IOException{
        fileWriter = new FileWriter(fileDirectory + "deck.txt", false);
        for (Card card:deck.getCards()) {
            fileWriter.write(card.getName() + " -" + deck.getNumberOfCards(card.getName()) + "\n");
        }
        fileWriter.close();
    }

    private void saveInventory(FileWriter fileWriter) throws IOException{
        fileWriter = new FileWriter(fileDirectory + "inventory.txt", false);

        fileWriter.write("items:\n");
        for (Item item:itemInventory.getItems()){
            fileWriter.write(item.getName() + " -" + itemInventory.getNumberOfItem(item.getName()) + "\n");
        }

        fileWriter.write("amulets:\n");
        for (Amulet amulet:amuletInventory.getAmulets()){
            fileWriter.write(amulet.getName() +  "\n");
        }

        fileWriter.write("cards:\n");
        for (Card card:cardInventory.getCards()){
            fileWriter.write(card.getName() + " -" + cardInventory.getNumberOfCards(card.getName()) + "\n");
        }
        fileWriter.close();
    }

    private void saveShop(FileWriter fileWriter) throws IOException{
        fileWriter = new FileWriter(fileDirectory + "shop.txt");

        fileWriter.write("items:\n");
        for (Item item:itemShop.getItems()){
            fileWriter.write(item.getName() +  "\n");
        }

        fileWriter.write("amulets:\n");
        for (Amulet amulet:amuletShop.getAmulets()){
            fileWriter.write(amulet.getName() + "\n");
        }

        fileWriter.write("cards:\n");
        for (Card card:cardShop.getCards()){
            fileWriter.write(card.getName() + " -" + cardShop.getNumberOfCard(card.getName()) + "\n");
        }
        fileWriter.close();
    }

    /**
     * ends the game
     * checks if file data needs to be reset cause of completing the game
     * saves the necessary data
     */
    // TODO needs to be completed
    public void endGame(){
        System.out.println("Do you want to save the game?(Yes, No)");
        Scanner scan = new Scanner(System.in);
        switch (scan.next()){
            case "Yes":
                try{
                    saveGame();
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
}

class CardException extends Exception{
    CardException(String detail){
        System.out.println(detail);
    }
}