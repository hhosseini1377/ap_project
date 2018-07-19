package Control;

import Modules.Card.Monsters.Monster;
import Modules.Shop.AmuletShop;
import Modules.Shop.CardShop;
import Modules.Shop.ItemShop;
import Modules.User.User;
import View.ShopView.AmuletShopView;
import View.ShopView.CardShopView;
import View.ShopView.ItemShopView;
import View.ShopView.ShopView;

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
    private ShopView shopView;
    private User User;

    public Modules.User.User getUser() {
        return User;

    }

    private InventoryControl inventoryControl;

    public ShopControl (CardShop CardShop, ItemShop ItemShop, AmuletShop AmuletShop, User User, InventoryControl inventoryControl) {
        this.inventoryControl = inventoryControl;
        this.cardShop = CardShop;
        this.itemShop = ItemShop;
        this.amuletShop = AmuletShop;
        this.User = User;
        cardShopView = new CardShopView (this);
        itemShopView = new ItemShopView (this);
        amuletShopView = new AmuletShopView ();
        shopView = new ShopView(this,cardShopView,itemShopView);
    }

    public void enterShop(){
        shopView.enterShop();
    }



    public void buyCard(String cardName, int numberToBuy){
            for(int counter =0;counter<numberToBuy;counter++) {
                User.buy(cardShop.getCard(cardName).getGillCost());
                User.getCardInventory().add(cardShop.getCard(cardName));
                cardShop.removeCard(cardName);
            }
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
        if (User.getAmuletInventory().hasAmulet(amuletName) && !User.getBackPack().ContainsAmulet(amuletName)) {
            User.setGills(User.getGills() + numberToSell * User.getAmuletInventory().getAmulet(amuletName).getGillCost() / 2);
            User.getAmuletInventory().remove(amuletName);
            return true;
        }
        return false;
    }

    public CardShop getCardShop() {
        return cardShop;
    }

    public ItemShop getItemShop() {
        return itemShop;
    }

    public AmuletShop getAmuletShop() {
        return amuletShop;
    }
}
