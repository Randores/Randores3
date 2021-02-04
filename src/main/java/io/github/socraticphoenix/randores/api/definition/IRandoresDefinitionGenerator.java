package io.github.socraticphoenix.randores.api.definition;

import io.github.socraticphoenix.randores.api.registry.IRandoresRegistrable;
import io.github.socraticphoenix.randores.api.random.IRandoresWeighted;
import io.github.socraticphoenix.randores.api.random.RandomBuilder;

public interface IRandoresDefinitionGenerator extends IRandoresRegistrable, IRandoresWeighted {

    IRandoresDefinition generate(RandomBuilder random);

}
