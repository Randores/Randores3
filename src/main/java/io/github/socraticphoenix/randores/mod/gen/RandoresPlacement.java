package io.github.socraticphoenix.randores.mod.gen;

import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;
import io.github.socraticphoenix.randores.api.Randores;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.definition.component.IRandoresOre;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.WorldDecoratingHelper;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.stream.Stream;

public class RandoresPlacement extends Placement<RandoresPlacement.Config> {
    public static final RandoresPlacement INSTANCE = (RandoresPlacement) new RandoresPlacement(Config.CODEC)
            .setRegistryName(Randores.ID, "ore_placement");

    private RandoresSelector selector;

    public RandoresPlacement(Codec<RandoresPlacement.Config> codec) {
        super(codec);
        this.selector = new RandoresSelector();
    }

    private Set<Set<Integer>> tests = new HashSet<>();

    @Override
    public Stream<BlockPos> getPositions(WorldDecoratingHelper helper, Random rand, RandoresPlacement.Config config, BlockPos pos) {
        Stream.Builder<BlockPos> builder = Stream.builder();
        this.selector.setWorldSeed(yoinkSeed(helper));

        for (int i = 0; i < Randores.get().config().maxOrePerChunk(); i++) {
            BlockPos next = pos.add(rand.nextInt(16), rand.nextInt(helper.func_242891_a()), rand.nextInt(16));

        }

        return builder.build();
    }

    private long yoinkSeed(WorldDecoratingHelper helper) {
        try {
            Field field = helper.getClass().getField("field_242889_a");
            return ((ISeedReader) field.get(helper)).getSeed();
        } catch (NoSuchFieldException | IllegalAccessException e) {
            return 0;
        }
    }

    public static class Config implements IPlacementConfig {
        public static final Config INSTANCE = new Config();
        public static final Codec<Config> CODEC = Codec.unit(() -> INSTANCE);
    }

}
