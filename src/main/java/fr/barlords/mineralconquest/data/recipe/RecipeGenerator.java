package fr.barlords.mineralconquest.data.recipe;

import fr.barlords.mineralconquest.References;
import net.minecraft.advancements.criterion.EntityPredicate;
import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.KilledTrigger;
import net.minecraft.block.Blocks;
import net.minecraft.data.*;
import net.minecraft.entity.EntityType;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

import java.util.function.Consumer;

public class RecipeGenerator extends RecipeProvider {
    public RecipeGenerator(DataGenerator p_i48262_1_) {
        super(p_i48262_1_);
    }

    @Override
    protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shaped(Blocks.GRASS_BLOCK, 3)
                .pattern(" H ")
                .pattern("HTH")
                .pattern(" H ")
                .define('H', Blocks.GRASS)
                .define('T', Blocks.DIRT)
                .unlockedBy("unlock", InventoryChangeTrigger.Instance.hasItems(Blocks.DIRT, Blocks.GRASS))
                .save(consumer);

        ShapedRecipeBuilder.shaped(Blocks.DIAMOND_BLOCK)
                .pattern("TTT")
                .define('T', Blocks.DIRT)
                .unlockedBy("unlock", InventoryChangeTrigger.Instance.hasItems(Blocks.DIRT))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(Items.ENCHANTED_GOLDEN_APPLE)
                .requires(Items.APPLE)
                .requires(Blocks.GOLD_BLOCK)
                .unlockedBy("unlock", KilledTrigger.Instance.playerKilledEntity(EntityPredicate.Builder.entity().of(EntityType.CREEPER)))
                .save(consumer, new ResourceLocation(References.MODID, "notch_apple"));

        CookingRecipeBuilder.smelting(Ingredient.of(Items.DIAMOND), Items.ENCHANTED_GOLDEN_APPLE, 2f, 200)
                .unlockedBy("unlock", InventoryChangeTrigger.Instance.hasItems(Items.DIAMOND))
                .save(consumer);
    }
}
