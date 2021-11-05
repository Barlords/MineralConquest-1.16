package fr.barlords.mineralconquest.utils;


import com.mojang.datafixers.types.Type;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.LockableTileEntity;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.tileentity.TileEntityType.Builder;
import net.minecraftforge.event.RegistryEvent.Register;

import java.util.function.Supplier;

public abstract class RegistryUtil {
    public RegistryUtil() {
    }

    /*public static void registerBlock(Block b, String registryName, Register<Block> registryEvent) {
        b.setRegistryName(registryName);
        registryEvent.getRegistry().register(b);
    }

    public static void registerItem(Item i, String registryName, Register<Item> registryEvent) {
        i.setRegistryName(registryName);
        registryEvent.getRegistry().register(i);
    }

    public static TileEntityType registerTileEntity(Supplier<LockableTileEntity> supplier, String registryName, Block block, Register<TileEntityType<?>> registryEvent) {
        TileEntityType tileEntityType = Builder.create(supplier , new Block[]{block}).build((Type)null) ;
        tileEntityType.setRegistryName(registryName);
        registryEvent.getRegistry().register(tileEntityType);
        return tileEntityType;
    }*/
}