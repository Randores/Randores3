package io.github.socraticphoenix.randores.mod;

import io.github.socraticphoenix.randores.api.Randores;
import io.github.socraticphoenix.randores.api.RandoresConfig;
import io.github.socraticphoenix.randores.api.definition.IRandoresComponentGenerator;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinition;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinitionGenerator;
import io.github.socraticphoenix.randores.api.definition.IRandoresPropertyGenerator;
import io.github.socraticphoenix.randores.api.plugin.IRandoresPluginRegistry;
import io.github.socraticphoenix.randores.mod.block.RandoresOreBlock;
import io.github.socraticphoenix.randores.mod.data.RandoresTileEntity;
import io.github.socraticphoenix.randores.mod.gen.RandoresFeature;
import io.github.socraticphoenix.randores.mod.gen.RandoresPlacement;
import io.github.socraticphoenix.randores.mod.gen.RandoresSelector;
import io.github.socraticphoenix.randores.mod.listener.RandoresWorldListener;
import io.github.socraticphoenix.randores.mod.plugin.core.DefaultRandoresPlugin;
import io.github.socraticphoenix.randores.mod.plugin.RandoresPluginRegistry;
import io.github.socraticphoenix.randores.mod.start.ClientStartup;
import io.github.socraticphoenix.randores.mod.start.CommonStartup;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryBuilder;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

@Mod(Randores.ID)
public class RandoresMod implements Randores {
    public static RandoresMod INSTANCE;

    private RandoresConfig config = new RandoresConfig();
    private RandoresPluginRegistry registry = new RandoresPluginRegistry();
    private Logger logger = LogManager.getLogger(Randores.ID);

    private RandoresSelector selector = new RandoresSelector();

    public RandoresMod() {
        INSTANCE = this;
        FMLJavaModLoadingContext.get().getModEventBus().register(this);

        registry.register(new DefaultRandoresPlugin());

        CommonStartup.init();
        DistExecutor.safeRunWhenOn(Dist.CLIENT, () -> ClientStartup::init);

        register(
                new RandoresWorldListener()
        );
    }

    public static void register(Object... listeners){
        Arrays.stream(listeners).forEach(MinecraftForge.EVENT_BUS::register);
    }

    @Override
    public RandoresConfig config() {
        return this.config;
    }

    @Override
    public IRandoresPluginRegistry plugins() {
        return this.registry;
    }

    @Override
    public IRandoresDefinition definition(int seed, int x, int y, int z) {
        return selector.generate(seed, x, y, z);
    }

    @Override
    public IRandoresDefinition definition(int seed, int index) {
        return RandoresWorldData.from(seed).get(index);
    }

    @Override
    public IRandoresDefinition[] definitions(int seed) {
        return RandoresWorldData.from(seed).getDefinitions();
    }

    @Override
    public int definitionIndex(int seed, IRandoresDefinition definition) {
        return RandoresWorldData.from(seed).indexOf(definition);
    }

    @Override
    public int randoresSeed(long worldSeed) {
        return RandoresSelector.randoresSeed(worldSeed);
    }


    @SubscribeEvent
    public void onRegistryInit(RegistryEvent.NewRegistry ev) {
        new RegistryBuilder<IRandoresDefinitionGenerator>()
                .setType(IRandoresDefinitionGenerator.class)
                .allowModification()
                .setName(createLoc("definition_generators"))
                .create();

        new RegistryBuilder<IRandoresComponentGenerator>()
                .setType(IRandoresComponentGenerator.class)
                .allowModification()
                .setName(createLoc("component_generators"))
                .create();

        new RegistryBuilder<IRandoresPropertyGenerator>()
                .setType(IRandoresPropertyGenerator.class)
                .allowModification()
                .setName(createLoc("property_generators"))
                .create();
    }

    @SubscribeEvent
    public void onFeatureRegister(RegistryEvent.Register<Feature<?>> ev) {
        ev.getRegistry().register(RandoresFeature.INSTANCE);
    }

    @SubscribeEvent
    public void onPlacementRegister(RegistryEvent.Register<Placement<?>> ev) {
        ev.getRegistry().register(RandoresPlacement.INSTANCE);
    }

    @SubscribeEvent
    public void onTileEnttiyRegister(RegistryEvent.Register<TileEntityType<?>> ev) {
        ev.getRegistry().register(RandoresTileEntity.TYPE.setRegistryName(Randores.ID, "ore"));
    }

    @SubscribeEvent
    public void onBlockRegister(RegistryEvent.Register<Block> ev) {
        ev.getRegistry().register(RandoresOreBlock.INSTANCE.setRegistryName(Randores.ID, "ore"));
    }

    @SubscribeEvent
    public void onItemRegister(RegistryEvent.Register<Item> ev) {
        ev.getRegistry().register(new BlockItem(RandoresOreBlock.INSTANCE, new Item.Properties()).setRegistryName(Randores.ID, "ore"));
    }


    public static String createName(String name) {
        return Randores.ID + ":" + name;
    }

    public static ResourceLocation createLoc(String name) {
        return new ResourceLocation(createName(name));
    }

    public static Logger logger() {
        return INSTANCE.logger;
    }

    public static void info(String... messages) {
        Arrays.stream(messages).forEach(logger()::info);
    }

    public static void error(String... messages) {
        Arrays.stream(messages).forEach(logger()::error);
    }

    public static void error(String message, Throwable throwable) {
        logger().error(message, throwable);
    }

    public static void warn(String... messages) {
        Arrays.stream(messages).forEach(logger()::warn);
    }

}
