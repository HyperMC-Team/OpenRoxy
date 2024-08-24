package net.viamcp.vialoadingbase.platform;

import com.viaversion.viarewind.api.ViaRewindPlatform;
import java.io.File;
import java.util.logging.Logger;
import net.minecraft.client.Minecraft;
import net.minecraft.item.ItemSword;
import net.viamcp.vialoadingbase.ViaLoadingBase;

public class ViaRewindPlatformImpl
implements ViaRewindPlatform {
    public ViaRewindPlatformImpl(File directory) {
        this.init(new File(directory, "viarewind.yml"));
    }

    public Logger getLogger() {
        return ViaLoadingBase.LOGGER;
    }

    public boolean isSword() {
        Minecraft mc = Minecraft.getMinecraft();
        return mc.thePlayer != null && mc.thePlayer.getHeldItem() != null && mc.thePlayer.getHeldItem().getItem() instanceof ItemSword;
    }
}

