package io.github.socraticphoenix.randores.mod.plugin.core;

import io.github.socraticphoenix.randores.api.Randores;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.definition.IRandoresPropertyGenerator;
import io.github.socraticphoenix.randores.api.definition.property.IRandoresProperty;
import io.github.socraticphoenix.randores.api.definition.property.RandoresHarvestLevel;
import io.github.socraticphoenix.randores.api.plugin.IRandoresPlugin;
import io.github.socraticphoenix.randores.api.registry.AbstractRegistrable;

import java.util.Random;

public class DefaultHarvestLevelGenerator extends AbstractRegistrable<IRandoresPropertyGenerator> implements IRandoresPropertyGenerator {

    public DefaultHarvestLevelGenerator() {
        super(Randores.ID, "harvest_level_generator", IRandoresPropertyGenerator.class);
    }

    @Override
    public IRandoresProperty<Integer> generate(Random random, IRandoresDefinition def) {
        return new RandoresHarvestLevel(owner(), random.nextInt(4));
    }

    @Override
    public boolean test(Random random) {
        return true;
    }

    @Override
    public IRandoresPlugin owner() {
        return DefaultRandoresPlugin.INSTANCE;
    }

}
