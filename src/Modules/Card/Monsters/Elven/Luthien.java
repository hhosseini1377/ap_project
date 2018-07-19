package Modules.Card.Monsters.Elven;

import Modules.Card.Card;
import Modules.Card.Monsters.Hero;
import Modules.Card.Monsters.MonsterKind;
import Modules.Card.Monsters.MonsterTribe;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;
import javafx.scene.image.Image;

public class Luthien extends Hero{
    public Luthien(){
        name = "Luthien, The High Priestess";
        battleCryName = "Revive Allies";
        spellName = "Divine Blessing";
        willName = "Burst of Light";
        battleCryDetail = " move two random cards from your graceyard to hand";
        willDetail = " Increase HP of all friendly monste cards and player by 500\n and increase AP of all friendly monster cards by 200";
        spellDetail = " Increase HP of a friendly monster card or player by 2500";
        HP = 2500;
        AP = 2000;
        initialAP = AP;
        initialHP = HP;
        manaPoint = 9;
        gillCost = 1000 * manaPoint;
        isNimble = false;
        offenseType = true;
        monsterKind = MonsterKind.HERO;
        monsterTribe = MonsterTribe.ELVEN;
        cardImage = new Image("./src/Files/Images/CardImages/"+this.name+".jpg");
        cardView = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.getInstance().getStage().getWidth()/7,Graphics.getInstance().getStage().getHeight()/7,cardImage,this,0,0,true);
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend) {
        if (!canCast){
            System.out.println("this warrior cannot cast a spell right now!");
            return;
        }
        int random = (int)(Math.random() * friend.getMonsterField().getMonsterCards().size());
        friend.getMonsterField().getMonsterCards().get(random).increaseHP(2500);
        canCast = false;
        System.out.println(this.getName() + " has cast a spell:\n" + this.spellDetail());
    }

    @Override
    public void will(Warrior enemy, Warrior friend) {
        friend.getMonsterField().getMonsterCards().forEach(card -> {card.increaseAP(200); card.increaseHP(500);});
        friend.getCommander().increaseHP(500);
        System.out.println(this.getName() + " has cast a spell:\n" + this.willDetail());
    }

    @Override
    public void battleCry(Warrior enemy, Warrior friend) {
        for (int i = 0; i < 2; i++) {
            try {
                int random = (int) (Math.random() * friend.getGraveYard().getDestroyedCards().size());
                Card card = friend.getGraveYard().getDestroyedCards().get(random);
                friend.getGraveYard().remove(card.getName());
                friend.getHand().add(card);
            }catch (Exception e){
                System.out.println("there is not enough cards in grave yard!");
                return;
            }
        }
        System.out.println(this.getName() + " has cast a spell:\n" + this.battleCryDetail());
    }

    @Override
    public void enterField(Warrior enemy, Warrior friend) {
        System.out.println(this.getName() + " has entered the field proudly!");
        battleCry(enemy, friend);
    }

    @Override
    public void die(Warrior enemy, Warrior friend) {
        super.die(enemy, friend);
        will(enemy, friend);
    }
}
