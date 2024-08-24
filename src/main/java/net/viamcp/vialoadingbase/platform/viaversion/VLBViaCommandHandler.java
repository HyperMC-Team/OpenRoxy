package net.viamcp.vialoadingbase.platform.viaversion;

import com.viaversion.viaversion.commands.ViaCommandHandler;
import net.viamcp.vialoadingbase.command.impl.LeakDetectSubCommand;

public class VLBViaCommandHandler
extends ViaCommandHandler {
    public VLBViaCommandHandler() {
        this.registerVLBDefaults();
    }

    public void registerVLBDefaults() {
        this.registerSubCommand(new LeakDetectSubCommand());
    }
}

