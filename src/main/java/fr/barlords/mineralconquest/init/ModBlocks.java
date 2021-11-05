package fr.barlords.mineralconquest.init;

import fr.barlords.mineralconquest.MineralConquest;
import fr.barlords.mineralconquest.References;
import fr.barlords.mineralconquest.blocks.BasicBlock;
import fr.barlords.mineralconquest.blocks.BasicOreBlock;
import fr.barlords.mineralconquest.blocks.fusion.FusionFurnaceBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.material.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

import java.util.function.Supplier;

public class ModBlocks {

    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, References.MODID);

    public static final RegistryObject<Block> TITANE_BLOCK = createBlock("titane_block" , () -> new BasicBlock(AbstractBlock.Properties.of(Material.METAL) , 3 , 25 , ToolType.PICKAXE , 3));
    public static final RegistryObject<Block> TITANE_ORE = createBlock("titane_ore" , () -> new BasicOreBlock(AbstractBlock.Properties.of(Material.METAL) , 3 , 25 , ToolType.PICKAXE , 3));

    public static final RegistryObject<Block> TERRASTEEL_BLOCK = createBlock("terrasteel_block" , () -> new BasicBlock(AbstractBlock.Properties.of(Material.METAL) , 3 , 30 , ToolType.PICKAXE , 4));
    public static final RegistryObject<Block> TERRASTEEL_ORE = createBlock("terrasteel_ore" , () -> new BasicOreBlock(AbstractBlock.Properties.of(Material.METAL) , 3 , 30 , ToolType.PICKAXE , 4));

    public static final RegistryObject<Block> BARLORITE_BLOCK = createBlock("barlorite_block" , () -> new BasicBlock(AbstractBlock.Properties.of(Material.METAL) , 3 , 30 , ToolType.PICKAXE , 5));
    public static final RegistryObject<Block> BARLORITE_ORE = createBlock("barlorite_ore" , () -> new BasicOreBlock(AbstractBlock.Properties.of(Material.METAL) , 3 , 30 , ToolType.PICKAXE , 5));

    public static final RegistryObject<Block> CELESTIUM_BLOCK = createBlock("celestium_block" , () -> new BasicBlock(AbstractBlock.Properties.of(Material.METAL) , 3 , 35 , ToolType.PICKAXE , 6));
    public static final RegistryObject<Block> CELESTIUM_ORE = createBlock("celestium_ore" , () -> new BasicOreBlock(AbstractBlock.Properties.of(Material.METAL) , 3 , 35 , ToolType.PICKAXE , 6));


    public static final RegistryObject<Block> PURIUM_BLOCK = createBlock("purium_block" , () -> new BasicBlock(AbstractBlock.Properties.of(Material.METAL) , 3 , 35 , ToolType.PICKAXE , 6));

    //public static final RegistryObject<Block> FUSION_FURNACE = createBlock( "fusion_furnace" , () -> new FusionFurnaceBlock(AbstractBlock.Properties.copy(Blocks.FURNACE)));
    public static final RegistryObject<Block> FUSION_FURNACE = createBlock( "fusion_furnace" , FusionFurnaceBlock::new);


    public static RegistryObject<Block> createBlock(String name, Supplier<? extends Block> supplier)
    {
        RegistryObject<Block> block = BLOCKS.register(name , supplier);
        if(name.contains("barlorite") || name.contains("purium"))
            ModItems.ITEMS.register(name , () -> new BlockItem(block.get() , new Item.Properties().fireResistant().tab(ModItemGroups.TAB_MOD)));
        else
            ModItems.ITEMS.register(name , () -> new BlockItem(block.get() , new Item.Properties().tab(ModItemGroups.TAB_MOD)));
        return block;
    }

}
