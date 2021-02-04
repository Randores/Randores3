package io.github.socraticphoenix.randores.api.plugin.event;

import io.github.socraticphoenix.randores.api.definition.component.IRandoresComponent;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class ComponentGeneratedEvent extends Event {
    private IRandoresComponent component;

    public ComponentGeneratedEvent(IRandoresComponent component) {
        this.component = component;
    }

    public IRandoresComponent component() {
        return this.component;
    }

}
