package io.github.socraticphoenix.randores.api.definition;

import io.github.socraticphoenix.randores.api.definition.component.IRandoresComponent;
import io.github.socraticphoenix.randores.api.definition.property.IRandoresProperty;
import io.github.socraticphoenix.randores.api.plugin.IRandoresOwned;
import io.github.socraticphoenix.randores.api.random.IRandoresWeighted;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public interface IRandoresDefinition extends IRandoresWeighted, IRandoresOwned {

    String name();

    Collection<IRandoresComponent> components();

    default void addComponents(IRandoresComponent... components) {
        Collections.addAll(components(), components);
    }

    default <T extends IRandoresComponent> Collection<T> getAllComponents(Class<T> type) {
        return (Set) components().stream().filter(type::isInstance).collect(Collectors.toSet());
    }

    default <T extends IRandoresComponent> Optional<T> getComponent(Class<T> type) {
        return (Optional) components().stream().filter(type::isInstance).findFirst();
    }

    default <T extends IRandoresComponent> boolean hasComponent(Class<T> type) {
        return getComponent(type).isPresent();
    }


    Collection<IRandoresProperty> properties();

    default void addProperties(IRandoresProperty... properties) {
        Collections.addAll(properties(), properties);
    }

    default <T extends IRandoresProperty> Collection<T> getAllProperties(Class<T> type) {
        return (Set) properties().stream().filter(type::isInstance).collect(Collectors.toSet());
    }

    default <K, T extends IRandoresProperty<K>> Optional<K> getProperty(Class<T> type) {
        return (Optional) properties().stream().filter(type::isInstance).findFirst().map(IRandoresProperty::value);
    }

    default <T extends IRandoresProperty> boolean hasProperty(Class<T> type) {
        return getProperty(type).isPresent();
    }

}
