package Modules.BattleGround.Fields;

import Modules.Card.Monsters.Monster;
import Modules.Card.Spell.Spell;

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

    public void add(Spell spell, int slotNum){
        if (slotNum == -1){
            if (availablePlaces <= 0){
                System.out.println("not enough space in the field");
            }
            for (int i = 0; i < 5; i++){
                if (slots.get(slotNum) == null){
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
            return;
        }
        if (slots.get(slotNum) != null){
            System.out.println("the slot is full");
            return;
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
        else
            System.out.println("spellfield is full");
    }

    public void remove(Spell spell){
        for (int i = 0; i < 3; i++) {
            if (slots.get(i).equals(spell)){
                slots.replace(i, null);
                break;
            }
        }
        spellCards.remove(spell);
        availablePlaces++;
        if(numberOfCards.get(spell.getName()) == 1){
            numberOfCards.remove(spell.getName());
        }
        else{
            numberOfCards.replace(spell.getName(),numberOfCards.get(spell.getName()) - 1);
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
