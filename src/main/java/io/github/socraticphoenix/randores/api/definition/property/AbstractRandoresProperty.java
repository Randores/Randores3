package io.github.socraticphoenix.randores.api.definition.property;

import io.github.socraticphoenix.randores.api.plugin.IRandoresPlugin;

public abstract class AbstractRandoresProperty<T> implements IRandoresProperty<T> {
    private IRandoresPlugin owner;
    private String name;
    private T value;
    private Class<T> type;

    public AbstractRandoresProperty(IRandoresPlugin owner, String name, T value, Class<T> type) {
        this.owner = owner;
        this.name = name;
        this.value = value;
        this.type = type;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public T value() {
        return this.value;
    }

    @Override
    public Class<T> type() {
        return this.type;
    }

    @Override
    public IRandoresPlugin owner() {
        return this.owner;
    }
}
