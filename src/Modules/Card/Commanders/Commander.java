package Modules.Card.Commanders;

import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterTribe;

public abstract class Commander extends Monster{
    protected String name;
    protected int HP;
    protected int AP;
    protected int initialHP;
    protected int initialAP;
    protected int manaPoint;
    protected MonsterTribe monsterTribe;
    protected boolean isDead = false;

    public void setHP (int HP) {
        this.HP = HP;
    }

    public void setAP (int AP) {
        this.AP = AP;
    }

    public String getName () {
        return name;
    }

    public int getHP () {
        return HP;
    }

    public int getAP () {
        return AP;
    }

    public int getInitialHP () {
        return initialHP;
    }

    public int getInitialAP () {
        return initialAP;
    }

    public int getManaPoint () {
        return manaPoint;
    }

    public MonsterTribe getMonsterTribe () {
        return monsterTribe;
    }

    public boolean isDead(){
        return (this.HP <= 0);
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
}
