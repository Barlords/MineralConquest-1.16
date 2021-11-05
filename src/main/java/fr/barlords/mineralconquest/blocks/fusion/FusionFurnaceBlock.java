package fr.barlords.mineralconquest.blocks.fusion;

import fr.barlords.mineralconquest.blocks.fusion.tileentity.FusionFurnaceTileEntity;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.stats.Stats;
import net.minecraft.tileentity.FurnaceTileEntity;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.Direction;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nullable;
import java.util.Random;

public class FusionFurnaceBlock extends AbstractFusionFurnaceBlock {

    public FusionFurnaceBlock() {
        super(AbstractBlock.Properties.copy(Blocks.FURNACE));
    }

    public FusionFurnaceBlock(Properties properties) {
        super(properties);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader blockReader) {
        return new FusionFurnaceTileEntity();
    }

    protected void openContainer(World worldIn, BlockPos blockPos, PlayerEntity playerEntity) {
        TileEntity tileentity = worldIn.getBlockEntity(blockPos);
        if (tileentity instanceof FusionFurnaceTileEntity) {
            playerEntity.openMenu((INamedContainerProvider)tileentity);
            playerEntity.awardStat(Stats.INTERACT_WITH_FURNACE);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState blockState, World worldIn, BlockPos blockPos, Random random) {
        if (blockState.getValue(LIT)) {
            double d0 = (double)blockPos.getX() + 0.5D;
            double d1 = (double)blockPos.getY();
            double d2 = (double)blockPos.getZ() + 0.5D;
            if (random.nextDouble() < 0.1D) {
                worldIn.playLocalSound(d0, d1, d2, SoundEvents.FURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = blockState.getValue(FACING);
            Direction.Axis direction$axis = direction.getAxis();
            double d3 = 0.52D;
            double d4 = random.nextDouble() * 0.6D - 0.3D;
            double d5 = direction$axis == Direction.Axis.X ? (double)direction.getStepX() * 0.52D : d4;
            double d6 = random.nextDouble() * 6.0D / 16.0D;
            double d7 = direction$axis == Direction.Axis.Z ? (double)direction.getStepZ() * 0.52D : d4;
            worldIn.addParticle(ParticleTypes.SMOKE, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
            worldIn.addParticle(ParticleTypes.FLAME, d0 + d5, d1 + d6, d2 + d7, 0.0D, 0.0D, 0.0D);
        }
    }
}
