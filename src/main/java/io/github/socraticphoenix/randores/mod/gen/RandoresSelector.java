package io.github.socraticphoenix.randores.mod.gen;

import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.mod.RandoresWorldData;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.noise.module.Module;
import org.spongepowered.noise.module.modifier.ScaleBias;
import org.spongepowered.noise.module.source.Perlin;

import java.util.Random;

public class RandoresSelector {
    private Perlin perlin;
    private Module noise;
    private RandoresWorldData data;

    private int seed;

    public RandoresSelector() {
         this.perlin = new Perlin();
         this.noise = perlin;
    }

    public synchronized IRandoresDefinition generate(int seed, int x, int y, int z) {
        setRandoresSeed(seed);
        return generate(x, y, z);
    }

    public synchronized IRandoresDefinition generateWorld(long worldSeed, int x, int y, int z) {
        setWorldSeed(worldSeed);
        return generate(x, y, z);
    }

    public void setWorldSeed(long seed) {
        setRandoresSeed(randoresSeed(seed));
    }

    public void setRandoresSeed(int seed) {
        if (this.seed != seed || this.seed == 0) {
            this.seed = seed;
            this.data = RandoresWorldData.from(seed);

            ScaleBias scaleBias = new ScaleBias();
            scaleBias.setSourceModule(0, this.perlin);
            scaleBias.setScale((1 / perlin.getMaxValue()) * data.distributionSize());
            scaleBias.setBias(0);

            this.noise = scaleBias;
        }
    }

    public IRandoresDefinition generate(int x, int y, int z) {
        int index = (int) this.noise.getValue(x, y, z);
        return this.data.select(index);
    }

    public static int randoresSeed(long worldSeed) {
        Random rand = new Random(worldSeed);
        return (int) (rand.nextLong() + rand.nextInt() * 31);
    }

    public IRandoresDefinition generate(BlockPos pos) {
        return generate(pos.getX(), pos.getY(), pos.getZ());
    }
}
