package io.github.socraticphoenix.randores.api.definition.property;

public interface IRandoresProperty<T> {

    String name();

    T value();

    Class<T> type();

}
