package io.github.socraticphoenix.randores.api.registry;

import net.minecraftforge.registries.IForgeRegistryEntry;

public interface IRandoresRegistrable<T> extends IForgeRegistryEntry<T> {

    String name();

}
