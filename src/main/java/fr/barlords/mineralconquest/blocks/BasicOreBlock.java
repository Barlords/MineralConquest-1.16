package fr.barlords.mineralconquest.blocks;

import fr.barlords.mineralconquest.init.ModBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.OreBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.common.ToolType;

import java.util.Random;

public class BasicOreBlock extends OreBlock {
    public BasicOreBlock(Properties properties , int hardness , int resistance , ToolType toolType , int harvestLvl) {
        super(properties
                .strength(hardness , resistance)
                .harvestTool(toolType)
                .harvestLevel(harvestLvl)
                .requiresCorrectToolForDrops()
        );
    }
}
