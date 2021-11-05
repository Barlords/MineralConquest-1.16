package fr.barlords.mineralconquest.blocks.fusion.tileentity;

import fr.barlords.mineralconquest.blocks.fusion.container.FusionFurnaceContainer;
import fr.barlords.mineralconquest.init.ModRecipes;
import fr.barlords.mineralconquest.init.ModTileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class FusionFurnaceTileEntity extends AbstractFusionFurnaceTileEntity {
    public FusionFurnaceTileEntity() {
        super(ModTileEntity.FUSION_FURNACE_TILE_ENTITY.get(), ModRecipes.FUSION_FURNACE_RECIPE_TYPE);
    }

    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("block.mineralconquest.fusion_furnace");
    }

    protected Container createMenu(int id, PlayerInventory playerInv) {
        return new FusionFurnaceContainer(id, playerInv, this, this.dataAccess);
    }

    public ItemStack decrStackSize(int index, int count) {
        return ItemStackHelper.removeItem(this.items, index, count);
    }
}