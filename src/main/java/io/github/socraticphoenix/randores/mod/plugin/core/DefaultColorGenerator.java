package io.github.socraticphoenix.randores.mod.plugin.core;

import io.github.socraticphoenix.randores.api.Randores;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.definition.IRandoresPropertyGenerator;
import io.github.socraticphoenix.randores.api.definition.property.IRandoresProperty;
import io.github.socraticphoenix.randores.api.definition.property.RandoresRenderColor;
import io.github.socraticphoenix.randores.api.plugin.IRandoresPlugin;
import io.github.socraticphoenix.randores.api.registry.AbstractRegistrable;

import java.awt.Color;
import java.util.Random;

public class DefaultColorGenerator extends AbstractRegistrable<IRandoresPropertyGenerator> implements IRandoresPropertyGenerator {

    public DefaultColorGenerator() {
        super(Randores.ID, "color_generator", IRandoresPropertyGenerator.class);
    }


    @Override
    public IRandoresProperty<Color> generate(Random random, IRandoresDefinition def) {
        return new RandoresRenderColor(owner(), new Color(random.nextInt()));
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
