package io.github.socraticphoenix.randores.api.definition;

import io.github.socraticphoenix.randores.api.plugin.IRandoresOwned;
import io.github.socraticphoenix.randores.api.random.IRandoresTestable;
import io.github.socraticphoenix.randores.api.registry.IRandoresRegistrable;

import java.util.Random;

public interface IRandoresDefinitionGenerator extends IRandoresRegistrable<IRandoresDefinitionGenerator>, IRandoresTestable, IRandoresOwned {

    IRandoresDefinition generate(Random random);

}
