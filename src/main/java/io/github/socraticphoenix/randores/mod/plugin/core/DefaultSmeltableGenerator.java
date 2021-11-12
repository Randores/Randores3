package io.github.socraticphoenix.randores.mod.plugin.core;

import io.github.socraticphoenix.randores.api.Randores;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.definition.IRandoresPropertyGenerator;
import io.github.socraticphoenix.randores.api.definition.property.IRandoresProperty;
import io.github.socraticphoenix.randores.api.definition.property.RandoresSmeltable;
import io.github.socraticphoenix.randores.api.plugin.IRandoresPlugin;
import io.github.socraticphoenix.randores.api.random.RandoresProbability;
import io.github.socraticphoenix.randores.api.registry.AbstractRegistrable;

import java.util.Random;

public class DefaultSmeltableGenerator extends AbstractRegistrable<IRandoresPropertyGenerator> implements IRandoresPropertyGenerator {

    public DefaultSmeltableGenerator() {
        super(Randores.ID, "smeltable_generator", IRandoresPropertyGenerator.class);
    }

    @Override
    public IRandoresProperty<Integer> generate(Random random, IRandoresDefinition def) {
        return new RandoresSmeltable(owner(), random.nextInt(256) + 1);
    }

    @Override
    public boolean test(Random random) {
        return RandoresProbability.percentChance(.2, random);
    }

    @Override
    public IRandoresPlugin owner() {
        return DefaultRandoresPlugin.INSTANCE;
    }

}
