package Modules.BattleGround.Fields;

        import Modules.Card.Monsters.Monster;

        import java.util.ArrayList;
        import java.util.HashMap;

public class MonsterField {
    private HashMap<String,Integer> numberOfCards;
    private HashMap<Integer, Monster> slots;//a map of every card and its slot number
    private ArrayList<Monster> monsterCards;
    private int availablePlaces=5;

    public void add(Monster monster){
        if(availablePlaces>=0){
            monsterCards.add(monster);
            numberOfCards.replace(monster.getName(),numberOfCards.get(monster.getName())-1);
            availablePlaces--;
        }
        else
            System.out.println("MonsterField is full");
    }

    public void remove(Monster monster){
        monsterCards.remove(monster);
        availablePlaces++;
        if(numberOfCards.get(monster.getName()) == 1){
            numberOfCards.remove(monster.getName());
        }
        else{
            numberOfCards.replace(monster.getName(),numberOfCards.get(monster.getName())-1);
        }
    }

    public Monster getSlot(int slotNum) {
        return slots.get(slotNum);
    }

    public int getAvailablePlaces() {
        return availablePlaces;
    }

    public ArrayList<Monster> getMonsterCards() {
        return monsterCards;
    }

    public int getNumberOfMonsters(String name){
        return numberOfCards.get(name);
    }

    public int getNumberOfMonsters(Monster monster){
        return numberOfCards.get(monster.getName());
    }
}
