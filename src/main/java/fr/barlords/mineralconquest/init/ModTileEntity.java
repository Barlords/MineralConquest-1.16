package fr.barlords.mineralconquest.init;

import com.mojang.datafixers.types.Type;
import fr.barlords.mineralconquest.References;
import fr.barlords.mineralconquest.blocks.fusion.container.FusionFurnaceContainer;
import fr.barlords.mineralconquest.blocks.fusion.gui.FusionFurnaceScreen;
import fr.barlords.mineralconquest.blocks.fusion.tileentity.AbstractFusionFurnaceTileEntity;
import fr.barlords.mineralconquest.blocks.fusion.tileentity.FusionFurnaceTileEntity;
import net.minecraft.block.Block;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.client.gui.screen.inventory.FurnaceScreen;
import net.minecraft.inventory.container.ContainerType;
import net.minecraft.inventory.container.FurnaceContainer;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = "mineralconquest")

public class ModTileEntity {

    public static final DeferredRegister<TileEntityType<?>> TILE_ENTITY_TYPES = DeferredRegister.create(ForgeRegistries.TILE_ENTITIES, References.MODID);

    public static final RegistryObject<TileEntityType<FusionFurnaceTileEntity>> FUSION_FURNACE_TILE_ENTITY = TILE_ENTITY_TYPES.register("ffte", () -> TileEntityType.Builder.of(FusionFurnaceTileEntity::new, ModBlocks.FUSION_FURNACE.get()).build(null));

    /*public static final ModTileEntity INSTANCE = new ModTileEntity();

    //public static ContainerType<FusionFurnaceContainer> FUSION_FURNACE_C;

    public static TileEntityType<FusionFurnaceTileEntity> FUSION_FURNACE_TILE_ENTITY;

    @SubscribeEvent
    public static void registerTileEntities(RegistryEvent.Register<TileEntityType<?>> registryEvent) {
        FUSION_FURNACE_TILE_ENTITY = registerTileEntity(FusionFurnaceTileEntity::new, "ffte", ModBlocks.FUSION_FURNACE.get(), registryEvent);
    }
    public static TileEntityType registerTileEntity(Supplier<LockableTileEntity> supplier, String registryName, Block block, RegistryEvent.Register<TileEntityType<?>> registryEvent) {
        TileEntityType tileEntityType = TileEntityType.Builder.of(supplier , new Block[]{block}).build((Type)null) ;
        tileEntityType.setRegistryName(registryName);
        registryEvent.getRegistry().register(tileEntityType);
        return tileEntityType;
    }

    /*@SubscribeEvent
    public static void registerContainerTypes(RegistryEvent.Register<ContainerType<?>> registryEvent) {
        FUSION_FURNACE_C = IForgeContainerType.create(FusionFurnaceContainer::new);
        FUSION_FURNACE_C.setRegistryName("container");
        registryEvent.getRegistry().register(FUSION_FURNACE_C);
    }

    @SubscribeEvent
    public static void setup(FMLClientSetupEvent event) {
        ScreenManager.register(FUSION_FURNACE_C , FusionFurnaceScreen::new);
    }*/
}
