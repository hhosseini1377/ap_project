package Control.GameControll;

import Modules.BattleGround.Deck;
import Modules.Card.Card;
import Modules.Card.Monsters.Atlantian.Kraken;
import Modules.Card.Monsters.Atlantian.NagaSiren;
import Modules.Card.Monsters.Atlantian.Neptun;
import Modules.Card.Monsters.Atlantian.SeaSerpent;
import Modules.Card.Monsters.Demonic.*;
import Modules.Card.Monsters.DragonBreed.BlueDragon;
import Modules.Card.Monsters.DragonBreed.GreaterDragon;
import Modules.Card.Monsters.DragonBreed.Igneel;
import Modules.Card.Monsters.DragonBreed.VolcanicDragon;
import Modules.Card.Monsters.Elven.ElvenDruid;
import Modules.Card.Monsters.Elven.ElvenSorceress;
import Modules.Card.Monsters.Elven.Luthien;
import Modules.Card.Monsters.Elven.NobleElf;
import Modules.Card.Spell.*;
import Modules.ItemAndAmulet.Amulet;
import Modules.ItemAndAmulet.Item;
import Modules.ItemAndAmulet.Items.*;
import Modules.Shop.AmuletShop;
import Modules.Shop.CardShop;
import Modules.Shop.ItemShop;
import Modules.User.Inventory.AmuletInventory;
import Modules.User.Inventory.CardInventory;
import Modules.User.Inventory.ItemInventory;
import Modules.Warrior.BackPack;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

class GameDetailController {
     private final String fileDirectory;
     private final GameControl gameControl;

    GameDetailController(String fileDirectory, GameControl gameControl){
        this.fileDirectory = fileDirectory;
        this.gameControl = gameControl;
    }

    //initializing the monster cards and thus the maps
    void monsterStart(){
        gameControl.cardHashMap.put("Cerberus, Gatekeeper of Hell", new Cerberus());
        gameControl.cardHashMap.put("Dark Knight", new DarkKnight());
        gameControl.cardHashMap.put("Kraken", new Kraken());
        gameControl.cardHashMap.put("Neptune, King of Atlantis", new Neptun());
        gameControl.cardHashMap.put("Blue Dragon", new BlueDragon());
        gameControl.cardHashMap.put("Greater Dragon", new GreaterDragon());
        gameControl.cardHashMap.put("Volcanic Dragon", new VolcanicDragon());
        gameControl.cardHashMap.put("Igneel, The Dragon King", new Igneel());
        gameControl.cardHashMap.put("Luthien, The High Priestess", new Luthien());
        gameControl.cardHashMap.put("Naga Siren", new NagaSiren());
        gameControl.cardHashMap.put("Sea Serpent", new SeaSerpent());
        gameControl.cardHashMap.put("Evil Eye", new EvilEye());
        gameControl.cardHashMap.put("Goblin Shaman", new GoblinShaman());
        gameControl.cardHashMap.put("Necromancer", new Necromancer());
        gameControl.cardHashMap.put("Ogre Magi", new OgreMagi());
        gameControl.cardHashMap.put("Ogre Warchief", new OgreWarchief());
        gameControl.cardHashMap.put("Undead Mage", new UndeadMage());
        gameControl.cardHashMap.put("Vampire Acolyte", new VampireAcolyte());
        gameControl.cardHashMap.put("Vampire Prince", new VampirePrince());
        gameControl.cardHashMap.put("Elven Druid", new ElvenDruid());
        gameControl.cardHashMap.put("Elven Sorceress", new ElvenSorceress());
        gameControl.cardHashMap.put("Noble Elf", new NobleElf());
    }

    //initializing the spell cards and thus the maps
     void spellStart(){
         gameControl.cardHashMap.put("Blood Feast", new BloodFeast());
         gameControl.cardHashMap.put("First Aid Kit", new FirstAidKit());
         gameControl.cardHashMap.put("Healing Ward", new HealingWard());
         gameControl.cardHashMap.put("Greater Purge", new GreaterPurge());
         gameControl.cardHashMap.put("Poisonous Cauldron", new PoisonousCauldron());
         gameControl.cardHashMap.put("Throwing Knives", new ThrowingKnives());
         gameControl.cardHashMap.put("War Drum", new WarDrum());
         gameControl.cardHashMap.put("Reaper's Scythe", new ReaperScythe());
         gameControl.cardHashMap.put("Meteor Shower", new MeteorShower());
         gameControl.cardHashMap.put("Lunar Blessing", new LunarBlessing());
         gameControl.cardHashMap.put("Strategic Retreat", new StrategicRetreat());
         gameControl.cardHashMap.put("Tsunami", new Tsunami());
         gameControl.cardHashMap.put("Take All You Can", new TakeAllYouCan());
         gameControl.cardHashMap.put("Arcane Bolt", new ArcaneBolt());
         gameControl.cardHashMap.put("Magic Seal", new MagicSeal());
    }

