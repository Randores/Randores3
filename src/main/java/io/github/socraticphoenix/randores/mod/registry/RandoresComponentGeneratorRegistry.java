package io.github.socraticphoenix.randores.mod.registry;

import io.github.socraticphoenix.randores.api.definition.IRandoresComponentGenerator;
import io.github.socraticphoenix.randores.api.registry.RandoresSimpleRegistry;

public class RandoresComponentGeneratorRegistry extends RandoresSimpleRegistry<IRandoresComponentGenerator> {

    public RandoresComponentGeneratorRegistry() {
        super("componentgenerator", IRandoresComponentGenerator.class);
    }

}
