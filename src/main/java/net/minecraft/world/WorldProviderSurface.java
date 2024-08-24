package net.minecraft.world;

import net.minecraft.world.WorldProvider;

public class WorldProviderSurface
extends WorldProvider {
    @Override
    public String getDimensionName() {
        return "Overworld";
    }

    @Override
    public String getInternalNameSuffix() {
        return "";
    }
}

