package io.github.socraticphoenix.randores.api.definition.property;

import java.awt.Color;

public class RandoresColorProperty extends AbstractRandoresProperty<Color> {

    public RandoresColorProperty(Color value) {
        super("color", value, Color.class);
    }

}
