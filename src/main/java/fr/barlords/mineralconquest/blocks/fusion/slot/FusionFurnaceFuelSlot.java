package fr.barlords.mineralconquest.blocks.fusion.slot;

import fr.barlords.mineralconquest.blocks.fusion.container.AbstractFusionFurnaceContainer;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.AbstractFurnaceContainer;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

public class FusionFurnaceFuelSlot extends Slot {
    private final AbstractFusionFurnaceContainer menu;

    public FusionFurnaceFuelSlot(AbstractFusionFurnaceContainer abstractFusionFurnaceContainer, IInventory InventoryIn, int index, int xPosition, int yPosition) {
        super(InventoryIn, index, xPosition, yPosition);
        this.menu = abstractFusionFurnaceContainer;
    }

    public boolean mayPlace(ItemStack stack) {
        if(stack.getItem() == Items.BLAZE_ROD){
            return true;
        }
        else { return false; }
    }
}
