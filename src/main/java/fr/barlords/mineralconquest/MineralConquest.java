package fr.barlords.mineralconquest;

import fr.barlords.mineralconquest.blocks.fusion.gui.FusionFurnaceScreen;
import fr.barlords.mineralconquest.init.*;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(References.MODID)
public class MineralConquest {

    public MineralConquest() {

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::clientSetup);

        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        ModBlocks.BLOCKS.register(bus);
        ModContainers.CONTAINER_TYPES.register(bus);
        ModItems.ITEMS.register(bus);
        ModRecipes.RECIPES.register(bus);
        ModTileEntity.TILE_ENTITY_TYPES.register(bus);

    }

    private void setup(FMLCommonSetupEvent e) {

        ModFeatures features = new ModFeatures();
        features.init();
        MinecraftForge.EVENT_BUS.register(features);

        ModEvents events = new ModEvents();
        MinecraftForge.EVENT_BUS.register(events);

    }

    private void clientSetup(FMLClientSetupEvent e) {

        ScreenManager.register(ModContainers.FUSION_FURNACE_CONTAINER.get(), FusionFurnaceScreen::new);

        IEventBus bus = MinecraftForge.EVENT_BUS;
        ModKeybindings.register();
        bus.addListener(ModKeybindings::onKeyPress);

    }

}