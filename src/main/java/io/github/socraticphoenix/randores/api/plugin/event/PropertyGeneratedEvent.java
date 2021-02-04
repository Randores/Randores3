package io.github.socraticphoenix.randores.api.plugin.event;

import io.github.socraticphoenix.randores.api.definition.property.IRandoresProperty;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.GenericEvent;

@Cancelable
public class PropertyGeneratedEvent<T> extends GenericEvent<T> {
    private IRandoresProperty<T> property;

    public PropertyGeneratedEvent(IRandoresProperty<T> property) {
        super(property.type());
        this.property = property;
    }
}
