package Control;

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



}
