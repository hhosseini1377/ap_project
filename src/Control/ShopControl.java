package Control;

import Modules.Card.Monsters.Monster;
import Modules.Card.Spell.Spell;
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

    public ShopControl (CardShop CardShop, ItemShop ItemShop, AmuletShop AmuletShop, User User, InventoryControl inventoryControl) {
        this.inventoryControl = inventoryControl;
        this.cardShop = CardShop;
        this.itemShop = ItemShop;
        this.amuletShop = AmuletShop;
        this.User = User;
        cardShopView = new CardShopView ();
        itemShopView = new ItemShopView ();
        amuletShopView = new AmuletShopView ();
    }

    private void cardShopController () {
        cardShopView.PrintCardShopDetails (cardShop.getCards (), User.getCardInventory ().getNumberOfCards ());
        Scanner scanner = new Scanner (System.in);
        String request = scanner.nextLine ();
        while (! request.equals ("Exit")) {
            String[] resreq = request.split (" ");
            Matcher tradeMatcher = Pattern.compile ("([^ ]*) (.*) - (\\d*)").matcher (request);
            Matcher infoMatcher = Pattern.compile ("(Info) (.*)").matcher (request);
            switch (resreq[0]) {
                case "Buy":
                    if(tradeMatcher.matches ()) {
                        int numberToBuy = Integer.parseInt(tradeMatcher.group(3));
                        try {
                            cardShopView.printBuyCardsDetails(buyCard(tradeMatcher.group(2), numberToBuy), numberToBuy, tradeMatcher.group(2));
                        } catch (Exception e) {
                            System.out.println("The entered name is wrong, make sure and try again.");
                        }
                    }else
                        System.out.println("please enter the instruction as written");
                    break;
                case "Help":
                    cardShopView.PrintCardShopHelpDetails ();
                    break;
                case "Sell":
                    if(tradeMatcher.matches ()) {
                        try {
                            int numberToSell = Integer.parseInt(tradeMatcher.group(3));
                            cardShopView.printSellCardDetails(sellCard(tradeMatcher.group(2), numberToSell), numberToSell, tradeMatcher.group(2));
                        }catch (Exception e){
                            System.out.println("The entered name is wrong, make sure and try again.");
                        }
                    }else
                        System.out.println("please enter the instructions as written");
                    break;
                case "Info":
                    if(infoMatcher.matches ()) {
                        try {
                            if (cardShop.getCard(infoMatcher.group(2)) instanceof Monster) {
                                cardShopView.printInfoOfMonsterCard(infoMatcher.group(2), ((Monster) cardShop.getCard(infoMatcher.group(2))).getInitialHP(), ((Monster) cardShop.getCard(infoMatcher.group(2))).getInitialAP(), cardShop.getCard(infoMatcher.group(2)).getManaPoint(), ((Monster) cardShop.getCard(infoMatcher.group(2))).getMonsterKind(), ((Monster) cardShop.getCard(infoMatcher.group(2))).getMonsterTribe(), ((Monster) cardShop.getCard(infoMatcher.group(2))).isOffenseType(), ((Monster) cardShop.getCard(infoMatcher.group(2))).isNimble(), cardShop.getCard(infoMatcher.group(2)).toString());
                            } else
                                cardShopView.printInfoOfSpellCard(infoMatcher.group(2), cardShop.getCard(infoMatcher.group(2)).getManaPoint(), cardShop.getCard(infoMatcher.group(2)).toString());
                        } catch (Exception e) {
                            System.out.println("The entered name is wrong, make sure and try again.");
                        }
                    }else
                        System.out.println("please enter the instruction as written");
                    break;
                case "Edit":
//                    cardShopView.printEditDeckDetails(User.getCardInventory().getNumberOfCards(), User.getDeck().getCards());
                    inventoryControl.editDeck ();
                    break;
                default:
                    System.out.println ("invalid input");
            }
            request = scanner.nextLine ();
        }
        mainController ();
    }

    private void amuletShopController () {
        amuletShopView.printAmuletShopDetails (amuletShop.getAmulets (), User.getBackPack ().getAmulet (), User.getAmuletInventory().getNumberOfAmulet(), User.getBackPack().isAmuletEquipped());
        Scanner scanner = new Scanner (System.in);
        String request = scanner.nextLine ();
        while (! request.equals ("Exit")) {
            String[] resreq = request.split (" ");
            Matcher tradeMatcher = Pattern.compile ("([^ ]*) (.*) - (\\d*)").matcher (request);
            Matcher infoMatcher = Pattern.compile ("(Info) (.*)").matcher (request);
            switch (resreq[0]) {
                case "Buy":
                    if (tradeMatcher.matches ()) {
                        try {
                            int numberToBuy = Integer.parseInt(tradeMatcher.group(3));
                            amuletShopView.printBuyAmuletDetails(buyAmulet(tradeMatcher.group(2), numberToBuy), numberToBuy, tradeMatcher.group(2));
                        }catch (Exception e){
                            System.out.println("The entered name is wrong, make sure and try again.");
                        }
                    }else
                        System.out.println("wrong format, please follow the help menu thoroughly");
                    break;
                case "Help":
                    amuletShopView.printHelpDetails ();
                    break;
                case "Sell":
                    if (tradeMatcher.matches ()) {
                        try {
                            int numberToSell = Integer.parseInt(tradeMatcher.group(3));
                            amuletShopView.printSellAmuletDetails(sellAmulet(tradeMatcher.group(2), numberToSell), numberToSell, tradeMatcher.group(2));
                        } catch (Exception e) {
                            System.out.println("The entered name is wrong, make sure and try again.");
                        }
                    }else
                        System.out.println("wrong format, please follow the help menu thoroughly");
                    break;
                case "Info":
                    if (tradeMatcher.matches ()) {
                        try {
                            amuletShopView.printInfoOfAnAmulet(tradeMatcher.group(2), amuletShop.getAmulet(tradeMatcher.group(2)).toString());
                        } catch (Exception e) {
                            System.out.println("The entered name is wrong, make sure and try again.");
                        }
                    }else
                        System.out.println("wrong format, please follow the help menu thoroughly");
                    break;
                case "Edit":
//                    amuletShopView.PrintEditAmuletDetails(User.getAmuletInventory().getAmulets(), User.getBackPack().getAmulet(), User.getBackPack().isAmuletEquipped());
                    inventoryControl.editBackPack ();
                    break;
                default:
                    System.out.println ("invalid input");
            }
            request = scanner.nextLine ();
        }
        mainController ();
    }

    private void itemShopController () {
        itemShopView.printItemShopdetails (User.getItemInventory ().getNumberOfItem (), itemShop.getItems ());
        Scanner scanner = new Scanner (System.in);
        String request = scanner.nextLine ();
        while (! request.equals ("Exit")) {
            String[] resreq = request.split (" ");
            Matcher infoMatcher = Pattern.compile ("(Info) (.*)").matcher (request);
            Matcher tradeMatcher = Pattern.compile ("([^ ]*) (.*) - (\\d*)").matcher (request);
            switch (resreq[0]) {
                case "Buy":
                    if (tradeMatcher.matches ()) {
//                        try {
                            int numberToBuy = Integer.parseInt(tradeMatcher.group(3));
                            itemShopView.printBuyItemDetails(buyItem(tradeMatcher.group(2), numberToBuy), numberToBuy, tradeMatcher.group(2));
//                        }catch (Exception e) {
//                            System.out.println("The entered name is wrong, make sure and try again.");
//                        }
                    }else
                        System.out.println("wrong format, please follow the help menu thoroughly");
                    break;
                case "Help":
                    itemShopView.printHelpDetails ();
                    break;
                case "Sell":
                    if (tradeMatcher.matches ()) {
                        try {
                            int numberToSell = Integer.parseInt(tradeMatcher.group(3));
                            itemShopView.printSellItemDetails(sellItem(tradeMatcher.group(2), numberToSell), numberToSell, tradeMatcher.group(2));
                        } catch (Exception e) {
                            System.out.println("The entered name is wrong, make sure and try again.");
                        }
                    }else
                        System.out.println("wrong format, please follow the help menu thoroughly");
                    break;
                case "Info":
                    if (infoMatcher.matches ()) {
                        try {
                            itemShopView.printInfoOfItem(infoMatcher.group(2), User.getItemInventory().getItem(infoMatcher.group(2)).toString());
                        }catch (Exception e){
                            System.out.println("The entered name is wrong, make sure and try again.");
                        }
                    }else
                        System.out.println("wrong format, please follow the help menu thoroughly");
                    break;
                default:
                    System.out.println ("invalid input");
            }
            request = scanner.nextLine ();
        }
        mainController ();
    }

    public void mainController() {
        Scanner scanner = new Scanner(System.in);
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

    private Boolean buyCard(String cardName, int numberToBuy){
        if(User.canBuy(cardShop.getCard(cardName).getGillCost()*numberToBuy) && cardShop.getNumberOfCard(cardName) >= numberToBuy){
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
            User.setGills(User.getGills() + User.getCardInventory().getCard(CardName).getGillCost() * NumberToSell /2);
            for (int counter = 0; counter < NumberToSell; counter++) {
                cardShop.addCard(User.getCardInventory().getCard(CardName));
                User.getCardInventory().remove(CardName);
            }
            return true;
        }
        return false;
    }

    public boolean sellItem(String ItemName,int NumberToSell){
//        if(User.getItemInventory().getNumberOfItem(ItemName) -  User.getBackPack().getNumberOfItems(ItemName) >= NumberToSell){
            User.setGills(User.getGills() + User.getItemInventory().getItem(ItemName).getGillCost() * NumberToSell /2);
            for(int counter=0;counter<NumberToSell;counter++) {
                User.getItemInventory().remove(ItemName);
            }
            return true;
//        }
//        return false;
    }

    public boolean sellAmulet(String amuletName,int numberToSell){
        if (User.getAmuletInventory().hasAmulet(amuletName) && !User.getBackPack().getAmulet().getName().equals(amuletName)) {
            User.getAmuletInventory().remove(amuletName);
            User.setGills(User.getGills() + numberToSell * User.getAmuletInventory().getAmulet(amuletName).getGillCost() / 2);
            return true;
        }
        return false;
    }
}
