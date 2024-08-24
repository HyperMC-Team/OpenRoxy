package net.viamcp.vialoadingbase.platform.viaversion;

import com.viaversion.viaversion.configuration.AbstractViaConfig;
import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class VLBViaConfig
extends AbstractViaConfig {
    private static final List<String> UNSUPPORTED = Arrays.asList("anti-xray-patch", "bungee-ping-interval", "bungee-ping-save", "bungee-servers", "quick-move-action-fix", "nms-player-ticking", "velocity-ping-interval", "velocity-ping-save", "velocity-servers", "blockconnection-method", "change-1_9-hitbox", "change-1_14-hitbox", "show-shield-when-sword-in-hand", "left-handed-handling");

    public VLBViaConfig(File configFile) {
        super(configFile);
        this.reload();
    }

    public URL getDefaultConfigURL() {
        return ((Object)((Object)this)).getClass().getClassLoader().getResource("assets/viaversion/config.yml");
    }

    protected void handleConfig(Map<String, Object> config) {
    }

    public List<String> getUnsupportedOptions() {
        return UNSUPPORTED;
    }
}

