package io.github.socraticphoenix.randores.mod.plugin.core;

import io.github.socraticphoenix.randores.api.Randores;
import io.github.socraticphoenix.randores.api.definition.IRandoresComponentGenerator;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.definition.component.IRandoresComponent;
import io.github.socraticphoenix.randores.api.plugin.IRandoresPlugin;
import io.github.socraticphoenix.randores.api.registry.AbstractRegistrable;
import io.github.socraticphoenix.randores.mod.definition.RandoresOre;
import net.minecraftforge.common.Tags;

import java.util.Random;

public class DefaultOreGenerator extends AbstractRegistrable<IRandoresComponentGenerator> implements IRandoresComponentGenerator {

    public DefaultOreGenerator() {
        super(Randores.ID, "ore_generator", IRandoresComponentGenerator.class);
    }

    @Override
    public IRandoresComponent generate(Random random, IRandoresDefinition def) {
        return new RandoresOre(owner(), def, (a, b) -> a.isIn(Tags.Blocks.STONE));
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
