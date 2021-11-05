package fr.barlords.mineralconquest.blocks.fusion.recipe;

import fr.barlords.mineralconquest.init.ModBlocks;
import fr.barlords.mineralconquest.init.ModRecipes;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.util.ResourceLocation;

public class FusionFurnaceRecipe extends AbstractFusionCookingRecipe {
    public FusionFurnaceRecipe(ResourceLocation ressourceLocation, String groupId, Ingredient ingredientIn1, Ingredient ingredientIn2, Ingredient catalyserIn, ItemStack resultIn, float experienceIn, int cookTimeIn) {
        super(ModRecipes.FUSION_FURNACE_RECIPE_TYPE, ressourceLocation, groupId, ingredientIn1, ingredientIn2, catalyserIn, resultIn, experienceIn, cookTimeIn);
    }

    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.FUSION_FURNACE.get());
    }

    public IRecipeSerializer<?> getSerializer() {
        return ModRecipes.FUSION_FURNACE_RECIPE_SERIALIZER.get();
    }
}