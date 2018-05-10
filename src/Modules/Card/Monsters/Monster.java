package Modules.Card.Monsters;

import Modules.Card.Card;

public abstract class Monster extends Card{
    protected int initialAP;
    protected int initialHP;
    protected int AP;
    protected int HP;
    protected boolean offenseType;
    protected boolean isNimble;
    protected MonsterKind monsterKind;
    protected MonsterTribe monsterTribe;
    protected boolean isSleeping = true;
    protected boolean canAttack = true;

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

    public void increaseHP(int points){
        this.HP += points;
    }

    public void decreaseHP(int points){
        this.HP -= points;
    }

    public void increaseAP(int points){
        this.AP += points;
    }

    public void decreaseAP(int points){
        this.AP -= points;
    }

    public void attack(Monster other){
        if (!this.canAttack()){
            System.out.println("this monster cannot attack the opponent!!");
            return;
        }
        other.decreaseHP(this.AP);
        this.decreaseHP(other.getAP());
    }

    public boolean isDead(){
        return (this.HP <= 0);
    }

    public String toString(){
        return name + " " + AP + " " + HP
                + " " + manaPoint + " "
                + isNimble + " " + offenseType
                + " " + monsterKind + " " + monsterTribe;
    }
}
