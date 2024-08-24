package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.ScaledResolution;

public class Class38
implements Class7 {
    private float partialTicks;
    private final ScaledResolution resolution;

    public ScaledResolution getScaledResolution() {
        return new ScaledResolution(Minecraft.getMinecraft());
    }

    public Class38(float partialTicks, ScaledResolution resolution) {
        this.partialTicks = partialTicks;
        this.resolution = resolution;
    }

    public float getPartialTicks() {
        return this.partialTicks;
    }

    public ScaledResolution getResolution() {
        return this.resolution;
    }

    public void setPartialTicks(float partialTicks) {
        this.partialTicks = partialTicks;
    }
}