    //initializing the items and thus the maps
     void itemStart(){
        GreaterRestorative greaterRestorative= new GreaterRestorative();
         gameControl.itemHashMap.put(greaterRestorative.getName(), greaterRestorative);
        LargeHPPotion largeHPPotion = new LargeHPPotion();
         gameControl.itemHashMap.put(largeHPPotion.getName(), largeHPPotion);
        LargeMPPotion largeMPPotion = new LargeMPPotion();
         gameControl.itemHashMap.put(largeMPPotion.getName(), largeMPPotion);
        LesserRestorative lesserRestorative = new LesserRestorative();
         gameControl.itemHashMap.put(lesserRestorative.getName(), lesserRestorative);
        MediumHPPotion mediumHPPotion = new MediumHPPotion();
         gameControl.itemHashMap.put(mediumHPPotion.getName(), mediumHPPotion);
        MediumMPPotion mediumMPPotion = new MediumMPPotion();
         gameControl.itemHashMap.put(mediumMPPotion.getName(), mediumMPPotion);
        SmallHPPotion smallHPPotion = new SmallHPPotion();
         gameControl.itemHashMap.put(smallHPPotion.getName(), smallHPPotion);
        SmallMPPotion smallMPPotion = new SmallMPPotion();
         gameControl.itemHashMap.put(smallMPPotion.getName(), smallMPPotion);
         gameControl.itemHashMap.put("Mystic Hourglass", new MysticHourglass(gameControl));
    }

    //readying the backPack
     void backPackStart() throws IOException {
        String line;
        BufferedReader fileReader = new BufferedReader(new FileReader(fileDirectory + "backPack.txt"));
        this.gameControl.backPack = new BackPack();
        while((line = fileReader.readLine()) != null){
            String parts[] = line.split(" -");
            if (gameControl.itemHashMap.containsKey(parts[0]))
                for (int i = 0; i < Integer.parseInt(parts[1]); i++)
                    try {
                        this.gameControl.backPack.add((Item) gameControl.itemHashMap.get(parts[0]).clone(), 1);
                    }catch (CloneNotSupportedException e){
                        System.out.println("Clone not supported" + e.getStackTrace());
                    }
            else
                this.gameControl.backPack.add(gameControl.amuletHashMap.get(parts[0]));
        }
    }

    //readying the deck
     void deckStart()throws IOException{
        String line;
        BufferedReader fileReader = new BufferedReader(new FileReader(fileDirectory + "deck.txt"));
        this.gameControl.deck = new Deck();
        while((line = fileReader.readLine()) != null){
            String parts[] = line.split(" -");
            try {
                if (!gameControl.cardHashMap.containsKey(parts[0]))
                    throw new CardException();
                for (int i = 0; i < Integer.parseInt(parts[1]); i++) {
                    try {
                        this.gameControl.deck.add((Card) gameControl.cardHashMap.get(parts[0]).clone(), 1);
                    }catch (CloneNotSupportedException e){
                        System.out.println("Clone not supported" + e.getStackTrace());
                    }
                }
            }catch (CardException e){
                System.out.println("");
            }
        }
    }

    //readying the inventory
     void inventoryStart()throws IOException{
         gameControl.itemInventory = new ItemInventory(gameControl.backPack);
         gameControl.amuletInventory = new AmuletInventory(gameControl.backPack);
         gameControl.cardInventory = new CardInventory(gameControl.deck);
        String inventoryName = "items:";
        String line;
        BufferedReader fileReader = new BufferedReader(new FileReader(fileDirectory + "inventory.txt"));
        while((line = fileReader.readLine()) != null){
            String parts[] = line.split(" -");
            if (!line.contains(":")){
                if (inventoryName.equals("items:")){
                    for (int i = 0; i < Integer.parseInt(parts[1]); i++)
                        try {
                            gameControl.itemInventory.add((Item) gameControl.itemHashMap.get(parts[0]).clone());
                        }catch (CloneNotSupportedException e){
                            System.out.println("Clone not supported");
                        }
                }
                if (inventoryName.equals("amulets:")){
                        gameControl.amuletInventory.add(gameControl.amuletHashMap.get(parts[0]));
                }
                if (inventoryName.equals("cards:")){
                    for (int i = 0; i < Integer.parseInt(parts[1]); i++)
                        try {
                            gameControl.cardInventory.add((Card) gameControl.cardHashMap.get(parts[0]).clone());
                        }catch (CloneNotSupportedException e){
                            System.out.println(e);
                        }
                }
            }else{
                inventoryName = line;
            }
        }

    }

