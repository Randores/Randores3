package io.github.socraticphoenix.randores.api.definition;

import io.github.socraticphoenix.randores.api.definition.property.IRandoresProperty;
import io.github.socraticphoenix.randores.api.plugin.IRandoresOwned;
import io.github.socraticphoenix.randores.api.random.IRandoresTestable;
import io.github.socraticphoenix.randores.api.registry.IRandoresRegistrable;

import java.util.Random;

public interface IRandoresPropertyGenerator extends IRandoresRegistrable<IRandoresPropertyGenerator>, IRandoresTestable, IRandoresOwned {

    IRandoresProperty<?> generate(Random random, IRandoresDefinition def);

}
