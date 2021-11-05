package fr.barlords.mineralconquest.init;

import fr.barlords.mineralconquest.References;
import fr.barlords.mineralconquest.items.*;
import fr.barlords.mineralconquest.lists.CustomArmorTiers;
import fr.barlords.mineralconquest.lists.CustomToolTiers;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ModItems {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, References.MODID);

    public static final RegistryObject<Item> TITANE_INGOT = ITEMS.register("titane_ingot" , () -> new BasicItem(new Item.Properties()));
    public static final RegistryObject<SwordItem> TITANE_SWORD = ITEMS.register("titane_sword" , () -> new CustomSword(CustomToolTiers.TITANE , -2 , -2.4f , new Item.Properties()));
    public static final RegistryObject<AxeItem> TITANE_AXE = ITEMS.register("titane_axe" , () -> new CustomAxe(CustomToolTiers.TITANE , -2 , -2.7f , new Item.Properties()));
    public static final RegistryObject<PickaxeItem> TITANE_PICKAXE = ITEMS.register("titane_pickaxe" , () -> new CustomPickaxe(CustomToolTiers.TITANE , -4 , -2.8f , new Item.Properties()));
    public static final RegistryObject<HoeItem> TITANE_HOE = ITEMS.register("titane_hoe" , () -> new CustomHoe(CustomToolTiers.TITANE , -4 , -3f , new Item.Properties()));
    public static final RegistryObject<ShovelItem> TITANE_SHOVEL = ITEMS.register("titane_shovel" , () -> new CustomShovel(CustomToolTiers.TITANE , -4 , -3f , new Item.Properties()));
    public static final RegistryObject<ArmorItem> TITANE_HELMET = ITEMS.register("titane_helmet" , () ->  new CustomHelmet(CustomArmorTiers.TITANE , EquipmentSlotType.HEAD ,new Item.Properties()));
    public static final RegistryObject<ArmorItem> TITANE_CHESTPLATE = ITEMS.register("titane_chestplate" , () -> new CustomChestplate(CustomArmorTiers.TITANE , EquipmentSlotType.CHEST, new Item.Properties()));
    public static final RegistryObject<ArmorItem> TITANE_LEGGINGS = ITEMS.register("titane_leggings" , () -> new CustomLeggings(CustomArmorTiers.TITANE , EquipmentSlotType.LEGS, new Item.Properties()));
    public static final RegistryObject<ArmorItem> TITANE_BOOTS = ITEMS.register("titane_boots" , () -> new CustomBoots(CustomArmorTiers.TITANE , EquipmentSlotType.FEET, new Item.Properties()));

    public static final RegistryObject<Item> BARLORITE = ITEMS.register("barlorite" , () -> new BasicItem(new Item.Properties()));
    public static final RegistryObject<SwordItem> BARLORITE_SWORD = ITEMS.register("barlorite_sword" , () -> new CustomSword(CustomToolTiers.BARLORITE , -2 , -2.4f , new Item.Properties()));
    public static final RegistryObject<AxeItem> BARLORITE_AXE = ITEMS.register("barlorite_axe" , () -> new CustomAxe(CustomToolTiers.BARLORITE , -2 , -2.7f , new Item.Properties()));
    public static final RegistryObject<PickaxeItem> BARLORITE_PICKAXE = ITEMS.register("barlorite_pickaxe" , () -> new CustomPickaxe(CustomToolTiers.BARLORITE , -4 , -2.8f , new Item.Properties()));
    public static final RegistryObject<HoeItem> BARLORITE_HOE = ITEMS.register("barlorite_hoe" , () -> new CustomHoe(CustomToolTiers.BARLORITE , -4 , -3f , new Item.Properties()));
    public static final RegistryObject<ShovelItem> BARLORITE_SHOVEL = ITEMS.register("barlorite_shovel" , () -> new CustomShovel(CustomToolTiers.BARLORITE , -4 , -3f , new Item.Properties()));
    public static final RegistryObject<ArmorItem> BARLORITE_HELMET = ITEMS.register("barlorite_helmet" , () -> new CustomHelmet(CustomArmorTiers.BARLORITE , EquipmentSlotType.HEAD, new Item.Properties()));
    public static final RegistryObject<ArmorItem> BARLORITE_CHESTPLATE = ITEMS.register("barlorite_chestplate" , () -> new CustomChestplate(CustomArmorTiers.BARLORITE , EquipmentSlotType.CHEST, new Item.Properties()));
    public static final RegistryObject<ArmorItem> BARLORITE_LEGGINGS = ITEMS.register("barlorite_leggings" , () -> new CustomLeggings(CustomArmorTiers.BARLORITE , EquipmentSlotType.LEGS, new Item.Properties()));
    public static final RegistryObject<ArmorItem> BARLORITE_BOOTS = ITEMS.register("barlorite_boots" , () -> new CustomBoots(CustomArmorTiers.BARLORITE , EquipmentSlotType.FEET, new Item.Properties()));

    public static final RegistryObject<Item> TERRASTEEL_INGOT = ITEMS.register("terrasteel_ingot" , () -> new BasicItem(new Item.Properties()));
    public static final RegistryObject<SwordItem> TERRASTEEL_SWORD = ITEMS.register("terrasteel_sword" , () -> new CustomSword(CustomToolTiers.TERRASTEEL , -2 , -2.4f , new Item.Properties()));
    public static final RegistryObject<AxeItem> TERRASTEEL_AXE = ITEMS.register("terrasteel_axe" , () -> new CustomAxe(CustomToolTiers.TERRASTEEL , -2 , -2.7f , new Item.Properties()));
    public static final RegistryObject<PickaxeItem> TERRASTEEL_PICKAXE = ITEMS.register("terrasteel_pickaxe" , () -> new CustomPickaxe(CustomToolTiers.TERRASTEEL , -4 , -2.8f , new Item.Properties()));
    public static final RegistryObject<HoeItem> TERRASTEEL_HOE = ITEMS.register("terrasteel_hoe" , () -> new CustomHoe(CustomToolTiers.TERRASTEEL , -4 , -3f , new Item.Properties()));
    public static final RegistryObject<ShovelItem> TERRASTEEL_SHOVEL = ITEMS.register("terrasteel_shovel" , () -> new CustomShovel(CustomToolTiers.TERRASTEEL , -4 , -3f , new Item.Properties()));
    public static final RegistryObject<ArmorItem> TERRASTEEL_HELMET = ITEMS.register("terrasteel_helmet" , () -> new CustomHelmet(CustomArmorTiers.TERRASTEEL , EquipmentSlotType.HEAD, new Item.Properties()));
    public static final RegistryObject<ArmorItem> TERRASTEEL_CHESTPLATE = ITEMS.register("terrasteel_chestplate" , () -> new CustomChestplate(CustomArmorTiers.TERRASTEEL , EquipmentSlotType.CHEST, new Item.Properties()));
    public static final RegistryObject<ArmorItem> TERRASTEEL_LEGGINGS = ITEMS.register("terrasteel_leggings" , () -> new CustomLeggings(CustomArmorTiers.TERRASTEEL , EquipmentSlotType.LEGS, new Item.Properties()));
    public static final RegistryObject<ArmorItem> TERRASTEEL_BOOTS = ITEMS.register("terrasteel_boots" , () -> new CustomBoots(CustomArmorTiers.TERRASTEEL , EquipmentSlotType.FEET, new Item.Properties()));

    public static final RegistryObject<Item> PURIUM_NUGGET = ITEMS.register("purium_nugget" , () -> new BasicItem(new Item.Properties()));
    public static final RegistryObject<Item> PURIUM_INGOT = ITEMS.register("purium_ingot" , () -> new BasicItem(new Item.Properties()));
    public static final RegistryObject<SwordItem> PURIUM_SWORD = ITEMS.register("purium_sword" , () -> new CustomSword(CustomToolTiers.PURIUM , -2 , -2.4f , new Item.Properties()));
    public static final RegistryObject<AxeItem> PURIUM_AXE = ITEMS.register("purium_axe" , () -> new CustomAxe(CustomToolTiers.PURIUM , -2 , -2.7f , new Item.Properties()));
    public static final RegistryObject<PickaxeItem> PURIUM_PICKAXE = ITEMS.register("purium_pickaxe" , () -> new CustomPickaxe(CustomToolTiers.PURIUM , -4 , -2.8f , new Item.Properties()));
    public static final RegistryObject<ArmorItem> PURIUM_HELMET = ITEMS.register("purium_helmet" , () -> new CustomHelmet(CustomArmorTiers.PURIUM , EquipmentSlotType.HEAD, new Item.Properties()));
    public static final RegistryObject<ArmorItem> PURIUM_CHESTPLATE = ITEMS.register("purium_chestplate" , () -> new CustomChestplate(CustomArmorTiers.PURIUM , EquipmentSlotType.CHEST, new Item.Properties()));
    public static final RegistryObject<ArmorItem> PURIUM_LEGGINGS = ITEMS.register("purium_leggings" , () -> new CustomLeggings(CustomArmorTiers.PURIUM , EquipmentSlotType.LEGS, new Item.Properties()));
    public static final RegistryObject<ArmorItem> PURIUM_BOOTS = ITEMS.register("purium_boots" , () -> new CustomBoots(CustomArmorTiers.PURIUM , EquipmentSlotType.FEET, new Item.Properties()));

    public static final RegistryObject<Item> CELESTIUM_CRYSTAL = ITEMS.register("celestium_crystal" , () -> new BasicItem(new Item.Properties()));
    public static final RegistryObject<Item> CELESTIUM_INGOT = ITEMS.register("celestium_ingot" , () -> new BasicItem(new Item.Properties()));
    public static final RegistryObject<ArmorItem> CELESTIUM_HELMET = ITEMS.register("celestium_helmet" , () -> new CustomHelmet(CustomArmorTiers.CELESTIUM , EquipmentSlotType.HEAD, new Item.Properties()));
    public static final RegistryObject<ArmorItem> CELESTIUM_CHESTPLATE = ITEMS.register("celestium_chestplate" , () -> new CustomChestplate(CustomArmorTiers.CELESTIUM , EquipmentSlotType.CHEST, new Item.Properties()));
    public static final RegistryObject<ArmorItem> CELESTIUM_LEGGINGS = ITEMS.register("celestium_leggings" , () -> new CustomLeggings(CustomArmorTiers.CELESTIUM , EquipmentSlotType.LEGS, new Item.Properties()));
    public static final RegistryObject<ArmorItem> CELESTIUM_BOOTS = ITEMS.register("celestium_boots" , () -> new CustomBoots(CustomArmorTiers.CELESTIUM , EquipmentSlotType.FEET, new Item.Properties()));

    public static final RegistryObject<ArmorItem> SPEED_BOOTS = ITEMS.register("speed_boots" , () -> new SpeedBoots(CustomArmorTiers.LEGENDARY , EquipmentSlotType.FEET, new Item.Properties()));
    public static final RegistryObject<ArmorItem> AQUA_LEGGINGS = ITEMS.register("aqua_leggings" , () -> new AquaLeggings(CustomArmorTiers.LEGENDARY , EquipmentSlotType.LEGS, new Item.Properties()));
    public static final RegistryObject<ArmorItem> HASTE_CHESTPLATE = ITEMS.register("haste_chestplate" , () -> new HasteChesplate(CustomArmorTiers.LEGENDARY , EquipmentSlotType.CHEST, new Item.Properties()));
    public static final RegistryObject<ArmorItem> AQUA_HELMET = ITEMS.register("aqua_helmet" , () -> new AquaHelmet(CustomArmorTiers.LEGENDARY , EquipmentSlotType.HEAD, new Item.Properties()));

}
