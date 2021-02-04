package io.github.socraticphoenix.randores.api;

import io.github.socraticphoenix.randores.api.definition.IRandoresDefinitionGenerator;
import io.github.socraticphoenix.randores.api.registry.IRandoresRegistrable;
import io.github.socraticphoenix.randores.api.registry.IRandoresRegistry;
import io.github.socraticphoenix.randores.mod.RandoresMod;

public interface Randores {
    String ID = "randores";

    static Randores get() {
        return RandoresMod.INSTANCE;
    }

    IRandoresRegistry<IRandoresRegistry<?>> registry();

    <T extends IRandoresRegistrable> IRandoresRegistry<T> registryFor(Class<T> type);

    RandoresConfig config();

}
