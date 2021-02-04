package io.github.socraticphoenix.randores.api.registry;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public class RandoresSimpleRegistry<T extends IRandoresRegistrable> implements IRandoresRegistry<T> {
    private Map<String, T> items = new LinkedHashMap<>();
    private String name;
    private Class<T> type;

    public RandoresSimpleRegistry(String name, Class<T> type) {
        this.name = name;
        this.type = type;
    }

    @Override
    public Class<T> type() {
        return this.type;
    }

    @Override
    public void register(T... items) {
        for (T t : items) {
            if (!this.items.containsKey(t.name())) {
                this.items.put(t.name(), t);
            } else {
                throw new IllegalArgumentException("Duplicate key: " + t.name());
            }
        }
    }

    @Override
    public Optional<T> get(String name) {
        return Optional.ofNullable(items.get(name));
    }

    @Override
    public Collection<T> getAll() {
        return items.values();
    }

    @Override
    public String name() {
        return this.name;
    }
}
