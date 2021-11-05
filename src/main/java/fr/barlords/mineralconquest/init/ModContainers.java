package fr.barlords.mineralconquest.init;

import fr.barlords.mineralconquest.References;
import fr.barlords.mineralconquest.blocks.fusion.container.FusionFurnaceContainer;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.common.extensions.IForgeContainerType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModContainers {

    public static final DeferredRegister<ContainerType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.CONTAINERS, References.MODID);

    public static final RegistryObject<ContainerType<FusionFurnaceContainer>> FUSION_FURNACE_CONTAINER = CONTAINER_TYPES.register("ffc", () -> IForgeContainerType.create(FusionFurnaceContainer::new));

}
