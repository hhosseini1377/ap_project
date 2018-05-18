package Modules.BattleGround.Fields;

import Modules.Card.Card;
import Modules.Card.Spell.Spell;
import Modules.Card.Spell.SpellType;

import java.util.ArrayList;
import java.util.HashMap;

public class SpellField {
    private HashMap<String,Integer> numberOfCards = new HashMap<>();
    private HashMap<Integer, Spell> slots = new HashMap<>();//a map of every card and its slot number
    private ArrayList<Spell> spellCards = new ArrayList<>();
    private int availablePlaces=3;

    public SpellField(){
        for (int i = 0; i < 3; i++)
            slots.put(i, null);
    }

    public boolean hasCard(String name){
        return this.numberOfCards.containsKey(name);
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public Spell getSlot(int slotNum){
        return slots.get(slotNum);
    }

    public ArrayList<Spell> getSpellCards() {
        return spellCards;
    }

//    public int getNumberOfSpells(String spellName){
//        return numberOfCards.get(spellName);
//    }

//    public int getNumberOfSpells(Spell spell){
//        return numberOfCards.get(spell.getName());
//    }

    public Card getCard(String name){
        for (Spell spell:spellCards){
            if (spell.getName ().equals (name))
                return spell;
        }
        return null;
    }

    private void useInstantSpell(Spell spell){
        spell.castSpell();
        this.remove(spell);
    }

    private void useAuraSpell(Spell spell){
        spell.castSpell();
    }

    public boolean add(Spell spell, int slotNum){

        //if slotNum is -1 it doesn't matter which slot is going to be used
        if (slotNum == -1){
            if (availablePlaces <= 0){
                System.out.println("not enough space in the field");
                return false;
            }
            for (int i = 0; i < 5; i++){
                if (slots.get(i) == null){
                    slots.replace(i, spell);
                    spellCards.add(spell);
                    if (numberOfCards.containsKey(spell.getName())) {
                        numberOfCards.replace(spell.getName(), numberOfCards.get(spell.getName()) + 1);
                    }else
                        numberOfCards.put(spell.getName(), 1);
                    availablePlaces--;
                    break;
                }
            }
            System.out.println(spell.getName() + " was used");

            if (spell.getSpellType() == SpellType.INSTANT)
                useInstantSpell(spell);
            if (spell.getSpellType() == SpellType.AURA)
                useAuraSpell(spell);

            return true;
        }

        slotNum--;
        if (slots.get(slotNum) != null){
            System.out.println("the slot is full");
            return false;
        }
        if(availablePlaces>0){
            slots.replace(slotNum, spell);
            spellCards.add(spell);
            if (!numberOfCards.containsKey(spell.getName()))
                numberOfCards.put(spell.getName(), 1);
            else
                numberOfCards.replace(spell.getName(),numberOfCards.get(spell.getName()) + 1);
            availablePlaces--;
        }
        else {
            System.out.println("spellfield is full");
            return false;
        }
        System.out.println(spell.getName() + " was used");

        if (spell.getSpellType() == SpellType.INSTANT)
            useInstantSpell(spell);
        if (spell.getSpellType() == SpellType.AURA)
            useAuraSpell(spell);

        return true;
    }

    public boolean remove(Spell spell){
        try {
            for (int i = 0; i < 3; i++) {
                if (slots.get(i) != null && slots.get(i).equals(spell)) {
                    slots.replace(i, null);
                    break;
                }
            }
            spellCards.remove(spell);
            availablePlaces++;
            if (numberOfCards.get(spell.getName()) == 1) {
                numberOfCards.remove(spell.getName());
            } else {
                numberOfCards.replace(spell.getName(), numberOfCards.get(spell.getName()) - 1);
            }
            //To destroy the effects of spell
            if (spell.getSpellType() == SpellType.AURA)
                spell.reverseSpell();
            System.out.println(spell.getName() + " was destroyed");

            return true;
        }catch (NullPointerException e){
            System.out.println("there is no such spell in spell field");
            return false;
        }
    }

    /**
     * It will use the spells every turn and make the necessary changes
     */
    public void changeTurnActions(){
        for (Spell spell: this.spellCards){
            if (spell.getSpellType() == SpellType.CONTINUOUS)
                spell.castSpell();
        }
    }
}
