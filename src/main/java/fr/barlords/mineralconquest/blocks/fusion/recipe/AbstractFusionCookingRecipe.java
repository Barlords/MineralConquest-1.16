package fr.barlords.mineralconquest.blocks.fusion.recipe;

import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.NonNullList;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;

public abstract class AbstractFusionCookingRecipe implements IRecipe<IInventory> {
    protected final IRecipeType<?> type;
    protected final ResourceLocation id;
    protected final String group;
    protected final Ingredient ingredient1;
    protected final Ingredient ingredient2;
    protected final Ingredient catalyser;
    protected final ItemStack result;
    protected final float experience;
    protected final int cookingTime;

    public AbstractFusionCookingRecipe(IRecipeType<?> recipeType, ResourceLocation ressourceLocation, String groupIn, Ingredient ingredientIn1, Ingredient ingredientIn2, Ingredient catalyserIn, ItemStack resultIn, float experienceIn, int cookTimeIn) {
        this.type = recipeType;
        this.id = ressourceLocation;
        this.group = groupIn;
        this.ingredient1 = ingredientIn1;
        this.ingredient2 = ingredientIn2;
        this.catalyser = catalyserIn;
        this.result = resultIn;
        this.experience = experienceIn;
        this.cookingTime = cookTimeIn;
    }

    public boolean matches(IInventory inv, World worldIn) {
        if (this.ingredient1.test(inv.getItem(0)) && this.ingredient2.test(inv.getItem(3)) && this.catalyser.test(inv.getItem(4))){
            return true;
        }
        else { return false;}
    }

    public ItemStack assemble(IInventory inv) {
        return this.result.copy();
    }

    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> nonnulllist = NonNullList.create();
        nonnulllist.add(this.ingredient1);
        nonnulllist.add(this.ingredient2);
        nonnulllist.add(this.catalyser);
        return nonnulllist;
    }

    public float getExperience() {
        return this.experience;
    }

    public ItemStack getResultItem() {
        return this.result;
    }

    public String getGroup() {
        return this.group;
    }

    public int getCookingTime() {
        return this.cookingTime;
    }

    public ResourceLocation getId() {
        return this.id;
    }

    public IRecipeType<?> getType() {
        return this.type;
    }
}