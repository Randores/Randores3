package io.github.socraticphoenix.randores.mod.data;

import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.mod.RandoresWorldData;
import io.github.socraticphoenix.randores.mod.block.RandoresOreBlock;
import net.minecraft.block.BlockState;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityType;

import java.util.Optional;

public class RandoresTileEntity extends TileEntity {
    public static final TileEntityType<RandoresTileEntity> TYPE = TileEntityType.Builder.create(RandoresTileEntity::new, RandoresOreBlock.INSTANCE)
            .build(null);

    private int seed;
    private int index;

    public RandoresTileEntity() {
        super(TYPE);
    }

    public RandoresTileEntity(int seed, int index) {
        this();
        this.seed = seed;
        this.index = index;
    }

    public Optional<IRandoresDefinition> definition() {
        RandoresWorldData data = RandoresWorldData.from(seed);
        return data.canGet(index) ? Optional.ofNullable(data.get(index)) : Optional.empty();
    }

    public int getIndex() {
        return index;
    }

    public int getSeed() {
        return seed;
    }

    public void setSeed(int seed) {
        this.seed = seed;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        nbt = super.write(nbt);
        nbt.putInt("seed", seed);
        nbt.putInt("index", index);
        return nbt;
    }

    @Override
    public void read(BlockState state, CompoundNBT nbt) {
        super.read(state, nbt);
        this.seed = nbt.getInt("seed");
        this.index = nbt.getInt("index");
    }

    @Override
    public CompoundNBT getUpdateTag() {
        CompoundNBT nbt = new CompoundNBT();
        nbt.putInt("seed", seed);
        nbt.putInt("index", index);
        return nbt;
    }

    @Override
    public void handleUpdateTag(BlockState state, CompoundNBT nbt) {
        this.seed = nbt.getInt("seed");
        this.index = nbt.getInt("index");
    }
}
