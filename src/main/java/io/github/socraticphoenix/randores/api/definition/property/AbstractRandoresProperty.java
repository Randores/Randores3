package io.github.socraticphoenix.randores.api.definition.property;

public class AbstractRandoresProperty<T> implements IRandoresProperty<T> {
    private String name;
    private T value;
    private Class<T> type;

    public AbstractRandoresProperty(String name, T value, Class<T> type) {
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

}
