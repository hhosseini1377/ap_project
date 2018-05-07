package Control;

import Modules.Card.Card;
import Modules.Shop.AmuletShop;
import Modules.Shop.CardShop;
import Modules.Shop.ItemShop;
import Modules.User.User;
import View.ShopView.AmuletShopView;
import View.ShopView.CardShopView;
import View.ShopView.ItemShopView;

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

    public void mainController(String request){
        String[] resreq = request.split(" ");
        if(resreq[0].equals("Help")) {
            CardShopView.PrintCardShopHelpDetails();
        }
        if(resreq[0].equals("Buy")){
            int numberToBuy = Integer.parseInt(resreq[2]);
            if(buyCard(resreq[1],numberToBuy)) {
            }
            else
                CardShopView.printbuyCardsDetails(false,numberToBuy,resreq[1]);
        }
        if(resreq[0].equals("Sell ")){
            int numberToSell = Integer.parseInt(resreq[2]);
            if(SellCard(resreq[1],numberToSell))
                CardShopView.printSellCardDetails(true,numberToSell,resreq[1]);
            else
                CardShopView.printSellCardDetails(false,numberToSell,resreq[1]);
        }

        if(resreq[0].equals("Info")){


        }
    }


    public Boolean buyCard(String CardName,int NumberToBuy){
        if(User.canBuy(CardShop.GetGillCost(CardName)*NumberToBuy)){
            for(int counter =0;counter<NumberToBuy;counter++) {
                User.buy(CardShop.GetGillCost(CardName));
                User.getCardInventory().add(CardShop.GetCard(CardName));
                CardShop.RemoveCard(CardName);
            }
            return true;
        }
        else
            return false;
    }

    public Boolean buyItem(String ItemName,int NumberToBuy){
        if(User.canBuy(ItemShop.GetgillCost(ItemName)*NumberToBuy)){
            for(int counter =0;counter<NumberToBuy;counter++) {
                User.buy(ItemShop.GetgillCost(ItemName));
                User.getItemInventory().add(ItemShop.GetItem(ItemName));
                ItemShop.RemoveItem(ItemName);
            }
            return true;
        }
        else
            return false;
    }

    public boolean buyAmulet(String AmuletName,int NumberToBuy){
        if(User.canBuy(AmuletShop.GetGillCost(AmuletName)*NumberToBuy)) {
            for (int counter = 0; counter < NumberToBuy; counter++) {
                User.buy(AmuletShop.GetGillCost(AmuletName));
                User.getAmuletInventory().add(AmuletShop.getAmulet(AmuletName));
                AmuletShop.RemoveAmulet(AmuletName);
            }
            return true;
        }
        else
            return false;
    }

    public boolean SellCard(String CardName,int NumberToBuy) {
        if (User.getCardInventory().getNumberOfCards(CardName) - User.getDeck().getNumberOfCards(CardName) >= NumberToBuy) {
            for (int counter = 0; counter < NumberToBuy; counter++) {
                User.getCardInventory().remove(CardName);
            }
            return true;
        }
        return false;
    }

    public boolean Sellitem(String ItemName,int NumberToBuy){
        if(User.getItemInventory().getNumberOfItem(ItemName) -  User.getBackPack().getNumberOfItems(ItemName) >= NumberToBuy){
            for(int counter=0;counter<NumberToBuy;counter++)
                User.getItemInventory().remove(ItemName);
            return true;
        }
        return false;
    }

    public boolean SellAmulet(String amuletName,int numberToSell){
        if(User.getBackPack().getAmulet().getName().equals(amuletName)) {
            if (User.getAmuletInventory().getNumberOfAmulets(amuletName) - 1 >= numberToSell) {
                for (int counter = 0; counter < numberToSell; counter++)
                    User.getAmuletInventory().remove(amuletName);
                return true;
            }
            else{
                return false;
            }
        }
        else{
            if(User.getAmuletInventory().getNumberOfAmulets(amuletName) >= numberToSell){
                for(int counter=0;counter<numberToSell;counter++)
                    User.getAmuletInventory().remove(amuletName);
                return true;
            }
            else{
                return false;
            }
        }
    }
}
