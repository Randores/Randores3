package io.github.socraticphoenix.randores.api.plugin.event;

import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.definition.property.IRandoresProperty;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.GenericEvent;

@Cancelable
public class PropertyGeneratedEvent<T> extends GenericEvent<T> {
    private IRandoresProperty<T> property;
    private IRandoresDefinition definition;

    public PropertyGeneratedEvent(IRandoresProperty<T> property, IRandoresDefinition definition) {
        super(property.type());
        this.property = property;
        this.definition = definition;
    }

    public IRandoresProperty<T> property() {
        return property;
    }

    public IRandoresDefinition definition() {
        return definition;
    }

}
