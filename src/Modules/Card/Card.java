package Modules.Card;

import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card implements Cloneable{
    protected String name;
    protected int gillCost;
    protected int manaPoint;
    protected Warrior enemy;
    protected Warrior friend;
    protected Image cardImage;
    protected CardView cardView;
    protected CardView cardViewBig;

    public CardView getCardViewBig() {
        return cardViewBig;
    }

    protected int id;
    protected ImageView cardImage1 = new ImageView(new Image("Files/Images/Battle/cardBack.jpeg"));
    public static int cardNumbers = 0;

    public CardView getCardView() {
        return cardView;
    }

    public Card(){
        id = Card.cardNumbers;

        Card.cardNumbers++;
    }

    public ImageView getCardImage1() {
        return cardImage1;
    }

    public void setCardImage1(ImageView cardImage1) {
        this.cardImage1 = cardImage1;
    }

    public Warrior getEnemy() {
        return enemy;
    }

    public void setEnemy(Warrior enemy) {
        this.enemy = enemy;
    }

    public Warrior getFriend() {
        return friend;
    }

    public void setFriend(Warrior friend) {
        this.friend = friend;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGillCost() {
        return gillCost;
    }

    public void setGillCost(int gillCost) {
        this.gillCost = gillCost;
    }

    public int getManaPoint() {
        return manaPoint;
    }

    public void setManaPoint(int manaPoint) {
        this.manaPoint = manaPoint;
    }

    public String toString(){
        return name;
    }

    public String detail(){
        return "";
    }

    private String spellDetail(){
        return "nothing special";
    }

    private String willDetail(){
        return "nothing special";
    }

    private String battleCryDetail(){
        return "nothing special";
    }

    public int getId () {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object other){
        return ((Card)other).getId() == this.getId();
    }

    @Override
    public int hashCode(){
        return super.hashCode();
    }

    @Override
    public Object clone () throws CloneNotSupportedException {
        return super.clone();
    }

    public Card renew(){
        return this;
    }
}
