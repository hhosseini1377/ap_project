package Control;

import Modules.BattleGround.Deck;
import Modules.Card.Card;
import Modules.Card.Monsters.Normal;
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

import java.io.*;
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
            String parts[] = line.split(" ");
            Card monster = new Normal(parts[0], Integer.parseInt(parts[1]), Integer.parseInt(parts[2]), Integer.parseInt(parts[3]), Boolean.parseBoolean(parts[4]),Boolean.parseBoolean(parts[5]), parts[6]);
            cards.add(monster);
            cardHashMap.put(monster.getName(), monster);
        }

        //TODO instantiate other types of monster cards
        //TODO instantiate spells
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
            for (int i = 0; i < Integer.parseInt(parts[1]); i++) {
                if (itemHashMap.containsKey(parts[0]))
                    this.backPack.add(itemHashMap.get(parts[0]));
                else
                    this.backPack.add(amuletHashMap.get(parts[0]));
            }
        }
    }
    //readying the deck
    private void deckStart()throws IOException{
        String line;
        BufferedReader fileReader = new BufferedReader(new FileReader(fileDirectory + "deck.txt"));
        this.deck = new Deck();
        while((line = fileReader.readLine()) != null){
            String parts[] = line.split(" -");
            for (int i = 0; i < Integer.parseInt(parts[1]); i++){
                this.deck.add(cardHashMap.get(parts[0]));
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
                    for (int i = 0; i < Integer.parseInt(parts[1]); i++)
                        itemShop.addItem(itemHashMap.get(parts[0]));
                }
                if (shopName.equals("amulets:")){
                    for (int i = 0; i < Integer.parseInt(parts[1]); i++)
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
        FileWriter fileWriter = new FileWriter(fileDirectory + "backPack.txt", false);
        Item lastItem = backPack.getItems().get(0);
        int count = 0;
        for(Item item:backPack.getItems()) {
            if (!lastItem.equals(item)) {
                fileWriter.write(lastItem.toString() + count);
                count = 1;
            }
            else{
                count++;
            }
            lastItem = item;
        }
        Amulet amulet = backPack.getAmulet();
        fileWriter.write(amulet.toString());
        fileWriter.close();
    }

    /**
     * ends the game
     * checks if file data needs to be reset cause of completing the game
     * saves the necessary data
     */
    // TODO needs to be completed
    public void endGame(){
        try{
            saveGame();
        }catch (IOException e){
            System.out.println("file output problem");
        }
        System.exit(0);
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
