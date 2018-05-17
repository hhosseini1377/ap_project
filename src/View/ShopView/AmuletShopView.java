package View.ShopView;

import Modules.ItemAndAmulet.Amulet;

import java.util.ArrayList;
import java.util.HashMap;

public class AmuletShopView {
    /**
     * entrance print
     */
    public void printAmuletShopDetails(ArrayList<Amulet> availableShopAmulets,Amulet equipedAmulet){
        System.out.println("Shop List: ");
        int numberOfCards=1;
        //prints Amulets available in shop and their costs
        for(Amulet amulet:availableShopAmulets){
            System.out.println(numberOfCards++ + ". " + amulet.getName() + " " + amulet.getGillCost());
        }
        //prints equipped Amulet
        System.out.println("Equipped Amulet" + equipedAmulet.getName());
        //prints Inventory Amulets and number of each Amulet
    }
    public void printHelpDetails(){
        System.out.println("1. Buy \"Amulet Name\" - #NumberToBuy: To buy a certain number of an Amulet from shop");
        System.out.println("2. Sell \"Amulet Name\" - #NumberToSell: To sell a certain number of an Amulet from inventory");
        System.out.println("3. Info \"Amulet Name\" To get more information about an Amulet");
        System.out.println("4. Edit Amulets: To equip or remove your hero's amulet");
        System.out.println("5. Exit: To return to shop menu");
    }


    public void printBuyAmuletDetails(boolean canBuy,int numberToBuy,String amuletName) {
        if (canBuy) {
            System.out.println("Successfully bought " + numberToBuy + " of " + amuletName);
        }
        else {
            System.out.println("Not enough Gill!");
        }
    }

    public void printSellAmuletDetails(boolean canSell,int numberToSell,String amuletName){
        if(canSell){
            System.out.println("Successfully sold" + numberToSell + " of " + amuletName);
        }
        else{
            System.out.println("Not enough Gill!");
        }
    }

    public void printInfoOfAnAmulet(String amuletName,String detail){
        System.out.println(amuletName + " Info:");
        System.out.println(detail);
    }

    public void PrintEditAmuletDetails(ArrayList<Amulet> inventoryAmulets,Amulet equippedAmulet,boolean isAmuletEquipped){
        int numberOfAmulet=1;
        System.out.println("Amulets:");
        //prints Inventory amulets
        for(Amulet amulet:inventoryAmulets){
            System.out.println(numberOfAmulet++ + ". " + amulet.getName());
        }
        //prints if Player eqiuipped an Amulet
        if(isAmuletEquipped)
            System.out.println("Player is equiped with: " + equippedAmulet.getName());
    }


}
