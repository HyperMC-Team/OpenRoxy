package net.viamcp.vialoadingbase.command.impl;

import com.viaversion.viaversion.api.command.ViaCommandSender;
import com.viaversion.viaversion.api.command.ViaSubCommand;
import io.netty.util.ResourceLeakDetector;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class LeakDetectSubCommand
extends ViaSubCommand {
    public String name() {
        return "leakdetect";
    }

    public String description() {
        return "Sets ResourceLeakDetector level";
    }

    public boolean execute(ViaCommandSender viaCommandSender, String[] strings) {
        if (strings.length == 1) {
            try {
                ResourceLeakDetector.Level level = ResourceLeakDetector.Level.valueOf((String)strings[0]);
                ResourceLeakDetector.setLevel((ResourceLeakDetector.Level)level);
                viaCommandSender.sendMessage("Set leak detector level to " + String.valueOf(level));
            }
            catch (IllegalArgumentException e) {
                viaCommandSender.sendMessage("Invalid level (" + Arrays.toString(ResourceLeakDetector.Level.values()) + ")");
            }
        } else {
            viaCommandSender.sendMessage("Current leak detection level is " + String.valueOf(ResourceLeakDetector.getLevel()));
        }
        return true;
    }

    public List<String> onTabComplete(ViaCommandSender sender, String[] args) {
        if (args.length == 1) {
            return Arrays.stream(ResourceLeakDetector.Level.values()).map(Enum::name).filter(it -> it.startsWith(args[0])).collect(Collectors.toList());
        }
        return super.onTabComplete(sender, args);
    }
}

