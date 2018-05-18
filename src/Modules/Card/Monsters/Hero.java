package Modules.Card.Monsters;

import Modules.Card.Card;
import Modules.Warrior.Warrior;

public abstract class Hero extends Monster {
    protected boolean canCast = true;
    protected String spellName;
    protected String willName;
    protected String battleCryName;
    protected String spellDetail;
    protected String willDetail;
    protected String battleCryDetail;

    public boolean CanCast() {
        return canCast;
    }

    public void setCanCast(boolean canCast) {
        this.canCast = canCast;
    }

    public void castSpell(Warrior enemy, Warrior friend){
    }

    public abstract void will(Warrior enemy, Warrior friend);

    public abstract void battleCry(Warrior enemy, Warrior friend);

    public void battleCry(Card card){

    }

    public String spellDetail(){
        return this.spellDetail;
    }

    public String willDetail(){
        return this.willDetail;
    }

    public String battleCryDetail(){
        return this.battleCryDetail;
    }

    public String getBattleCryName() {
        return battleCryName;
    }

    public String getWillName() {
        return willName;
    }

    public String getSpellName() {
        return spellName;
    }

    @Override
    public String toString() {
        return "Name: " + name + "\nHP: " + HP + "\nAP: " + AP +
                "\nMP cost: " + manaPoint +
                "\nIs Nimble: " + isNimble + "\nIs Defensive: " + !offenseType +
                "\nMonster Kind: " + monsterKind + "\nTribe: " + monsterTribe +
                "\nSpell detail: " + spellName + ", " + spellDetail +
                "\nWill detail: " + willName + ", " + willDetail() +
                "\nBattle cry detail: " + battleCryName + ", " + battleCryDetail();
    }
}
