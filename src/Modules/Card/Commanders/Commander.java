package Modules.Card.Commanders;

import Modules.Card.Monsters.Monster;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Graphic.Graphics;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public abstract class Commander extends Monster{
    protected double reductionCoefficiency = 1;

    public void setReductionCoefficiency (double reductionCoefficiency) {
        this.reductionCoefficiency = reductionCoefficiency;
    }

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
        this.HP -= (int)(points * reductionCoefficiency);
        cardViewBig = new CardView(Graphics.SCREEN_WIDTH * 6 / 18, Graphics.SCREEN_HEIGHT * 9 /12, cardImage,this, 0, 0, true);
    }

    public void increaseAP(int points){
        this.AP += points;
    }

    public void decreaseAP(int points){
        this.AP -= points;
    }

    @Override
    public MonsterKind getMonsterKind () {
        return MonsterKind.COMMANDER;
    }

    @Override
    public CardView getCardViewBig () {
        cardImage = new Image(new File("src/Files/Images/Battle/" + name + ".jpg").toURI().toString());
        cardViewBig = new CardView(Graphics.SCREEN_WIDTH * 6 / 18, Graphics.SCREEN_HEIGHT * 9 /12, cardImage,this, 0, 0, true);
        return cardViewBig;
    }
}
