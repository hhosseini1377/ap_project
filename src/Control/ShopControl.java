package Control;

import Modules.Card.Monsters.Monster;
import Modules.Card.Spell.Spell;
import Modules.ItemAndAmulet.Amulet;
import Modules.Shop.AmuletShop;
import Modules.Shop.CardShop;
import Modules.Shop.ItemShop;
import Modules.User.User;
import View.ShopView.AmuletShopView;
import View.ShopView.CardShopView;
import View.ShopView.ItemShopView;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ShopControl {
    private CardShop cardShop;
    private ItemShop itemShop;
    private AmuletShop amuletShop;
    private AmuletShopView amuletShopView;
    private ItemShopView itemShopView;
    private CardShopView cardShopView;
    private User User;
    private InventoryControl inventoryControl;

    public ShopControl(CardShop CardShop,ItemShop ItemShop,AmuletShop AmuletShop,User User, InventoryControl inventoryControl){
        this.inventoryControl = inventoryControl;
        this.cardShop = CardShop;
        this.itemShop = ItemShop;
        this.amuletShop = AmuletShop;
        this.User = User;
        cardShopView = new CardShopView();
        itemShopView = new ItemShopView();
        amuletShopView = new AmuletShopView();
    }

    private void cardShopController(){
        cardShopView.PrintCardShopDetails(cardShop.getCards(),User.getCardInventory().getNumberOfCards());
        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();
        Matcher tradeMatcher  = Pattern.compile("([^ ]*) (.*) - (\\d*)").matcher(request);
        Matcher infoMatcher = Pattern.compile("(Info) (.*)").matcher(request);
        while(!request.equals("Exit")){
            String[] resreq = request.split(" ");
            switch (resreq[0]) {
                case "Buy":
                    tradeMatcher.matches();
                    int numberToBuy = Integer.parseInt(tradeMatcher.group(3));
                    cardShopView.printBuyCardsDetails(buyCard(tradeMatcher.group(2), numberToBuy), numberToBuy, tradeMatcher.group(2));
                    break;
                case "Help":
                    cardShopView.PrintCardShopHelpDetails();
                    break;
                case "Sell":
                    tradeMatcher.matches();
                    int numberToSell = Integer.parseInt(tradeMatcher.group(3));
                    cardShopView.printSellCardDetails(sellCard(tradeMatcher.group(2), numberToSell), numberToSell,tradeMatcher.group(2));
                    break;
                case "Info":
                    infoMatcher.matches();
                    if (cardShop.getCard(infoMatcher.group(2)) instanceof Monster) {
                        cardShopView.printInfoOfMonsterCard(infoMatcher.group(2), ((Monster) cardShop.getCard(infoMatcher.group(2))).getInitialHP(), ((Monster) cardShop.getCard(infoMatcher.group(2))).getInitialAP(), cardShop.getCard(infoMatcher.group(2)).getManaPoint(), ((Monster) cardShop.getCard(infoMatcher.group(2))).getMonsterKind(), ((Monster) cardShop.getCard(infoMatcher.group(2))).getMonsterTribe(), ((Monster) cardShop.getCard(infoMatcher.group(2))).isOffenseType(), ((Monster) cardShop.getCard(infoMatcher.group(2))).isNimble(), ((Monster) cardShop.getCard(infoMatcher.group(2))).toString());
                    } else
                        cardShopView.printInfoOfSpellCard(infoMatcher.group(2), ((Spell) cardShop.getCard(infoMatcher.group(2))).getManaPoint(), ((Spell) cardShop.getCard(infoMatcher.group(2))).toString());
                    break;
                case "Edit":
//                    cardShopView.printEditDeckDetails(User.getCardInventory().getNumberOfCards(), User.getDeck().getCards());
                    inventoryControl.editDeck();
                    break;
                default:
                    System.out.println("invalid input");
            }
            request=scanner.nextLine();
        }
        mainController();
    }

    private void amuletShopController(){
        amuletShopView.printAmuletShopDetails(amuletShop.getAmulets(),User.getBackPack().getAmulet(),User.getAmuletInventory().getNumberOfAmulet());
        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();
        Matcher tradeMatcher  = Pattern.compile("([^ ]*) (.*) - (\\d*)").matcher(request);
        Matcher infoMatcher = Pattern.compile("(Info) (.*)").matcher(request);
        while(!request.equals("Exit")){
            String[] resreq = request.split(" ");
            switch (resreq[0]) {
                case "Buy":
                    tradeMatcher.matches();
                    int numberToBuy = Integer.parseInt(tradeMatcher.group(3));
                    amuletShopView.printBuyAmuletDetails(buyAmulet(tradeMatcher.group(2), numberToBuy), numberToBuy, tradeMatcher.group(2));
                    break;
                case "Help":
                    amuletShopView.printHelpDetails();
                    break;
                case "Sell":
                    tradeMatcher.matches();
                    int numberToSell = Integer.parseInt(tradeMatcher.group(3));
                    amuletShopView.printSellAmuletDetails(sellAmulet(tradeMatcher.group(2), numberToSell), numberToSell, tradeMatcher.group(2));
                    break;
                case "Info":
                    tradeMatcher.matches();
                    amuletShopView.printInfoOfAnAmulet(tradeMatcher.group(2), amuletShop.getAmulet(tradeMatcher.group(2)).toString());
                    break;
                case "Edit":
//                    amuletShopView.PrintEditAmuletDetails(User.getAmuletInventory().getAmulets(), User.getBackPack().getAmulet(), User.getBackPack().isAmuletEquipped());
                    inventoryControl.editBackPack();
                    break;
                default:
                    System.out.println("invalid input");
            }
            request=scanner.nextLine();
        }
        mainController();
    }

    private void itemShopController(){
        itemShopView.printItemShopdetails(User.getItemInventory().getNumberOfItem(),itemShop.getItems());
        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();
        Matcher tradeMatcher  = Pattern.compile("([^ ]*) (.*) - (\\d*)").matcher(request);
        Matcher infoMatcher = Pattern.compile("(Info) (.*)").matcher(request);
        while(!request.equals("Exit")){
            String[] resreq = request.split(" ");
            switch (resreq[0]) {
                case "Buy":
                    tradeMatcher.matches();
                    int numberToBuy = Integer.parseInt(tradeMatcher.group(3));
                    itemShopView.printBuyItemDetails(sellItem(tradeMatcher.group(2), numberToBuy), numberToBuy,tradeMatcher.group(2));
                    break;
                case "Help":
                    itemShopView.printHelpDetails();
                    break;
                case "Sell":
                    tradeMatcher.matches();
                    int numberToSell = Integer.parseInt(tradeMatcher.group(3));
                    itemShopView.printSellItemDetails(sellItem(tradeMatcher.group(2), numberToSell), numberToSell,tradeMatcher.group(2));
                    break;
                case "Info":
                    infoMatcher.matches();
                    itemShopView.printInfoOfItem(infoMatcher.group(2), User.getItemInventory().getItem(infoMatcher.group(2)).toString());
                    break;
                default:
                    System.out.println("invalid input");
            }
            request=scanner.nextLine();
        }
        mainController();
    }

    public void mainController() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("You've entered to shops");
        System.out.println("Remaining Gill:" + User.getGills() + "Gill");
        System.out.println("1. Card Shop");
        System.out.println("2. Item Shop");
        System.out.println("3. Amulet Shop");
        System.out.println("4. Exit");

        while(true) {
            String request = scanner.nextLine();
            switch (request) {
                case "Help":
                    System.out.println("Remaining Gill:" + User.getGills() + "Gill");
                    System.out.println("1. Card Shop");
                    System.out.println("2. Item Shop");
                    System.out.println("3. Amulet Shop");
                    System.out.println("4. Exit");
                    break;
                case "Card Shop":
                    cardShopController();
                    break;
                case "Amulet Shop":
                    amuletShopController();
                    break;
                case "Item Shop":
                    itemShopController();
                    break;
                case "Exit":
                    return;
                default:
                    System.out.println("invalid input");
                    break;
            }
        }
    }

   private Boolean buyCard(String cardName,int numberToBuy){
        if(User.canBuy(cardShop.getCard(cardName).getGillCost()*numberToBuy)){
            for(int counter =0;counter<numberToBuy;counter++) {
                User.buy(cardShop.getCard(cardName).getGillCost());
                User.getCardInventory().add(cardShop.getCard(cardName));
                cardShop.removeCard(cardName);
            }
            return true;
        }
        else
            return false;
    }

    public Boolean buyItem(String itemName,int numberToBuy){
        if(User.canBuy(itemShop.getItem(itemName).getGillCost()*numberToBuy)){
            for(int counter =0;counter<numberToBuy;counter++) {
                User.buy(itemShop.getItem(itemName).getGillCost());
                User.getItemInventory().add(itemShop.getItem(itemName));
//                ItemShop.RemoveItem(itemName);
            }
            return true;
        }
        else
            return false;
    }

    public boolean buyAmulet(String amuletName,int numberToBuy){
        if(User.canBuy(amuletShop.getAmulet(amuletName).getGillCost()*numberToBuy)) {
            for (int counter = 0; counter < numberToBuy; counter++) {
                User.buy(amuletShop.getAmulet(amuletName).getGillCost());
                User.getAmuletInventory().add(amuletShop.getAmulet(amuletName));
                amuletShop.removeAmulet(amuletName);
            }
            return true;
        }
        else
            return false;
    }

    private boolean sellCard(String CardName,int NumberToSell) {
        if (User.getCardInventory().getNumberOfCards(CardName) - User.getDeck().getNumberOfCards(CardName) >= NumberToSell) {
            for (int counter = 0; counter < NumberToSell; counter++) {
                User.getCardInventory().remove(CardName);
            }
            return true;
        }
        return false;
    }

    public boolean sellItem(String ItemName,int NumberToSell){
        if(User.getItemInventory().getNumberOfItem(ItemName) -  User.getBackPack().getNumberOfItems(ItemName) >= NumberToSell){
            for(int counter=0;counter<NumberToSell;counter++)
                User.getItemInventory().remove(ItemName);
            return true;
        }
        return false;
    }

    public boolean sellAmulet(String amuletName,int numberToSell){
        if (User.getAmuletInventory().hasAmulet(amuletName) && !User.getBackPack().getAmulet().getName().equals(amuletName)) {
            User.getAmuletInventory().remove(amuletName);
            return true;
        }
        return false;
    }


}
