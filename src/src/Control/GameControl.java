package Control;

import Modules.Card.Card;
import Modules.ItemAndAmulet.Amulet;
import Modules.ItemAndAmulet.Item;
import Modules.User.User;

import java.util.ArrayList;

public class GameControl {
    private String fileDirectory;
    private ArrayList<Card> cards;
    private ArrayList<Item> items;
    private ArrayList<Amulet> amulets;
    private User user;

    public GameControl(String fileDirectory){
        this.fileDirectory = fileDirectory;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public ArrayList<Item> getItems() {
        return items;
    }

    public ArrayList<Amulet> getAmulets() {
        return amulets;
    }

    public User getUser() {
        return user;
    }

    public void startGame(){

    }

    public void help(){

    }

    public void saveGame(){

    }

    public void endGame(){

    }
}
