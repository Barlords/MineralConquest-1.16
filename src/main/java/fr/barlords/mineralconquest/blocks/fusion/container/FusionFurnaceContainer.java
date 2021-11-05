package fr.barlords.mineralconquest.blocks.fusion.container;

import fr.barlords.mineralconquest.init.ModContainers;
import fr.barlords.mineralconquest.init.ModRecipes;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.crafting.RecipeBookCategory;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.IIntArray;

public class FusionFurnaceContainer extends AbstractFusionFurnaceContainer {
    public FusionFurnaceContainer(int id, PlayerInventory playerInv, PacketBuffer packetBuffer) {
        super(ModContainers.FUSION_FURNACE_CONTAINER.get(), ModRecipes.FUSION_FURNACE_RECIPE_TYPE, RecipeBookCategory.FURNACE, id, playerInv);
    }

    public FusionFurnaceContainer(int id, PlayerInventory playerInv, IInventory iInventory, IIntArray array) {
        super(ModContainers.FUSION_FURNACE_CONTAINER.get(), ModRecipes.FUSION_FURNACE_RECIPE_TYPE, RecipeBookCategory.FURNACE, id, playerInv, iInventory, array);
    }
}