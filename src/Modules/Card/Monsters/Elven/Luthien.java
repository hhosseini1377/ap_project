package Modules.Card.Monsters.Elven;

import Modules.Card.Card;
import Modules.Card.Monsters.Hero;
import Modules.Warrior.Warrior;

public class Luthien extends Hero{
    public Luthien(){
        name = "Luthien, The High Priestess";
        battleCryName = "Revive Allies";
        spellName = "Divine Blessing";
        willName = "Burst of Light";
        battleCryDetail = "move two random cards from your graceyard to hand";
        willDetail = "Increase HP of all friendly monste cards and player by 500 and increase AP of all friendly monster cards by 200";
        spellDetail = "Increase HP of a friendly monster card or player by 2500";
        HP = 2500;
        AP = 2000;
        initialAP = AP;
        initialHP = HP;
        manaPoint = 9;
        isNimble = false;
        offenseType = true;
    }

    @Override
    public void castSpell(Warrior enemy, Warrior friend) {
        if (!canCast){
            System.out.println("this warrior cannot cast a spell right now!");
            return;
        }
        int random = (int)(Math.random() * friend.getMonsterField().getMonsterCards().size());
        friend.getMonsterField().getMonsterCards().get(random).increaseHP(2500);
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
}