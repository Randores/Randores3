package io.github.socraticphoenix.randores.api.definition.property;

import io.github.socraticphoenix.randores.api.plugin.IRandoresPlugin;

public class RandoresSmeltable extends AbstractRandoresProperty<Integer> {

    public RandoresSmeltable(IRandoresPlugin owner, int value) {
        super(owner, "smeltable", value, Integer.class);
    }

}
