package io.github.socraticphoenix.randores.mod.start;

import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class CommonStartup {

    public static void init() {
        FMLJavaModLoadingContext.get().getModEventBus().register(CommonStartup.class);
    }

}
