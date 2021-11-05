package fr.barlords.mineralconquest.blocks.fusion.container;

import fr.barlords.mineralconquest.blocks.fusion.recipe.AbstractFusionCookingRecipe;
import fr.barlords.mineralconquest.blocks.fusion.slot.FusionFurnaceCatalyserSlot;
import fr.barlords.mineralconquest.blocks.fusion.slot.FusionFurnaceFuelSlot;
import fr.barlords.mineralconquest.blocks.fusion.slot.FusionFurnaceInputSlot;
import fr.barlords.mineralconquest.blocks.fusion.slot.FusionFurnaceResultSlot;
import fr.barlords.mineralconquest.blocks.fusion.tileentity.AbstractFusionFurnaceTileEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.container.*;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.item.crafting.RecipeItemHelper;
import net.minecraft.item.crafting.ServerRecipePlacerFurnace;
import net.minecraft.tileentity.AbstractFurnaceTileEntity;
import net.minecraft.util.IIntArray;
import net.minecraft.util.IntArray;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public abstract class AbstractFusionFurnaceContainer extends RecipeBookContainer<IInventory> {
    private final IInventory container;
    private final IIntArray data;
    protected final World level;
    private final IRecipeType<? extends AbstractFusionCookingRecipe> recipeType;
    private final RecipeBookCategory recipeBookType;

    protected AbstractFusionFurnaceContainer(ContainerType<?> containerType, IRecipeType<? extends AbstractFusionCookingRecipe> recipeType, RecipeBookCategory recipeBook, int id, PlayerInventory playerInventory) {
        this(containerType, recipeType, recipeBook, id, playerInventory, new Inventory(5), new IntArray(4));
    }

    protected AbstractFusionFurnaceContainer(ContainerType<?> containerType, IRecipeType<? extends AbstractFusionCookingRecipe> recipeType, RecipeBookCategory recipeBook, int id, PlayerInventory playerInventoryIn, IInventory furnaceInventoryIn, IIntArray array) {
        super(containerType, id);
        this.recipeType = recipeType;
        this.recipeBookType = recipeBook;
        checkContainerSize(furnaceInventoryIn, 5);
        checkContainerDataCount(array, 4);
        this.container = furnaceInventoryIn;
        this.data = array;
        this.level = playerInventoryIn.player.level;

        this.addSlot(new FusionFurnaceInputSlot(this , furnaceInventoryIn, 0, 33, 35));
        this.addSlot(new FusionFurnaceFuelSlot(this , furnaceInventoryIn, 1, 79, 62));
        this.addSlot(new FusionFurnaceResultSlot(playerInventoryIn.player, furnaceInventoryIn, 2, 79, 34));
        this.addSlot(new FusionFurnaceInputSlot(this , furnaceInventoryIn, 3, 126, 34 ));
        this.addSlot(new FusionFurnaceCatalyserSlot(this , furnaceInventoryIn, 4, 79, 7 ));


        for(int i = 0; i < 3; ++i) {
            for(int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventoryIn, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for(int k = 0; k < 9; ++k) {
            this.addSlot(new Slot(playerInventoryIn, k, 8 + k * 18, 142));
        }

        this.addDataSlots(array);
    }

    public void fillCraftSlotsStackedContents(RecipeItemHelper p_201771_1_) {
        if (this.container instanceof IRecipeHelperPopulator) {
            ((IRecipeHelperPopulator)this.container).fillStackedContents(p_201771_1_);
        }

    }

    public void clearCraftingContent() {
        this.container.clearContent();
    }

    public void handlePlacement(boolean bool, IRecipe<?> recipe, ServerPlayerEntity sPlayerEntity) {
        (new ServerRecipePlacerFurnace<>(this)).recipeClicked(sPlayerEntity, (IRecipe<IInventory>) recipe, bool);
    }

    public boolean recipeMatches(IRecipe<? super IInventory> recipeIn) {
        return recipeIn.matches(this.container, this.level);
    }

    public int getResultSlotIndex() {
        return 2;
    }

    public int getGridWidth() {
        return 1;
    }

    public int getGridHeight() {
        return 1;
    }

    @OnlyIn(Dist.CLIENT)
    public int getSize() {
        return 3;
    }

    public boolean stillValid(PlayerEntity playerIn) {
        return this.container.stillValid(playerIn);
    }

    public ItemStack quickMoveStack(PlayerEntity playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            if (index == 2) {
                if (!this.moveItemStackTo(itemstack1, 3, 39, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onQuickCraft(itemstack1, itemstack);
            } else if (index != 1 && index != 0) {
                if (this.canSmelt(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (this.isFuel(itemstack1)) {
                    if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 3 && index < 30) {
                    if (!this.moveItemStackTo(itemstack1, 30, 39, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 30 && index < 39 && !this.moveItemStackTo(itemstack1, 3, 30, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.moveItemStackTo(itemstack1, 3, 39, false)) {
                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }

    protected boolean canSmelt(ItemStack stack) {
        return this.level.getRecipeManager().getRecipeFor((IRecipeType)this.recipeType, new Inventory(stack), this.level).isPresent();
    }

    public boolean isFuel(ItemStack stack) {
        return AbstractFusionFurnaceTileEntity.isFuel(stack);
    }

    @OnlyIn(Dist.CLIENT)
    public int getBurnProgress() {
        int i = this.data.get(2);
        int j = this.data.get(3);
        return j != 0 && i != 0 ? i * 24 / j : 0;
    }

    @OnlyIn(Dist.CLIENT)
    public int getLitProgress() {
        int i = this.data.get(1);
        if (i == 0) {
            i = 200;
        }

        return this.data.get(0) * 13 / i;
    }

    @OnlyIn(Dist.CLIENT)
    public boolean isLit() {
        return this.data.get(0) > 0;
    }

    @OnlyIn(Dist.CLIENT)
    public RecipeBookCategory getRecipeBookType() {
        return this.recipeBookType;
    }
}