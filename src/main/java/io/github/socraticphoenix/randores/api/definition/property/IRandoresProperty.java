package io.github.socraticphoenix.randores.api.definition.property;

import io.github.socraticphoenix.randores.api.plugin.IRandoresOwned;

public interface IRandoresProperty<T> extends IRandoresOwned {

    String name();

    T value();

    Class<T> type();

}
