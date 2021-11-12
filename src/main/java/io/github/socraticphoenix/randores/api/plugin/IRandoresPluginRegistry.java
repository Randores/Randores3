package io.github.socraticphoenix.randores.api.plugin;

import java.util.Collection;
import java.util.Optional;

public interface IRandoresPluginRegistry {

    Collection<IRandoresPlugin> getAll();

    Optional<IRandoresPlugin> get(String id);

    void register(IRandoresPlugin plugin);

}
