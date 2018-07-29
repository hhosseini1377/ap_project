package View.Custom;

import Modules.ItemAndAmulet.Item;
import Modules.Warrior.Warrior;

public class CustomItem extends Item {
    int manaChange;
    int friendlyHPChange;

    public CustomItem(int manaChange, int friendlyHPChange, int gilCost, String name) {
        this.manaChange = manaChange;
        this.friendlyHPChange = friendlyHPChange;
        this.gillCost = gilCost;
        this.name = name;
    }

    @Override
    public void castSpell(Warrior player) {
        player.setManaPoint(player.getManaPoint() + manaChange);
        player.getCommander().increaseHP(friendlyHPChange);
    }
}
