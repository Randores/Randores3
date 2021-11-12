package io.github.socraticphoenix.randores.api.definition.component;

import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.plugin.IRandoresOwned;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.Optional;

public interface IRandoresComponent extends IRandoresOwned {

    IRandoresDefinition definition();

}
