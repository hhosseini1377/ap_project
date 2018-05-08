package Modules.BattleGround.Fields;

import Modules.Card.Monsters.Monster;
import Modules.Card.Spell.Spell;

import java.util.ArrayList;
import java.util.HashMap;

public class SpellField {
    private HashMap<String,Integer> numberOfCards;
    private HashMap<Integer, Spell> slots;//a map of every card and its slot number
    private ArrayList<Spell> spellCards;
    private int availablePlaces=3;

    public void add(Spell spell){
        if(availablePlaces>=0){
            spellCards.add(spell);
            numberOfCards.replace(spell.getName(),numberOfCards.get(spell.getName())-1);
            availablePlaces--;
        }
        else
            System.out.println("spellfield is full");
    }

    public void remove(Spell spell){
        spellCards.remove(spell);
        availablePlaces++;
        if(numberOfCards.get(spell.getName()) ==1){
            numberOfCards.remove(spell);
        }
        else{
            numberOfCards.replace(spell.getName(),numberOfCards.get(spell)-1);
        }
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

    public int getNumberOfSpells(String spellName){
        return numberOfCards.get(spellName);
    }

    public int getNumberOfSpells(Spell spell){
        return numberOfCards.get(spell.getName());
    }
}
