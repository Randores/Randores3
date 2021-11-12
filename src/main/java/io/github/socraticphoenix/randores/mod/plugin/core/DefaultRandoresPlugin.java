package io.github.socraticphoenix.randores.mod.plugin.core;

import io.github.socraticphoenix.randores.api.definition.IRandoresComponentGenerator;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinitionGenerator;
import io.github.socraticphoenix.randores.api.definition.IRandoresPropertyGenerator;
import io.github.socraticphoenix.randores.api.plugin.IRandoresPlugin;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class DefaultRandoresPlugin implements IRandoresPlugin {
    public static IRandoresPlugin INSTANCE;

    public DefaultRandoresPlugin() {
        INSTANCE = this;
        FMLJavaModLoadingContext.get().getModEventBus().register(this);
    }

    @SubscribeEvent
    public void onGeneratorRegister(RegistryEvent.Register<IRandoresDefinitionGenerator> ev) {
        ev.getRegistry().register(new DefaultRandoresGenerator());
    }

    @SubscribeEvent
    public void onComponentGeneratorRegister(RegistryEvent.Register<IRandoresComponentGenerator> ev) {
        ev.getRegistry().register(new DefaultOreGenerator());
    }

    @SubscribeEvent
    public void onPropertyGeneratorRegister(RegistryEvent.Register<IRandoresPropertyGenerator> ev) {

    }

    @Override
    public String id() {
        return "randores_default";
    }

}
