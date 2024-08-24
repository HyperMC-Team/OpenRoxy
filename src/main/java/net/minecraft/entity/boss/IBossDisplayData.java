package net.minecraft.entity.boss;

import net.minecraft.util.IChatComponent;

public interface IBossDisplayData {
    public float getMaxHealth();

    public float getHealth();

    public IChatComponent getDisplayName();
}

