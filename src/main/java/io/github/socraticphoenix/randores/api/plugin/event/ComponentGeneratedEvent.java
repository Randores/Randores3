package io.github.socraticphoenix.randores.api.plugin.event;

import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.definition.component.IRandoresComponent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class ComponentGeneratedEvent extends Event {
    private IRandoresComponent component;
    private IRandoresDefinition definition;

    public ComponentGeneratedEvent(IRandoresComponent component, IRandoresDefinition definition) {
        this.component = component;
        this.definition = definition;
    }

    public IRandoresComponent component() {
        return this.component;
    }

    public IRandoresDefinition definition() {
        return this.definition;
    }

}
