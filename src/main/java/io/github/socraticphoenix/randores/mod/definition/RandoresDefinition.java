package io.github.socraticphoenix.randores.mod.definition;

import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.definition.component.IRandoresComponent;
import io.github.socraticphoenix.randores.api.definition.property.IRandoresProperty;
import io.github.socraticphoenix.randores.api.plugin.IRandoresPlugin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class RandoresDefinition implements IRandoresDefinition {
    private List<IRandoresComponent> components = new ArrayList<>();
    private List<IRandoresProperty> properties = new ArrayList<>();

    private IRandoresPlugin owner;
    private String name;
    private int weight;

    public RandoresDefinition(IRandoresPlugin owner, String name, int weight) {
        this.owner = owner;
        this.name = name;
        this.weight = weight;
    }

    @Override
    public Collection<IRandoresComponent> components() {
        return this.components;
    }

    @Override
    public Collection<IRandoresProperty> properties() {
        return this.properties;
    }

    @Override
    public String name() {
        return this.name;
    }

    @Override
    public int weight() {
        return this.weight;
    }

    @Override
    public IRandoresPlugin owner() {
        return this.owner;
    }
}
