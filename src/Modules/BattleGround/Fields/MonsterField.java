package Modules.BattleGround.Fields;

        import Modules.Card.Monsters.Monster;

        import java.util.ArrayList;
        import java.util.HashMap;

public class MonsterField {
    private HashMap<String,Integer> numberOfCards;
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
        if(numberOfCards.get(monster.getName()) ==1){
            numberOfCards.remove(monster);
        }
        else{
            numberOfCards.replace(monster.getName(),numberOfCards.get(monster)-1);
        }
    }

    public ArrayList<Monster> getMonsterCards() {
        return monsterCards;
    }

    public int getNumberOfMonsters(String itemName){
        return numberOfCards.get(itemName);
    }

    public int getNumberOfMonsters(Monster monster){
        return numberOfCards.get(monster.getName());
    }
}
