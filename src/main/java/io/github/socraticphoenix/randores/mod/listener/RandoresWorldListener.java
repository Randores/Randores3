package io.github.socraticphoenix.randores.mod.listener;

import io.github.socraticphoenix.randores.mod.RandoresMod;
import io.github.socraticphoenix.randores.mod.RandoresWorldData;
import io.github.socraticphoenix.randores.mod.gen.RandoresFeature;
import io.github.socraticphoenix.randores.mod.gen.RandoresPlacement;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class RandoresWorldListener {

    @SubscribeEvent
    public void onLoad(WorldEvent.Load ev) {
        if (ev.getWorld() instanceof ServerWorld) {
            RandoresWorldData.from((ServerWorld) ev.getWorld());
            RandoresMod.info("Loaded randores data for world " + ev.getWorld() + " - " + ((ServerWorld) ev.getWorld()).getDimensionType().getEffects());
        }
    }

    @SubscribeEvent
    public void onBiomeLoad(BiomeLoadingEvent ev) {
        ev.getGeneration().withFeature(GenerationStage.Decoration.UNDERGROUND_ORES,
                RandoresFeature.INSTANCE.withConfiguration(RandoresFeature.Config.INSTANCE)
                .withPlacement(RandoresPlacement.INSTANCE.configure(RandoresPlacement.Config.INSTANCE)));
    }

}
