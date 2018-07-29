package Modules.Card.Monsters;

import Modules.Card.Card;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;

import java.util.Scanner;

public abstract class Monster extends Card{
    protected int initialAP;
    protected int initialHP;
    protected boolean isCommander = false;
    protected int AP;
    protected int HP;
    protected boolean offenseType;
    protected boolean isNimble;
    protected MonsterKind monsterKind;
    protected MonsterTribe monsterTribe;
    protected boolean isSleeping = true;
    protected boolean canAttack = false;
    protected boolean canCast = true;


    @Override
    public void reset(){
        canAttack = false;
        isSleeping = true;
        AP = initialAP;
        HP = initialHP;
        canCast = true;
        cardView = new CardView(Graphics.SCREEN_WIDTH * 3 / 18,Graphics.SCREEN_HEIGHT * 5 / 12,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.SCREEN_WIDTH * 6 / 18, Graphics.SCREEN_HEIGHT * 9 /12,cardImage,this,0,0,true);
    }

    public Monster(){
        super();
    }

    public boolean canCast() {
        return canCast;
    }

    public boolean canAttack() {
        return canAttack || isNimble;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public int getInitialAP() {
        return initialAP;
    }

    public void setInitialAP(int initialAP) {
        this.initialAP = initialAP;
    }

    public int getInitialHP() {
        return initialHP;
    }

    public void setInitialHP(int initialHP) {
        this.initialHP = initialHP;
    }

    public int getAP() {
        return AP;
    }

    public void setAP(int AP) {
        this.AP = AP;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public boolean isOffenseType() {
        return offenseType;
    }

    public void setOffenseType(boolean offenseType) {
        this.offenseType = offenseType;
    }

    public boolean isNimble() {
        return isNimble;
    }

    public void setNimble(boolean nimble) {
        isNimble = nimble;
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }

    public MonsterKind getMonsterKind() {
        return monsterKind;
    }

    public void setMonsterKind(MonsterKind monsterKind) {
        this.monsterKind = monsterKind;
    }

    public MonsterTribe getMonsterTribe() {
        return monsterTribe;
    }

    public void increaseHP(int points){
        this.HP += points;
        friend.getMonsterField().update(this);
    }

    public void decreaseHP(int points){
        this.HP -= points;
        friend.getMonsterField().update(this);
        if (isDead()){
            if (!isCommander) {
                friend.getMonsterField().remove(this);
                friend.getGraveYard().add(this);
                die(enemy, friend);
            }
        }
    }

    public void increaseAP(int points){
        this.AP += points;
        friend.getMonsterField().update(this);
    }

    public void decreaseAP(int points){
        this.AP -= points;
        friend.getMonsterField().update(this);
    }

    public boolean isDead(){
        return (this.HP <= 0);
    }

    public void enterField(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has entered the field proudly!");
    }

    public void die(Warrior enemy, Warrior friend) {
        AP = initialAP;
        HP = initialHP;
        isSleeping = true;
        canCast = true;
        System.out.println(this.getName() + " has been murdered with cruelty!");
    }

    public void castSpell(Warrior enemy, Warrior friend){

    }

    public String toString(){
        return "Name: " + name + "\nHP: " + HP + "\nAP: " + AP +
                "\nMP cost: " + manaPoint +
                "\nIs Nimble: " + isNimble + "\nIs Defensive: " + !offenseType +
                "\nMonster Kind: " + monsterKind + "\nTribe: " + monsterTribe;
    }
}
