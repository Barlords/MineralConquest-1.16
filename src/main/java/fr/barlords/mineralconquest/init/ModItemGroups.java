package fr.barlords.mineralconquest.init;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class ModItemGroups {

    public static final ItemGroup TAB_MOD = new ItemGroup("tab_mineralconquest") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.PURIUM_INGOT.get());
        }
    };

}
