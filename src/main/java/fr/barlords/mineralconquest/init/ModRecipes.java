package fr.barlords.mineralconquest.init;

import fr.barlords.mineralconquest.References;
import fr.barlords.mineralconquest.blocks.fusion.recipe.FusionCookingRecipeSerializer;
import fr.barlords.mineralconquest.blocks.fusion.recipe.FusionFurnaceRecipe;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModRecipes {

    public static final IRecipeType<FusionFurnaceRecipe> FUSION_FURNACE_RECIPE_TYPE = IRecipeType.register(References.MODID + ":consolidating");

    public static final DeferredRegister<IRecipeSerializer<?>> RECIPES = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, References.MODID);

    public static final RegistryObject<FusionCookingRecipeSerializer<FusionFurnaceRecipe>> FUSION_FURNACE_RECIPE_SERIALIZER = RECIPES.register("consolidating",() -> new FusionCookingRecipeSerializer<>(FusionFurnaceRecipe::new, 200));

}
