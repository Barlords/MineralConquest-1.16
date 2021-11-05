package fr.barlords.mineralconquest.lists;

import fr.barlords.mineralconquest.init.ModItems;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

public enum CustomToolTiers implements IItemTier {

    TITANE(4 , 2000 , 7 , 10 , 25 , new LazyValue<>(() -> {return Ingredient.of(ModItems.TITANE_INGOT.get());})),
    TERRASTEEL(5, 3000, 8, 12, 27, new LazyValue<>(() -> {return Ingredient.of(ModItems.TERRASTEEL_INGOT.get());})),
    BARLORITE(5, 3000, 8, 12, 27, new LazyValue<>(() -> {return Ingredient.of(ModItems.BARLORITE.get());})),
    PURIUM(6, 3000*9, 10, 14, 29, new LazyValue<>(() -> {return Ingredient.of(ModItems.PURIUM_INGOT.get());}));

    private final int harvestLevel;
    private final int maxUses;
    private float efficiency;
    private final float attackDamage;
    private final int enchantability;
    private final LazyValue<Ingredient> repairMaterial;

    private CustomToolTiers(int harvestLevel, int maxUses, float efficiency, float attackDamage, int enchantability, LazyValue<Ingredient> repairMaterial) {
        this.harvestLevel = harvestLevel;
        this.maxUses = maxUses;
        this.efficiency = efficiency;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairMaterial = repairMaterial;
    }

    @Override
    public int getUses() {
        return maxUses;
    }

    @Override
    public float getSpeed() {
        return efficiency;
    }

    public void setSpeed(float lSpeed) {
        this.efficiency = lSpeed;
    }

    @Override
    public float getAttackDamageBonus() {
        return attackDamage;
    }

    @Override
    public int getLevel() {
        return harvestLevel;
    }

    @Override
    public int getEnchantmentValue() {
        return enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairMaterial.get();
    }
}
