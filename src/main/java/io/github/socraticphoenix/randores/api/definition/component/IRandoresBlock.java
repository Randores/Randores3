package io.github.socraticphoenix.randores.api.definition.component;

import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.Property;

public interface IRandoresBlock extends IRandoresComponent {
    Property<Boolean> TILED = BooleanProperty.create("tile_entity");
    Property<Integer> HARVERST_LEVEL = IntegerProperty.create("harvest_level", 0, 4);

}
