package io.github.socraticphoenix.randores.mod.start;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientStartup {

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().register(ClientStartup.class);
    }

}
