package fr.barlords.mineralconquest.blocks;

import net.minecraft.block.Block;
import net.minecraftforge.common.ToolType;

public class BasicBlock extends Block {

    public BasicBlock(Properties properties , int hardness , int resistance , ToolType toolType , int harvestLvl) {
        super(properties
                .strength(hardness , resistance)
                .harvestTool(toolType)
                .harvestLevel(harvestLvl)
        );
    }


}
