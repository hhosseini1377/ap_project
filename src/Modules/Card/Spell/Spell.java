package Modules.Card.Spell;

import Modules.Card.Card;
import Modules.Graphic.Graphics;
import Modules.Warrior.Warrior;
import View.ShopView.CardView;

public class Spell extends Card {
    protected boolean canCast = true;
    protected SpellType spellType;
    protected String spellDetail;

    @Override
    public void reset(){
        canCast = true;
        cardView = new CardView(Graphics.SCREEN_WIDTH * 3 / 18,Graphics.SCREEN_HEIGHT * 5 / 12,cardImage,this,0,0,false);
        cardViewBig = new CardView(Graphics.SCREEN_WIDTH * 6 / 18, Graphics.SCREEN_HEIGHT * 9 /12,cardImage,this,0,0,true);
    }

    public boolean isCanCast () {
        return canCast;
    }

    public SpellType getSpellType () {
        return spellType;
    }

    public String spellDetail(){
        return spellDetail;
    }

    public void castSpell(){
    }

    public void castSpell(Card card){
    }

    public void reverseSpell(){

    }

    @Override
    public String detail () {
        return "Name: " + this.name + "\nMP cost: " + this.manaPoint +
                "\nCard Type: " + this.spellType;
    }

    public String toString(){
        return "Name: " + this.name + "\nMP cost: " + this.manaPoint +
                "\nCard Type: " + this.spellType + "\nDetails: " + spellDetail;
    }
}
