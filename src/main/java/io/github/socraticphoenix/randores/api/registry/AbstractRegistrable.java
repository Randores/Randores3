package io.github.socraticphoenix.randores.api.registry;

import net.minecraft.util.ResourceLocation;

import javax.annotation.Nullable;

public class AbstractRegistrable<T extends IRandoresRegistrable<T>> implements IRandoresRegistrable<T> {
    private ResourceLocation name;
    private Class<T> type;

    public AbstractRegistrable(ResourceLocation name, Class<T> type) {
        this.name = name;
        this.type = type == null ? (Class<T>) getClass() : type;
    }

    public AbstractRegistrable(String modid, String name, Class<T> type) {
        this(new ResourceLocation(modid, name), type);
    }

    public AbstractRegistrable(String modid, String name) {
        this(modid, name, null);
    }

    @Override
    public String name() {
        return this.name.toString();
    }

    @Override
    public T setRegistryName(ResourceLocation name) {
        this.name = name;
        return (T) this;
    }

    @Nullable
    @Override
    public ResourceLocation getRegistryName() {
        return this.name;
    }

    @Override
    public Class<T> getRegistryType() {
        return this.type;
    }
}
