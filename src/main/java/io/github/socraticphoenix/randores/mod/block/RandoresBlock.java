package io.github.socraticphoenix.randores.mod.block;

import io.github.socraticphoenix.randores.api.IRandoresBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorldReader;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.ToolType;
import net.minecraftforge.common.extensions.IForgeBlock;

import javax.annotation.Nullable;

public class RandoresBlock extends Block implements IForgeBlock, IRandoresBlock {

    public RandoresBlock(Properties properties) {
        super(properties);
    }


    @Override
    public float getSlipperiness(BlockState state, IWorldReader world, BlockPos pos, @Nullable Entity entity) {
        return 0;
    }

    @Override
    public boolean canSustainPlant(BlockState state, IBlockReader world, BlockPos pos, Direction facing, IPlantable plantable) {
        return false;
    }

    @Override
    public ToolType getHarvestTool(BlockState state) {
        return null;
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return 0;
    }



}
