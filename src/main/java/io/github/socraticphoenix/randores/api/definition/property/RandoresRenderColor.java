package io.github.socraticphoenix.randores.api.definition.property;

import io.github.socraticphoenix.randores.api.plugin.IRandoresPlugin;

import java.awt.Color;

public class RandoresRenderColor extends AbstractRandoresProperty<Color> {

    public RandoresRenderColor(IRandoresPlugin owner, Color value) {
        super(owner,"color", value, Color.class);
    }

}
