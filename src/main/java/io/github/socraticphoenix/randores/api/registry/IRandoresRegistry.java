package io.github.socraticphoenix.randores.api.registry;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

public interface IRandoresRegistry<T extends IRandoresRegistrable> extends IRandoresRegistrable {

    Class<T> type();

    void register(T... items);

    Optional<T> get(String name);

    Collection<T> getAll();

    default <K extends IRandoresRegistrable> Collection<K> getAllByType(Class<K> type) {
        return (Collection<K>) getAll().stream().filter(type::isInstance).collect(Collectors.toList());
    }

    default <K extends IRandoresRegistrable> Optional<K> getByType(Class<K> type) {
        return (Optional<K>) getAll().stream().filter(type::isInstance).findFirst();
    }

}
