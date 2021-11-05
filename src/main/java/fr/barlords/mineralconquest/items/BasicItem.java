package fr.barlords.mineralconquest.items;

import fr.barlords.mineralconquest.MineralConquest;
import fr.barlords.mineralconquest.init.ModItemGroups;
import net.minecraft.item.Item;

public class BasicItem extends Item {

    public BasicItem(Properties properties) {
        super(properties
                .tab(ModItemGroups.TAB_MOD)
        );
    }
}
