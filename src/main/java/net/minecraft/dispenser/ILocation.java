package net.minecraft.dispenser;

import net.minecraft.dispenser.IPosition;
import net.minecraft.world.World;

public interface ILocation
extends IPosition {
    public World getWorld();
}

