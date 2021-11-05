package fr.barlords.mineralconquest.blocks.fusion.recipe;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.item.crafting.ShapedRecipe;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;

public class FusionCookingRecipeSerializer<T extends AbstractFusionCookingRecipe> extends net.minecraftforge.registries.ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<T> {
    public final int defaultCookingTime;
    public final FusionCookingRecipeSerializer.IFactory<T> factory;

    public FusionCookingRecipeSerializer(FusionCookingRecipeSerializer.IFactory<T> factory, int cookingTime) {
        this.defaultCookingTime = cookingTime;
        this.factory = factory;
    }

    public T fromJson(ResourceLocation ressourceLocation, JsonObject json) {
        String group = JSONUtils.getAsString(json, "group", "");
        JsonElement jsonelement1 = (JsonElement)(JSONUtils.isArrayNode(json, "ingredient1") ? JSONUtils.getAsJsonArray(json, "ingredient1") : JSONUtils.getAsJsonObject(json, "ingredient1"));
        Ingredient ingredient1 = Ingredient.fromJson(jsonelement1);
        JsonElement jsonelement2 = (JsonElement)(JSONUtils.isArrayNode(json, "ingredient2") ? JSONUtils.getAsJsonArray(json, "ingredient2") : JSONUtils.getAsJsonObject(json, "ingredient2"));
        Ingredient ingredient2 = Ingredient.fromJson(jsonelement2);
        JsonElement jsonelement3 = (JsonElement)(JSONUtils.isArrayNode(json, "catalyser") ? JSONUtils.getAsJsonArray(json, "catalyser") : JSONUtils.getAsJsonObject(json, "catalyser"));
        Ingredient catalyser = Ingredient.fromJson(jsonelement3);
        //Forge: Check if primitive string to keep vanilla or a object which can contain a count field.
        if (!json.has("result")) throw new com.google.gson.JsonSyntaxException("Missing result, expected to find a string or object");
        ItemStack itemstack;
        if (json.get("result").isJsonObject()) itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
        else {
            String s1 = JSONUtils.getAsString(json, "result");
            ResourceLocation resourcelocation = new ResourceLocation(s1);
            itemstack = new ItemStack(Registry.ITEM.getOptional(resourcelocation).orElseThrow(() -> {
                return new IllegalStateException("Item: " + s1 + " does not exist");
            }));
        }
        float experience = JSONUtils.getAsFloat(json, "experience", 0.0F);
        int cookingTime = JSONUtils.getAsInt(json, "cookingtime", this.defaultCookingTime);
        return this.factory.create(ressourceLocation, group, ingredient1, ingredient2, catalyser, itemstack, experience, cookingTime);
    }

    public T fromNetwork(ResourceLocation ressourceLocation, PacketBuffer buffer) {
        String s = buffer.readUtf(32767);
        Ingredient ingredient1 = Ingredient.fromNetwork(buffer);
        Ingredient ingredient2 = Ingredient.fromNetwork(buffer);
        Ingredient catalyser = Ingredient.fromNetwork(buffer);
        ItemStack itemstack = buffer.readItem();
        float f = buffer.readFloat();
        int i = buffer.readVarInt();
        return this.factory.create(ressourceLocation, s, ingredient1, ingredient2, catalyser, itemstack, f, i);
    }

    public void toNetwork(PacketBuffer buffer, T recipe) {
        buffer.writeUtf(recipe.group);
        recipe.ingredient1.toNetwork(buffer);
        recipe.ingredient2.toNetwork(buffer);
        recipe.catalyser.toNetwork(buffer);
        buffer.writeItem(recipe.result);
        buffer.writeFloat(recipe.experience);
        buffer.writeVarInt(recipe.cookingTime);
    }

    public interface IFactory<T extends AbstractFusionCookingRecipe> {
        T create(ResourceLocation ressourceLocation, String p_create_2_, Ingredient ingredientIn1, Ingredient ingredientIn2, Ingredient catalyserIn, ItemStack p_create_4_, float p_create_5_, int p_create_6_);
    }
}
