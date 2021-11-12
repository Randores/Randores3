package io.github.socraticphoenix.randores.mod;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import io.github.socraticphoenix.randores.api.Randores;
import io.github.socraticphoenix.randores.api.definition.IRandoresComponentGenerator;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinitionGenerator;
import io.github.socraticphoenix.randores.api.plugin.event.DefinitionGeneratedEvent;
import io.github.socraticphoenix.randores.api.random.IRandoresWeighted;
import io.github.socraticphoenix.randores.api.random.RandomBuilder;
import io.github.socraticphoenix.randores.api.random.RandoresProbability;
import io.github.socraticphoenix.randores.mod.gen.RandoresSelector;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.storage.WorldSavedData;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

public class RandoresWorldData extends WorldSavedData {
    private static Cache<Integer, RandoresWorldData> cache = CacheBuilder.newBuilder()
            .initialCapacity(2)
            .maximumSize(10)
            .build();

    private IRandoresDefinition[] definitions;
    private int[] distribution;

    private int seed = 0;

    public RandoresWorldData(String name) {
        super(name);
    }

    public IRandoresDefinition[] getDefinitions() {
        return definitions;
    }

    public static RandoresWorldData from(ServerWorld world) {
        return world.getSavedData().getOrCreate(() -> from(RandoresSelector.randoresSeed(world.getSeed())), "randores:definitions");
    }

    public static RandoresWorldData from(int randoresSeed) {
        try {
            return cache.get(randoresSeed, () -> {
                RandoresWorldData data = new RandoresWorldData("randores:definitions");
                data.load(randoresSeed);
                return data;
            });
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    public int distributionSize() {
        return this.distribution.length;
    }

    public boolean canSelect(int index) {
        return index >= 0 && index < this.distribution.length;
    }

    public IRandoresDefinition select(int index) {
        return this.definitions[this.distribution[index]];
    }

    public boolean canGet(int defIndex) {
        return defIndex >= 0 && defIndex < this.definitions.length;
    }

    public IRandoresDefinition get(int defIndex) {
        return this.definitions[defIndex];
    }

    public int indexOf(IRandoresDefinition d) {
        for (int i = 0; i < this.definitions.length; i++) {
            if (d.equals(this.definitions[i])) {
                return i;
            }
        }
        return -1;
    }

    private void load(int seed) {
        if (!isLoaded()) {
            this.seed = seed;
            RandoresMod.info("Generating randores definition for seed " + seed);
            Random random = new Random(seed);
            int count = Randores.get().config().oreCount();
            IRandoresDefinition[] definitions = new IRandoresDefinition[count];
            for (int i = 0, tries = 0; i < count; tries++) {
                IRandoresDefinitionGenerator generator = RandoresProbability.randomWeighted(GameRegistry.findRegistry(IRandoresDefinitionGenerator.class).getValues(), random);
                if (generator != null) {
                    IRandoresDefinition def = generator.generate(new Random(random.nextLong()));

                    if (!MinecraftForge.EVENT_BUS.post(new DefinitionGeneratedEvent(def))) {
                        definitions[i] = def;
                        i++;
                    }
                }

                if (tries >= Randores.get().config().definitionThreshold()) {
                    RandoresMod.warn("Cancelled definition generation early. (Total required: " + count + ", total generated: " + i + ", tries: " + (Randores.get().config().definitionThreshold()) + ")");
                    break;
                }
            }
            this.definitions = definitions;

            int distSize = Stream.of(definitions).mapToInt(d -> d == null ? 0 : d.weight()).sum();
            int[] distribution = new int[distSize];
            int index = 0;
            for (int i = 0; i < definitions.length; i++) {
                IRandoresDefinition def = definitions[i];
                if (def != null) {
                    for (int j = 0; j < def.weight(); j++) {
                        distribution[index++] = i;
                    }
                }
            }

            this.distribution = distribution;
            RandoresMod.info("Finished definition generation");
            markDirty();
        }
    }

    public boolean isLoaded() {
        return this.definitions != null;
    }

    @Override
    public void read(CompoundNBT nbt) {
        this.seed = nbt.getInt("seed");
    }

    @Override
    public CompoundNBT write(CompoundNBT nbt) {
        nbt.putInt("seed", this.seed);
        return nbt;
    }

}
