package io.github.socraticphoenix.randores.api.definition.component;

import net.minecraft.block.BlockState;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;
import net.minecraft.util.math.BlockPos;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

public interface IRandoresOre extends IRandoresBlock {

    BiPredicate<BlockState, BlockPos> generationSettings();

}
