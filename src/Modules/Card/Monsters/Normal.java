package Modules.Card.Monsters;

import Modules.Graphic.Graphics;
import View.ShopView.CardView;
import javafx.scene.image.Image;

import java.io.File;

public class Normal extends Monster {
    public Normal(String name, int AP, int HP, int manaPoint, boolean isNimble, boolean offenseType,
                  String tribe) {
        super();
        this.name = name;
        this.AP = AP;
        this.initialAP = AP;
        this.initialHP = HP;
        this.HP = HP;
        this.manaPoint = manaPoint;
        this.offenseType = offenseType;
        this.isNimble = isNimble;
        this.monsterKind = MonsterKind.NORMAL;
        if (tribe.equals("ELVEN"))
            this.monsterTribe = MonsterTribe.ELVEN;
        if (tribe.equals("DRAGON_BREED"))
            this.monsterTribe = MonsterTribe.DRAGON_BREED;
        if (tribe.equals("ATLANTIAN"))
            this.monsterTribe = MonsterTribe.ATLANTIAN;
        if (tribe.equals("DEMONIC"))
            this.monsterTribe = MonsterTribe.DEMONIC;
        this.gillCost = manaPoint * 300;
        cardImage = new Image(new File("./src/Files/Images/CardImages/"+name+".jpg").toURI().toString());
        cardView = new CardView(Graphics.SCREEN_WIDTH * 3 / 18,Graphics.SCREEN_HEIGHT * 5 / 12,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.SCREEN_WIDTH * 6 / 18, Graphics.SCREEN_HEIGHT * 9 /12, cardImage,this, 0, 0, true);

    }
}
