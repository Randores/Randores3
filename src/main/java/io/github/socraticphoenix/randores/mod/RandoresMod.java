package io.github.socraticphoenix.randores.mod;

import io.github.socraticphoenix.randores.api.Randores;
import io.github.socraticphoenix.randores.api.RandoresConfig;
import io.github.socraticphoenix.randores.api.definition.IRandoresDefinitionGenerator;
import io.github.socraticphoenix.randores.api.plugin.IRandoresPlugin;
import io.github.socraticphoenix.randores.api.registry.IRandoresRegistrable;
import io.github.socraticphoenix.randores.api.registry.IRandoresRegistry;
import io.github.socraticphoenix.randores.api.registry.RandoresSimpleRegistry;
import io.github.socraticphoenix.randores.mod.registry.RandoresComponentGeneratorRegistry;
import io.github.socraticphoenix.randores.mod.registry.RandoresDefinitionGeneratorRegistry;
import io.github.socraticphoenix.randores.mod.registry.RandoresPropertyGeneratorRegistry;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

@Mod(Randores.ID)
public class RandoresMod implements IRandoresPlugin, Randores {
    public static RandoresMod INSTANCE;

    private RandoresConfig config = new RandoresConfig();
    private Logger logger = LogManager.getLogger(Randores.ID);
    private IRandoresRegistry<IRandoresRegistry<?>> registry;

    public RandoresMod() {
        INSTANCE = this;
        this.registry = new RandoresSimpleRegistry("registry", IRandoresRegistry.class);
        this.registry.register(
                new RandoresDefinitionGeneratorRegistry(),
                new RandoresComponentGeneratorRegistry(),
                new RandoresPropertyGeneratorRegistry()
        );
    }

    @Override
    public String id() {
        return "randores";
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

    @Override
    public IRandoresRegistry<IRandoresRegistry<?>> registry() {
        return null;
    }

    @Override
    public <T extends IRandoresRegistrable> IRandoresRegistry<T> registryFor(Class<T> type) {
        return (IRandoresRegistry<T>) this.registry().getAll().stream().filter(c -> type.isAssignableFrom(c.type())).findFirst().orElse(null);
    }

    @Override
    public RandoresConfig config() {
        return this.config;
    }

}
