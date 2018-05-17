package Modules.Card.Monsters;

import Modules.Card.Card;
import Modules.Warrior.Warrior;

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
    protected boolean canAttack = true;
    protected boolean canCast;

    public boolean canCast() {
        return canCast;
    }

    public boolean canAttack() {
        return canAttack;
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
    }

    public void decreaseHP(int points){
        this.HP -= points;
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
    }

    public void decreaseAP(int points){
        this.AP -= points;
    }

    public void attack(Warrior enemy, Scanner scan){
        if (!this.canAttack()){
            System.out.println("this monster cannot attack the opponent!!");
            return;
        }
        Monster targeted;
        //checks if there are any defensive cards in the enemy field and if there was the defender will stop the combatant
        if (enemy.getMonsterField().containDefensiveCard()){
            targeted = enemy.getMonsterField().getDefensiveCard();
            this.attack(targeted);
            return;
        }
        String target = scan.next();
        if ((target.equals("Commander"))) {
            targeted = enemy.getCommander();
            this.attack(enemy.getCommander());
        }
        else{
            targeted = enemy.getMonsterField().getSlot(Integer.parseInt(target));
            this.attack(targeted);
        }
        System.out.println(this.getName() + " clashed with " + targeted.getName());
    }

    private void attack(Monster other){
        other.decreaseHP(this.AP);
        this.decreaseHP(other.getAP());
    }

    public boolean isDead(){
        return (this.HP <= 0);
    }

    public void enterField(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has entered the field proudly!");
    }

    public void die(Warrior enemy, Warrior friend) {
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
