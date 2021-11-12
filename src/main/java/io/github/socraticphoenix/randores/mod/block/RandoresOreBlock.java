package io.github.socraticphoenix.randores.mod.block;

import io.github.socraticphoenix.randores.api.definition.component.IRandoresBlock;
import io.github.socraticphoenix.randores.api.definition.component.IRandoresOre;
import io.github.socraticphoenix.randores.mod.data.RandoresTileEntity;
import io.github.socraticphoenix.randores.mod.gen.RandoresSelector;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.material.Material;
import net.minecraft.state.StateContainer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.IBlockReader;
import net.minecraftforge.common.extensions.IForgeBlock;

import javax.annotation.Nullable;

public class RandoresOreBlock extends Block implements IForgeBlock {
    public static RandoresOreBlock INSTANCE = new RandoresOreBlock();
    private RandoresSelector selector = new RandoresSelector();

    public RandoresOreBlock() {
        super(Properties.create(Material.ROCK));

        this.setDefaultState(this.stateContainer.getBaseState()
                .with(IRandoresBlock.HARVERST_LEVEL, 0)
                .with(IRandoresBlock.TILED, false));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        super.fillStateContainer(builder);
        builder.add(IRandoresOre.HARVERST_LEVEL, IRandoresOre.TILED);
    }

    @Override
    public boolean hasTileEntity(BlockState state) {
        return state.get(IRandoresOre.TILED);
    }

    @Nullable
    @Override
    public TileEntity createTileEntity(BlockState state, IBlockReader world) {
        if (state.get(IRandoresOre.TILED)) {
            return new RandoresTileEntity();
        }
        return null;
    }

}
