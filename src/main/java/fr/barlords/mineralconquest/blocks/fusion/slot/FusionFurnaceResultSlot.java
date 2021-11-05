package fr.barlords.mineralconquest.blocks.fusion.slot;

import fr.barlords.mineralconquest.blocks.fusion.tileentity.AbstractFusionFurnaceTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.container.Slot;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;

public class FusionFurnaceResultSlot extends Slot {
    private final PlayerEntity player;
    private int removeCount;

    public FusionFurnaceResultSlot(PlayerEntity player, IInventory inventoryIn, int slotIndex, int xPosition, int yPosition) {
        super(inventoryIn, slotIndex, xPosition, yPosition);
        this.player = player;
    }

    public boolean mayPlace(ItemStack stack) {
        return false;
    }

    public ItemStack remove(int amount) {
        if (this.hasItem()) {
            this.removeCount += Math.min(amount, this.getItem().getCount());
        }

        return super.remove(amount);
    }

    public ItemStack onTake(PlayerEntity thePlayer, ItemStack stack) {
        this.checkTakeAchievements(stack);
        super.onTake(thePlayer, stack);
        return stack;
    }

    protected void onQuickCraft(ItemStack stack, int amount) {
        this.removeCount += amount;
        this.checkTakeAchievements(stack);
    }

    protected void checkTakeAchievements(ItemStack stack) {
        stack.onCraftedBy(this.player.level, this.player, this.removeCount);
        if (!this.player.level.isClientSide && this.container instanceof AbstractFusionFurnaceTileEntity) {
            ((AbstractFusionFurnaceTileEntity)this.container).awardUsedRecipesAndPopExperience(this.player);
        }

        this.removeCount = 0;
        net.minecraftforge.fml.hooks.BasicEventHooks.firePlayerSmeltedEvent(this.player, stack);
    }
}