    //readying the shops
     void shopStart()throws IOException{
         gameControl.itemShop = new ItemShop();
         gameControl.amuletShop = new AmuletShop();
         gameControl.cardShop = new CardShop();
        String shopName = "items:";
        String line;
        BufferedReader fileReader = new BufferedReader(new FileReader(fileDirectory + "shop.txt"));
        while((line = fileReader.readLine()) != null){
            String parts[] = line.split(" -");
            if (!line.contains(":")){
                if (shopName.equals("items:")){
                    //we have indefinite numbers of items in shop
                    gameControl.itemShop.addItem(gameControl.itemHashMap.get(parts[0]));
                }
                if (shopName.equals("amulets:")){
                    //from every amulet we have one
                    gameControl.amuletShop.addAmulet(gameControl.amuletHashMap.get(parts[0]));
                }
                if (shopName.equals("cards:")){
                    for (int i = 0; i < Integer.parseInt(parts[1]); i++)
                        try {
                            gameControl.cardShop.addCard((Card) gameControl.cardHashMap.get(parts[0]).clone());
                        }catch (CloneNotSupportedException e){
                            System.out.println(e);
                        }
                }
            }else{
                shopName = line;
            }
        }
    }

    /**
     * saves the game when endGame() method is called
     * @throws IOException if the file input is wrong
     */
    // TODO needs to be completed
     public void saveGame() throws IOException{
        FileWriter fileWriter = null;
        saveBackPack();
        saveDeck();
        saveInventory();
        saveShop();
        fileWriter = new FileWriter(fileDirectory + "userInfo.txt", false);
        fileWriter.write("level:" + gameControl.user.getLevel());
        fileWriter.write("\ngills:" + gameControl.user.getGills());
        fileWriter.close();
    }

     private void saveBackPack () throws IOException{
         FileWriter fileWriter = new FileWriter (fileDirectory + "backPack.txt", false);
        for (Item item:gameControl.backPack.getItems()) {
            fileWriter.write(item.getName() + " -" + gameControl.backPack.getNumberOfItems(item.getName()) + "\n");
        }
        if (gameControl.backPack.getAmulet() != null) {
            fileWriter.write(gameControl.backPack.getAmulet().getName());
        }
        fileWriter.close();
    }

     private void saveDeck () throws IOException{
         FileWriter fileWriter = new FileWriter (fileDirectory + "deck.txt", false);
         Card lastCard = null;
         for (Card card:gameControl.deck.getCards()) {
             if (lastCard == null || !card.getName().equals(lastCard.getName())) {
                 fileWriter.write(card.getName() + " -" + gameControl.deck.getNumberOfCards(card.getName()) + "\n");
                 lastCard = card;
             }
         }
         fileWriter.close();
    }

     private void saveInventory () throws IOException{
         FileWriter fileWriter = new FileWriter (fileDirectory + "inventory.txt", false);

        fileWriter.write("items:\n");
        for (Item item:gameControl.itemInventory.getItems()){
            fileWriter.write(item.getName() + " -" + gameControl.itemInventory.getNumberOfItem(item.getName()) + "\n");
        }

        fileWriter.write("amulets:\n");
        for (Amulet amulet:gameControl.amuletInventory.getAmulets()){
            fileWriter.write(amulet.getName() +  "\n");
        }

        fileWriter.write("cards:\n");
        for (Card card:gameControl.cardInventory.getCards()){
            fileWriter.write(card.getName() + " -" + gameControl.cardInventory.getNumberOfCards(card.getName()) + "\n");
        }
        fileWriter.close();
    }

     private void saveShop () throws IOException{
         FileWriter fileWriter = new FileWriter (fileDirectory + "shop.txt");

        fileWriter.write("items:\n");
        for (Item item:gameControl.itemShop.getItems()){
            fileWriter.write(item.getName() +  "\n");
        }

        fileWriter.write("amulets:\n");
        for (Amulet amulet:gameControl.amuletShop.getAmulets()){
            fileWriter.write(amulet.getName() + "\n");
        }

        fileWriter.write("cards:\n");
        Card lastCard = null;
        for (Card card:gameControl.cardShop.getCards()){
            if (lastCard == null || !card.getName().equals(lastCard.getName())){
                fileWriter.write(card.getName() + " -" + gameControl.cardShop.getNumberOfCard(card.getName()) + "\n");
                lastCard = card;
            }
        }
        fileWriter.close();
    }

}
