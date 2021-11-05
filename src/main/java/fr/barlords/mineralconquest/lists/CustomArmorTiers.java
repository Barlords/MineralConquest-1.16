package fr.barlords.mineralconquest.lists;

import fr.barlords.mineralconquest.References;
import fr.barlords.mineralconquest.init.ModItems;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;

import java.util.function.Supplier;

public enum CustomArmorTiers implements IArmorMaterial
{
    TITANE(References.MODID + ":titane", 300, new int[]{4, 7, 9, 4}, 25, new LazyValue<>(() -> {return Ingredient.of(ModItems.TITANE_INGOT.get());}),SoundEvents.ARMOR_EQUIP_IRON, 2.0f),
    TERRASTEEL(References.MODID + ":terrasteel", 400, new int[]{5, 8, 10, 5}, 27,new LazyValue<>(() -> {return Ingredient.of(ModItems.TERRASTEEL_INGOT.get());}),SoundEvents.ARMOR_EQUIP_IRON, 2.0f),
    BARLORITE(References.MODID + ":barlorite", 400, new int[]{5, 8, 10, 5}, 27, new LazyValue<>(() -> {return Ingredient.of(ModItems.BARLORITE.get());}),SoundEvents.ARMOR_EQUIP_IRON, 2.0f),
    PURIUM(References.MODID + ":purium", 400*9, new int[]{7, 10, 12, 7}, 29, new LazyValue<>(() -> {return Ingredient.of(ModItems.PURIUM_INGOT.get());}),SoundEvents.ENDER_DRAGON_GROWL, 2.0f),
    CELESTIUM(References.MODID + ":celestium", 300, new int[]{2, 5, 6, 2}, 35, new LazyValue<>(() -> {return Ingredient.of(ModItems.CELESTIUM_INGOT.get());}),SoundEvents.ARMOR_EQUIP_IRON, 0.0f),
    LEGENDARY(References.MODID + ":legendary", 100, new int[]{1, 1, 1, 1}, 35, new LazyValue<>(() -> {return null;}),SoundEvents.LIGHTNING_BOLT_THUNDER, 0.0f);

    private static final int[] max_damage_array = new int[]{4 , 7 , 8 , 5};
    private String name ;
    private SoundEvent equipSound;
    private int durability, enchantability;
    private LazyValue<Ingredient> repairItem;
    private int[] damageReductionAmounts;
    private float toughness;

    private CustomArmorTiers(String name, int durability, int[] damageReductionAmounts, int enchantability, LazyValue<Ingredient> repairItem, SoundEvent equipSound, float toughness)
    {
        this.name = name;
        this.equipSound = equipSound;
        this.durability = durability;
        this.enchantability = enchantability;
        this.repairItem = repairItem;
        this.damageReductionAmounts = damageReductionAmounts;
        this.toughness = toughness;
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slotIn) {
        return max_damage_array[slotIn.getIndex()] * this.durability;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slotIn) {
        return this.damageReductionAmounts[slotIn.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Override
    public SoundEvent getEquipSound() {
        return this.equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairItem.get();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return 0;
    }
}
