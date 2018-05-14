package Control;

import Modules.BattleGround.Deck;
import Modules.Card.Card;
import Modules.ItemAndAmulet.Amulet;
import Modules.User.Inventory.AmuletInventory;
import Modules.User.Inventory.CardInventory;
import Modules.User.Inventory.ItemInventory;
import Modules.User.User;
import Modules.Warrior.BackPack;

import java.util.Scanner;

public class InventoryControl {
    private User user;

    public InventoryControl(User user){
        this.user = user;
    }

    public void mainThread(){
        Scanner scan = new Scanner(System.in);

        while (true){
            switch (scan.nextLine()){
                case "Exit":
                    return;
                case "Card Inventory":
                    //TODO view inventory
                    break;
                case "Item Inventory":
                    //TODO view inventory
                    break;
                case "Amulet Inventory":
                    //TODO view inventory
                    break;
                case "Help":
                    help();
                    break;
                case "Edit Deck":
                    editDeck();
                    break;
                case "Edit Amulet":
                    editBackPack();
                    break;
            }
        }
    }

    private void help(){
        System.out.println("1." +
                " Card Inventory : To view your cards\n" +
                "2." +
                " Item Inventory : To view your items\n" +
                "3." +
                " Amulet Inventory : To view your amulets\n" +
                "4." +
                " Edit Deck: To edit your card deck\n" +
                "5." +
                " Edit Amulets : To equip or remove your amulets\n" +
                "6." +
                " Exit: To exit to previous menu");
    }

    public void editDeck(){
        int index = 1;
        System.out.println("Your deck:");
        for (Card card:user.getDeck().getCards()) {
            System.out.println(index + ". " + card.getName());
            index++;
        }
        if (index < 30){
            for (int i = index; i <= 30; i++){
                System.out.println(i + ". Empty");
            }
        }
        index = 1;
        System.out.println("Your Inventory:");
        for (Card card: user.getCardInventory().getCards()){
            System.out.println(index + ". " + card.getName() + user.getCardInventory().getNumberOfCards(card) + "/"
            + user.getDeck().getNumberOfCards(card));
            index++;
        }

        Scanner scan = new Scanner(System.in);
        String previousDetail = null;
        String[] action;
        while(true){
            action = scan.nextLine().split(" ");
            switch (action[0]){
                case "Help":
                    previousDetail = "1. Add \"Card Name\" #CardSlotNum : To add cards to your deck\n" +
                            "2. Remove \"Card Name\" #CardSlotNum : To remove cards from your deck\n" +
                            "3. Info \"Card Name\": To get more information about a specific card\n" +
                            "4. Exit: To return to the previous section\n";
                    System.out.println(previousDetail);
                    break;
                case "Exit":
                    return;
                case "Again":
                try {
                    System.out.println(previousDetail);
                }catch (NullPointerException e){
                    System.out.println("no previous action available");
                }
                break;
                case "Add":
                    user.getDeck().add(user.getCardInventory().getCard(action[1]), 1);
                    previousDetail = action[1] + " was added to deck.";
                    System.out.println(previousDetail);
                    break;
                case "Remove":
                    user.getDeck().remove(user.getCardInventory().getCard(action[1]));
                    previousDetail = action[1] + " was removed from deck.";
                    System.out.println(previousDetail);
                    break;
                case "Info":
                    //TODO needs to be completed when there is card info method
                    break;
            }
        }
    }

    public void editBackPack(){
        int index = 1;
        System.out.println("Amulets:");
        for(Amulet amulet:user.getAmuletInventory().getAmulets()){
            System.out.println(index + ". " + amulet.getName());
        }
        if (user.getBackPack().getAmulet() != null)
            System.out.println("Player is equipped with " + user.getBackPack().getAmulet().getName());

        Scanner scan = new Scanner(System.in);
        String previousDetail = null;
        String[] action;
        while(true){
            action = scan.nextLine().split(" ");
            switch (action[0]){
                case "Help":
                    previousDetail = "1. Equip \" Amulet Name\": To equip the player with an amulet\n" +
                            "2. Remove Amulet : To remove the amulet equipped with the player\n" +
                            "3. Info \" Amulet Name\": To get more information about a specific amulet\n" +
                            "4. Exit: To return to the previous section\n";
                    System.out.println(previousDetail);
                    break;
                case "Exit":
                    return;
                case "Again":
                    try {
                        System.out.println(previousDetail);
                    }catch (NullPointerException e){
                        System.out.println("no previous action available");
                    }
                    break;
                case "Equip":
                    user.getBackPack().add(user.getAmuletInventory().getAmulet(action[1]));
                    previousDetail = action[1] + " was equipped.";
                    System.out.println(previousDetail);
                    break;
                case "Remove":
                    user.getBackPack().remove();
                    previousDetail = action[1] + " was disequipped.";
                    System.out.println(previousDetail);
                    break;
                case "Info":
                    //TODO needs to be completed when there is card info method
                    break;
            }
        }
    }
}
