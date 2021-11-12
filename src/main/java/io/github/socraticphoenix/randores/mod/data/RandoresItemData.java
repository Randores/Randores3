package io.github.socraticphoenix.randores.mod.data;

import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.mod.RandoresWorldData;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;

import java.util.Optional;

public class RandoresItemData {

    public static void apply(ItemStack stack, int seed, int index) {
        CompoundNBT nbt = stack.getOrCreateChildTag("randores");
        nbt.putInt("seed", seed);
        nbt.putInt("index", index);
    }

    public Optional<IRandoresDefinition> from(ItemStack stack) {
        CompoundNBT nbt = stack.getChildTag("randores");
        if (nbt != null && nbt.contains("seed") && nbt.contains("index")) {
            RandoresWorldData data = RandoresWorldData.from(nbt.getInt("seed"));
            return data.canGet(nbt.getInt("index")) ? Optional.ofNullable(data.get(nbt.getInt("index"))) : Optional.empty();
        }
        return Optional.empty();
    }

}
