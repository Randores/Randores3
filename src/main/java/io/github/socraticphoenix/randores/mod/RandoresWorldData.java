package io.github.socraticphoenix.randores.mod;

import io.github.socraticphoenix.randores.api.Randores;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinitionGenerator;
import io.github.socraticphoenix.randores.api.random.RandomBuilder;
import io.github.socraticphoenix.randores.api.random.RandoresProbability;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;

import java.util.Random;

public class RandoresWorldData extends WorldSavedData {
    private IRandoresDefinition[] definitions;
    private long seed = 0;

    public RandoresWorldData(String name) {
        super(name);
    }

    public static RandoresWorldData from(ServerWorld world) {
        RandoresWorldData data = world.getSavedData().getOrCreate(() -> new RandoresWorldData("randores:definitions"), "randores:definitions");
        long seed = new Random(world.getSeed()).nextLong();
        data.load(world, seed);
        return data;
    }

    public void load(ServerWorld world, long seed) {
        if (!isLoaded()) {
            this.seed = seed;
            Random random = new Random(seed);
            int count = Randores.get().config().oreCount();
            IRandoresDefinition[] definitions = new IRandoresDefinition[count];

            for (int i = 0; i < count; i++) {
                IRandoresDefinitionGenerator generator = RandoresProbability.randomWeighted(Randores.get().registryFor(IRandoresDefinitionGenerator.class).getAll(), random);
                definitions[i] = generator.generate(new RandomBuilder(new Random(random.nextLong())));
            }

            markDirty();
        }
    }

    public boolean isLoaded() {
        return this.definitions != null;
    }

    @Override
    public void read(CompoundNBT nbt) {
        this.seed = nbt.getLong("seed");
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        nbt.putLong("seed", this.seed);
        return nbt;
    }

}
