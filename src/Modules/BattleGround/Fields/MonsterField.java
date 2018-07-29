package Modules.BattleGround.Fields;

        import Modules.Card.Card;
        import Modules.Card.Monsters.Monster;
        import View.BattleGroundView.MonsterFieldView;
        import javafx.scene.Node;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.HBox;

        import java.io.Serializable;
        import java.util.ArrayList;
        import java.util.HashMap;

public class MonsterField implements Serializable{
    private boolean isEnemy = false;
    private transient MonsterFieldView monsterFieldView = new MonsterFieldView();
    private HashMap<String,Integer> numberOfCards = new HashMap<>();
    private HashMap<Integer, Monster> slots = new HashMap<>();//a map of every card and its slot number
    private ArrayList<Monster> monsterCards = new ArrayList<>();
    private ArrayList<Monster> defensiveCards = new ArrayList<>();
    private int availablePlaces=5;

    public void startViews(){
        monsterFieldView = new MonsterFieldView();
    }

    public MonsterField(){
        for (int i = 0; i < 5; i++)
            slots.put(i, null);
    }

    public Monster getSlot(int slotNum) {
        slotNum--;
        Monster monster = null;
        try {
             monster = slots.get(slotNum);
        }catch (NullPointerException e){
            System.out.println("this slot is empty");
        }
        return monster;
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

    public boolean add(Monster monster, int slotNum){
        if (slotNum > 5){
            System.out.println("This slot is invalid, try another one");
            return false;
        }
        slotNum--;
        if (slotNum == -2){
            if (availablePlaces <= 0){
                System.out.println("not enough space in the field");
                return false;
            }
            for (int i = 0; i < 5; i++){
                if (slots.get(i) == null){
                    monsterCards.add(monster);
                    if (numberOfCards.containsKey(monster.getName()))
                        numberOfCards.replace(monster.getName(),numberOfCards.get(monster.getName()) + 1);
                    else
                        numberOfCards.put(monster.getName(), 1);
                    availablePlaces--;
                    slots.replace(i, monster);
                    monsterFieldView.addToField(monster, i);
                    if (!monster.isOffenseType())
                        defensiveCards.add(monster);
                    break;
                }
            }
            return true;
        }
        if (slots.get(slotNum) != null){
            System.out.println("the slot is full!!");
            return false;
        }
        if(availablePlaces>0){
            monsterCards.add(monster);
            if (numberOfCards.containsKey(monster.getName()))
                numberOfCards.replace(monster.getName(),numberOfCards.get(monster.getName()) + 1);
            else
                numberOfCards.put(monster.getName(), 1);
            monsterFieldView.addToField(monster, slotNum);
            availablePlaces--;
            slots.replace(slotNum, monster);
            if (!monster.isOffenseType())
                defensiveCards.add(monster);
        }
        else
            System.out.println("MonsterField is full");
        return true;
    }

    public Card getCard(String name){
        for (Monster monster:monsterCards){
            if (monster.getName ().equals (name))
                return monster;
        }
        return null;
    }

    public void remove(Monster monster){
        for (int i = 0; i < 5; i++) {
            try{
            if (slots.get(i) != null && slots.get(i).equals(monster)) {
                slots.replace(i, null);
                monsterFieldView.removeFromField(monster, i);
                break;
            }
            }catch (NullPointerException e){
                System.out.println(e);
            }
        }
        monsterCards.remove(monster);
        if (defensiveCards.contains(monster)) {
            defensiveCards.remove(monster);
        }
        availablePlaces++;
        if(numberOfCards.get(monster.getName()) == 1){
            numberOfCards.remove(monster.getName());
        }
        else{
            numberOfCards.replace(monster.getName(),numberOfCards.get(monster.getName()) - 1);
        }
    }

    public boolean containDefensiveCard(){
        return (defensiveCards.size() > 0);
    }

    public Monster getDefensiveCard(){
        Monster strongestDefender = defensiveCards.get(0);
        for (Monster monster:defensiveCards){
            if (strongestDefender.getHP() < monster.getHP())
                strongestDefender = monster;
        }
        return strongestDefender;
    }

    public void changeTurnActions(Boolean isMyTurn){
        if (isMyTurn)
            for (Monster monster: getMonsterCards()){
                if (monster.isSleeping()){
                    monster.setSleeping(false);
                    monster.setCanAttack(true);
                }
            }
        else
            for (Monster monster: defensiveCards){
                if (monster.isSleeping()){
                    monster.setSleeping(false);
                }
            }
    }

    public MonsterFieldView getMonsterFieldView () {
        return monsterFieldView;
    }

    public void setMonsterFieldView (MonsterFieldView monsterFieldView) {
        this.monsterFieldView = monsterFieldView;
    }

    public void update(Card card){
        for (int i = 0; i < 5; i++) {
            try{
                if (slots.get(i) != null && slots.get(i).equals(card)) {
                    monsterFieldView.update(card, i);
                    break;
                }
            }catch (NullPointerException e){
                System.out.println(e);
            }
        }
    }

    public void setFieldView(HBox view){
        HBox[] monsters = new HBox[5];
        int index = 0;
        for (Node hBox:view.getChildren()){
            monsters[index] = ((HBox) hBox);
            index++;
        }
        monsterFieldView.setFieldView(monsters);
    }

    public boolean isEnemy () {
        return isEnemy;
    }

    public void setEnemy (boolean enemy) {
        isEnemy = enemy;
    }

    public boolean hasCard(String name){
        return this.numberOfCards.containsKey(name);
    }
}
