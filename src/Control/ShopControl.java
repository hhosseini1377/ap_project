package Control;

import Modules.Shop.AmuletShop;
import Modules.Shop.CardShop;
import Modules.Shop.ItemShop;
import Modules.User.User;
import View.ShopView.AmuletShopView;
import View.ShopView.CardShopView;
import View.ShopView.ItemShopView;

import java.util.Scanner;

public class ShopControl {
    private CardShop CardShop;
    private ItemShop ItemShop;
    private AmuletShop AmuletShop;
    private AmuletShopView AmuletShopView;
    private ItemShopView ItemShopView;
    private CardShopView CardShopView;
    private User User;

    public ShopControl(CardShop CardShop,ItemShop ItemShop,AmuletShop AmuletShop,User User){
        this.CardShop = CardShop;
        this.ItemShop = ItemShop;
        this.AmuletShop = AmuletShop;
        this.User = User;
    }

    public void mainController() {
        String request;
        Scanner scan = new Scanner(System.in);
        while (true) {
            request = scan.next();
            String[] resreq = request.split(" ");
            if (resreq[0].equals("Help")) {
                CardShopView.PrintCardShopHelpDetails();
            }
            if (resreq[0].equals("Buy")) {
                int numberToBuy = Integer.parseInt(resreq[2]);
                if (buyCard(resreq[1], numberToBuy)) {
                    //TODO

                } else
                    CardShopView.printBuyCardsDetails(false, numberToBuy, resreq[1]);
            }
            if (resreq[0].equals("Sell")) {
                int numberToSell = Integer.parseInt(resreq[2]);
                if (sellCard(resreq[1], numberToSell))
                    CardShopView.printSellCardDetails(true, numberToSell, resreq[1]);
                else
                    CardShopView.printSellCardDetails(false, numberToSell, resreq[1]);
            }

            if (resreq[0].equals("Info")) {
                //TODO

            }
        }
    }

   private Boolean buyCard(String cardName,int numberToBuy){
        if(User.canBuy(CardShop.getCard(cardName).getGillCost()*numberToBuy)){
            for(int counter =0;counter<numberToBuy;counter++) {
                User.buy(CardShop.getCard(cardName).getGillCost());
                User.getCardInventory().add(CardShop.getCard(cardName));
                CardShop.removeCard(cardName);
            }
            return true;
        }
        else
            return false;
    }

    public Boolean buyItem(String itemName,int numberToBuy){
        if(User.canBuy(ItemShop.getItem(itemName).getGillCost()*numberToBuy)){
            for(int counter =0;counter<numberToBuy;counter++) {
                User.buy(ItemShop.getItem(itemName).getGillCost());
                User.getItemInventory().add(ItemShop.getItem(itemName));
//                ItemShop.RemoveItem(itemName);
            }
            return true;
        }
        else
            return false;
    }

    public boolean buyAmulet(String amuletName,int numberToBuy){
        if(User.canBuy(AmuletShop.getAmulet(amuletName).getGillCost()*numberToBuy)) {
            for (int counter = 0; counter < numberToBuy; counter++) {
                User.buy(AmuletShop.getAmulet(amuletName).getGillCost());
                User.getAmuletInventory().add(AmuletShop.getAmulet(amuletName));
                AmuletShop.removeAmulet(amuletName);
            }
            return true;
        }
        else
            return false;
    }

    private boolean sellCard(String CardName,int NumberToBuy) {
        if (User.getCardInventory().getNumberOfCards(CardName) - User.getDeck().getNumberOfCards(CardName) >= NumberToBuy) {
            for (int counter = 0; counter < NumberToBuy; counter++) {
                User.getCardInventory().remove(CardName);
            }
            return true;
        }
        return false;
    }

    public boolean sellItem(String ItemName,int NumberToBuy){
        if(User.getItemInventory().getNumberOfItem(ItemName) -  User.getBackPack().getNumberOfItems(ItemName) >= NumberToBuy){
            for(int counter=0;counter<NumberToBuy;counter++)
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
