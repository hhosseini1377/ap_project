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

public class ShopControl {
    private CardShop cardShop;
    private ItemShop itemShop;
    private AmuletShop amuletShop;
    private AmuletShopView amuletShopView;
    private ItemShopView itemShopView;
    private CardShopView cardShopView;
    private User User;

    public ShopControl(CardShop CardShop,ItemShop ItemShop,AmuletShop AmuletShop,User User){
        this.cardShop = CardShop;
        this.itemShop = ItemShop;
        this.amuletShop = AmuletShop;
        this.User = User;
    }

    private void cardShopController(){
        cardShopView.PrintCardShopDetails(cardShop.getCards(),User.getCardInventory().getNumberOfCards());
        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();
        while(!request.equals("Exit")){
            String[] resreq = request.split(" ");
            if(resreq[0].equals("Buy")){
                int numberToBuy = Integer.parseInt(resreq[2]);
                cardShopView.printBuyCardsDetails(buyCard(resreq[1],numberToBuy),numberToBuy,resreq[1]);
            }
            else if(resreq[0].equals("Help")){
                cardShopView.PrintCardShopHelpDetails();
            }
            else if(resreq[0].equals("Sell")){
                int numberToSell = Integer.parseInt(resreq[2]);
                cardShopView.printSellCardDetails(sellCard(resreq[1],numberToSell),numberToSell,resreq[1]);
            }
            else if(resreq[0].equals("Info")){
                if(cardShop.getCard(resreq[1]) instanceof Monster){
                    cardShopView.printInfoOfMonsterCard(resreq[1],((Monster) cardShop.getCard(resreq[1])).getInitialHP(),((Monster) cardShop.getCard(resreq[1])).getInitialAP(),cardShop.getCard(resreq[1]).getManaPoint(),((Monster) cardShop.getCard(resreq[1])).getMonsterKind(),((Monster) cardShop.getCard(resreq[1])).getMonsterTribe(),((Monster) cardShop.getCard(resreq[1])).isOffenseType(),((Monster) cardShop.getCard(resreq[1])).isNimble(),((Monster) cardShop.getCard(resreq[1])).toString());
                }
                else
                    cardShopView.printInfoOfSpellCard(resreq[1],((Spell) cardShop.getCard(resreq[1])).getManaPoint(),((Spell) cardShop.getCard(resreq[1])).toString());
            }
            else if(resreq[0].equals("Edit")){
                cardShopView.printEditDeckDetails(User.getCardInventory().getNumberOfCards(),User.getDeck().getCards());
                //TODO should be completed when editDeck added
            }
            request=scanner.nextLine();
        }
        mainController();
    }

    private void amuletShopController(){
        amuletShopView.printAmuletShopDetails(amuletShop.getAmulets(),User.getBackPack().getAmulet(),User.getAmuletInventory().getNumberOfAmulet());
        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();
        while(!request.equals("Exit")){
            String[] resreq = request.split(" ");
            if(resreq[0].equals("Buy")){
                int numberToBuy = Integer.parseInt(resreq[2]);
                amuletShopView.printBuyAmuletDetails(buyAmulet(resreq[1],numberToBuy),numberToBuy,resreq[1]);
            }
            else if(resreq[0].equals("Help")){
                amuletShopView.printHelpDetails();
            }
            else if(resreq[0].equals("Sell")){
                int numberToSell = Integer.parseInt(resreq[2]);
                amuletShopView.printSellAmuletDetails(sellAmulet(resreq[1],numberToSell),numberToSell,resreq[1]);
            }
            else if(resreq[0].equals("Info")){
                amuletShopView.printInfoOfAnAmulet(resreq[1],amuletShop.getAmulet(resreq[1]).toString());
            }
            else if(resreq[0].equals("Edit")){
                amuletShopView.PrintEditAmuletDetails(User.getAmuletInventory().getAmulets(),User.getBackPack().getAmulet(),User.getBackPack().isAmuletEquipped());
                //TODO should be completed when edit amulets added
            }
            request=scanner.nextLine();
        }
        mainController();
    }

    private void itemShopController(){
        itemShopView.printItemShopdetails(User.getItemInventory().getNumberOfItem(),itemShop.getItems());
        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();
        while(!request.equals("Exit")){
            String[] resreq = request.split(" ");
            if(resreq[0].equals("Buy")){
                int numberToBuy = Integer.parseInt(resreq[2]);
                itemShopView.printBuyItemDetails(sellItem(resreq[1],numberToBuy),numberToBuy,resreq[1]);
            }
            else if(resreq[0].equals("Help")){
                itemShopView.printHelpDetails();
            }
            else if(resreq[0].equals("Sell")){
                int numberToSell = Integer.parseInt(resreq[2]);
                itemShopView.printSellItemDetails(sellItem(resreq[1],numberToSell),numberToSell,resreq[1]);
            }
            else if(resreq[0].equals("Info")){
                itemShopView.printInfoOfItem(resreq[1],User.getItemInventory().getItem(resreq[1]).toString());
            }
            request=scanner.nextLine();
        }
        mainController();
    }

    public void mainController() {
        Scanner scanner = new Scanner(System.in);
        String request = scanner.nextLine();
        if(request.equals("Help")){
            System.out.println("Remaining Gill:" + User.getGills() + "Gill");
            System.out.println("1. Card Shop");
            System.out.println("2. Item Shop");
            System.out.println("3. Amulet Shop");
            System.out.println("4. Exit");
        }
        if(request.equals("Card Shop")){
            cardShopController();
        }
        else if(request.equals("Amulet Shop")) {
            amuletShopController();
        }
        else if(request.equals("Item Shop")){
            itemShopController();
        }
        else if(request.equals("Exit")){
            //TODO should be completed when edit deck added
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
