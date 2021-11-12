package io.github.socraticphoenix.randores.mod.plugin.core;

import io.github.socraticphoenix.randores.api.Randores;
import io.github.socraticphoenix.randores.api.definition.IRandoresComponentGenerator;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinitionGenerator;
import io.github.socraticphoenix.randores.api.definition.IRandoresPropertyGenerator;
import io.github.socraticphoenix.randores.api.definition.component.IRandoresComponent;
import io.github.socraticphoenix.randores.api.definition.property.IRandoresProperty;
import io.github.socraticphoenix.randores.api.plugin.IRandoresPlugin;
import io.github.socraticphoenix.randores.api.plugin.event.ComponentGeneratedEvent;
import io.github.socraticphoenix.randores.api.plugin.event.PropertyGeneratedEvent;
import io.github.socraticphoenix.randores.api.registry.AbstractRegistrable;
import io.github.socraticphoenix.randores.mod.definition.RandoresDefinition;
import io.github.socraticphoenix.randores.mod.definition.RandoresNameGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.registry.GameRegistry;

import java.util.Random;

public class DefaultRandoresGenerator extends AbstractRegistrable<IRandoresDefinitionGenerator> implements IRandoresDefinitionGenerator {

    public DefaultRandoresGenerator() {
        super(Randores.ID, "default_generator", IRandoresDefinitionGenerator.class);
    }

    @Override
    public IRandoresDefinition generate(Random random) {
        IRandoresDefinition definition = new RandoresDefinition(owner(), RandoresNameGenerator.name(random.nextInt()), 1);

        GameRegistry.findRegistry(IRandoresComponentGenerator.class).getValues().stream()
                .filter(r -> r.test(new Random(random.nextLong())))
                .forEach(r -> {
                    IRandoresComponent component = r.generate(new Random(random.nextLong()), definition);
                    if (!MinecraftForge.EVENT_BUS.post(new ComponentGeneratedEvent(component, definition))) {
                        definition.addComponents(component);
                    }
                });

        GameRegistry.findRegistry(IRandoresPropertyGenerator.class).getValues().stream()
                .filter(r -> r.test(new Random(random.nextLong())))
                .forEach(r -> {
                    IRandoresProperty<?> property = r.generate(new Random(random.nextLong()), definition);
                    if (!MinecraftForge.EVENT_BUS.post(new PropertyGeneratedEvent<>(property, definition))) {
                        definition.addProperties(property);
                    }
                });

        return definition;
    }

    @Override
    public IRandoresPlugin owner() {
        return DefaultRandoresPlugin.INSTANCE;
    }

    @Override
    public boolean test(Random random) {
        return true;
    }

}
