package View.ShopView;

import Modules.ItemAndAmulet.Item;

import java.util.ArrayList;
import java.util.HashMap;

public class ItemShopView {

    /**
     * entrance view
     */
    public void printItemShopdetails(HashMap<String,Integer> numberOfItemsInInventory, ArrayList<Item> availableShopItems){
        int numberOfCards=1;
        //prints shop amulets
        System.out.println("Shop List: ");
        for(Item item:availableShopItems)
            System.out.println(numberOfCards + ". " + item.getName() +  " " + item.getGillCost());
        //prints Inventory amulets
        System.out.println("Item Inventory: ");
        numberOfItemsInInventory.forEach((key,value) -> System.out.println(value + " " + key));
    }

    public void printHelpDetails(){
        System.out.println("1. Buy \"Item Name\" - #NumberToBuy: To buy a certain number of an item from shop");
        System.out.println("2. Sell \"Item Name\" - #NumberToSell: To sell a certain number of an item from inventory");
        System.out.println("3. Info \"Item Name\" To get more information about an item");
        System.out.println("4. Exit: To return to shop menu");
    }

    public void printBuyItemDetails(boolean canBuy,int numberToBuy,String itemName) {
        if (canBuy) {
            System.out.println("Successfully bought " + numberToBuy + " of " + itemName);
        }
        else {
            System.out.println("Not enough Gill!");
        }
    }

    public void printSellItemDetails(boolean canSell,int numberToSell,String itemName){
        if(canSell){
            System.out.println("Successfully sold" + numberToSell + " of " + itemName);
        }
        else{
            System.out.println("Not enough Gill!");
        }
    }

    public void printInfoOfItem(String itemName,String details){
        System.out.println(itemName + " Info:");
        System.out.println(details);
    }
}
