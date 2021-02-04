package io.github.socraticphoenix.randores.mod.registry;

import io.github.socraticphoenix.randores.api.definition.IRandoresDefinitionGenerator;
import io.github.socraticphoenix.randores.api.registry.RandoresSimpleRegistry;

public class RandoresDefinitionGeneratorRegistry extends RandoresSimpleRegistry<IRandoresDefinitionGenerator> {

    public RandoresDefinitionGeneratorRegistry() {
        super("definition");
    }

}
