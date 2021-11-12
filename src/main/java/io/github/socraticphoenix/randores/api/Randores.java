package io.github.socraticphoenix.randores.api;

import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.plugin.IRandoresPluginRegistry;
import io.github.socraticphoenix.randores.mod.RandoresMod;

import javax.swing.text.html.Option;

public interface Randores {
    String ID = "randores";

    static Randores get() {
        return RandoresMod.INSTANCE;
    }

    RandoresConfig config();

    IRandoresPluginRegistry plugins();

    IRandoresDefinition definition(int seed, int x, int y, int z);

    IRandoresDefinition definition(int seed, int index);

    IRandoresDefinition[] definitions(int seed);

    int definitionIndex(int seed, IRandoresDefinition definition);

    int randoresSeed(long worldSeed);

}
