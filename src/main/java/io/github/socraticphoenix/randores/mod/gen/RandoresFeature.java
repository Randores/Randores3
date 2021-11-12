package io.github.socraticphoenix.randores.mod.gen;

import com.mojang.serialization.Codec;
import io.github.socraticphoenix.randores.api.Randores;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.definition.component.IRandoresBlock;
import io.github.socraticphoenix.randores.api.definition.component.IRandoresOre;
import io.github.socraticphoenix.randores.api.definition.property.RandoresHarvestLevel;
import io.github.socraticphoenix.randores.mod.block.RandoresOreBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;

import java.util.Random;

public class RandoresFeature extends Feature<RandoresFeature.Config> {
    public static final RandoresFeature INSTANCE = (RandoresFeature) new RandoresFeature(Config.CODEC)
            .setRegistryName(Randores.ID, "ore_feature");

    private RandoresSelector selector;

    public RandoresFeature(Codec<RandoresFeature.Config> codec) {
        super(codec);
        this.selector = new RandoresSelector();
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, RandoresFeature.Config config) {
        this.selector.setWorldSeed(reader.getSeed());
        IRandoresDefinition definition = this.selector.generate(pos);
        IRandoresOre ore = definition.getComponent(IRandoresOre.class).get();
        setBlockState(reader, pos, RandoresOreBlock.INSTANCE.getDefaultState().with(IRandoresBlock.HARVERST_LEVEL, ore.definition().getProperty(RandoresHarvestLevel.class).orElse(1)));
        return true;
    }

    public static class Config implements IFeatureConfig {
        public static final Config INSTANCE = new Config();
        public static final Codec<Config> CODEC = Codec.unit(() -> INSTANCE);
    }

}
