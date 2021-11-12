package io.github.socraticphoenix.randores.mod.plugin;

import io.github.socraticphoenix.randores.api.plugin.IRandoresPlugin;
import io.github.socraticphoenix.randores.api.plugin.IRandoresPluginRegistry;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class RandoresPluginRegistry implements IRandoresPluginRegistry {
    private Map<String, IRandoresPlugin> plugins = new LinkedHashMap<>();

    public void register(IRandoresPlugin plugin) {
        if (plugins.containsKey(plugin.id())) {
            throw new IllegalArgumentException("Duplicate plugin id: " + plugin.id());
        }

        plugins.put(plugin.id(), plugin);
    }

    @Override
    public Collection<IRandoresPlugin> getAll() {
        return plugins.values();
    }

    @Override
    public Optional<IRandoresPlugin> get(String id) {
        return Optional.ofNullable(plugins.get(id));
    }
}
