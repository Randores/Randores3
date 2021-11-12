package io.github.socraticphoenix.randores.mod.definition;

import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.definition.component.IRandoresOre;
import io.github.socraticphoenix.randores.api.plugin.IRandoresPlugin;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;

import java.util.function.BiPredicate;

public class RandoresOre implements IRandoresOre {
    private IRandoresDefinition definition;
    private IRandoresPlugin owner;
    private BiPredicate<BlockState, BlockPos> generationSettings;

    public RandoresOre(IRandoresPlugin owner, IRandoresDefinition definition, BiPredicate<BlockState, BlockPos> generationSettings) {
        this.owner = owner;
        this.definition = definition;
        this.generationSettings = generationSettings;
    }

    @Override
    public BiPredicate<BlockState, BlockPos> generationSettings() {
        return this.generationSettings;
    }

    @Override
    public IRandoresPlugin owner() {
        return this.owner;
    }

    @Override
    public IRandoresDefinition definition() {
        return this.definition;
    }
}
