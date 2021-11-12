package io.github.socraticphoenix.randores.api.definition;

import io.github.socraticphoenix.randores.api.definition.component.IRandoresComponent;
import io.github.socraticphoenix.randores.api.plugin.IRandoresOwned;
import io.github.socraticphoenix.randores.api.random.IRandoresTestable;
import io.github.socraticphoenix.randores.api.registry.IRandoresRegistrable;

import java.util.Random;

public interface IRandoresComponentGenerator extends IRandoresRegistrable<IRandoresComponentGenerator>, IRandoresTestable, IRandoresOwned {

    IRandoresComponent generate(Random random, IRandoresDefinition def);

}
