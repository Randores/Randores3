package io.github.socraticphoenix.randores.mod.registry;

import io.github.socraticphoenix.randores.api.definition.IRandoresPropertyGenerator;
import io.github.socraticphoenix.randores.api.registry.RandoresSimpleRegistry;

public class RandoresPropertyGeneratorRegistry extends RandoresSimpleRegistry<IRandoresPropertyGenerator> {

    public RandoresPropertyGeneratorRegistry() {
        super("propertygenerator", IRandoresPropertyGenerator.class);
    }

}
