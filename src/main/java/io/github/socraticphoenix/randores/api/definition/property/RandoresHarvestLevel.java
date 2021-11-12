package io.github.socraticphoenix.randores.api.definition.property;

import io.github.socraticphoenix.randores.api.plugin.IRandoresPlugin;

public class RandoresHarvestLevel extends AbstractRandoresProperty<Integer> {

    public RandoresHarvestLevel(IRandoresPlugin owner, Integer value) {
        super(owner,"harvest_level", value, Integer.class);
    }

}
