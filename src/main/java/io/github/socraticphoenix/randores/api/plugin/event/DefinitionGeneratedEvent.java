package io.github.socraticphoenix.randores.api.plugin.event;

import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import net.minecraftforge.eventbus.api.Cancelable;
import net.minecraftforge.eventbus.api.Event;

@Cancelable
public class DefinitionGeneratedEvent extends Event {
    private IRandoresDefinition definition;

    public DefinitionGeneratedEvent(IRandoresDefinition definition) {
        this.definition = definition;
    }

    public IRandoresDefinition definition() {
        return this.definition;
    }

}
