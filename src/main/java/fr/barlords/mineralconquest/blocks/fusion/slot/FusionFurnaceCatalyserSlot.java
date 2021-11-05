package fr.barlords.mineralconquest.blocks.fusion.slot;

import fr.barlords.mineralconquest.blocks.fusion.container.AbstractFusionFurnaceContainer;
import fr.barlords.mineralconquest.init.ModItems;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class FusionFurnaceCatalyserSlot extends Slot {
    private final AbstractFusionFurnaceContainer menu;

    public FusionFurnaceCatalyserSlot(AbstractFusionFurnaceContainer abstractFusionFurnaceContainer, IInventory InventoryIn, int index, int xPosition, int yPosition) {
        super(InventoryIn, index, xPosition, yPosition);
        this.menu = abstractFusionFurnaceContainer;
    }

    public boolean mayPlace(ItemStack stack) {
        if(stack.getItem() == Items.GHAST_TEAR){
            return true;
        }
        else { return false; }
    }
}
