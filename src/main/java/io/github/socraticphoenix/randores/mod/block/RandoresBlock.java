package io.github.socraticphoenix.randores.mod.block;

import io.github.socraticphoenix.randores.api.definition.component.IRandoresBlock;
import io.github.socraticphoenix.randores.api.definition.component.IRandoresOre;
import io.github.socraticphoenix.randores.mod.data.RandoresTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.extensions.IForgeBlock;

public class RandoresBlock extends Block implements IForgeBlock {

    public RandoresBlock(Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState()
                .with(IRandoresBlock.HARVERST_LEVEL, 0)
                .with(IRandoresBlock.TILED, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(IRandoresBlock.TILED, IRandoresBlock.HARVERST_LEVEL);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return state.get(IRandoresBlock.TILED);
    }

    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        if (state.hasProperty(IRandoresBlock.TILED) && state.get(IRandoresBlock.TILED)) {
            return new RandoresTileEntity();
        }
        return null;
    }

    @Override
    public int getHarvestLevel(BlockState state) {
        return state.hasProperty(IRandoresBlock.HARVERST_LEVEL) ? state.get(IRandoresBlock.HARVERST_LEVEL) : super.getHarvestLevel(state);
    }
}
