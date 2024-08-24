package net.viamcp.vialoadingbase.platform;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.ViaAPI;
import com.viaversion.viaversion.api.command.ViaCommandSender;
import com.viaversion.viaversion.api.configuration.ViaVersionConfig;
import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.api.platform.UnsupportedSoftware;
import com.viaversion.viaversion.api.platform.ViaPlatform;
import com.viaversion.viaversion.api.protocol.version.ProtocolVersion;
import com.viaversion.viaversion.libs.gson.JsonObject;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import net.viamcp.vialoadingbase.ViaLoadingBase;
import net.viamcp.vialoadingbase.platform.viaversion.VLBViaAPIWrapper;
import net.viamcp.vialoadingbase.platform.viaversion.VLBViaConfig;
import net.viamcp.vialoadingbase.util.VLBTask;

public class ViaVersionPlatformImpl implements ViaPlatform<UUID> {
    private final ViaAPI<UUID> api = new VLBViaAPIWrapper();
    private final Logger logger;
    private final VLBViaConfig config;

    public ViaVersionPlatformImpl(Logger logger) {
        this.logger = logger;
        this.config = new VLBViaConfig(new File(ViaLoadingBase.getInstance().getRunDirectory(), "viaversion.yml"));
    }

    public static List<ProtocolVersion> createVersionList() {
        List<ProtocolVersion> versions = (List)new ArrayList(ProtocolVersion.getProtocols())
                .stream()
                .filter(protocolVersion -> protocolVersion != ProtocolVersion.unknown && ProtocolVersion.getProtocols().indexOf(protocolVersion) >= 7)
                .collect(Collectors.toList());
        Collections.reverse(versions);
        return versions;
    }

    public ViaCommandSender[] getOnlinePlayers() {
        return new ViaCommandSender[0];
    }

    public void sendMessage(UUID uuid, String msg) {
        if (uuid == null) {
            this.getLogger().info(msg);
        } else {
            this.getLogger().info("[" + uuid + "] " + msg);
        }
    }

    public boolean kickPlayer(UUID uuid, String s) {
        return false;
    }

    public boolean disconnect(UserConnection connection, String message) {
        return ViaPlatform.super.disconnect(connection, message);
    }

    public VLBTask runAsync(Runnable runnable) {
        return new VLBTask(Via.getManager().getScheduler().execute(runnable));
    }

    public VLBTask runRepeatingAsync(Runnable runnable, long ticks) {
        return new VLBTask(Via.getManager().getScheduler().scheduleRepeating(runnable, 0L, ticks * 50L, TimeUnit.MILLISECONDS));
    }

    public VLBTask runSync(Runnable runnable) {
        return this.runAsync(runnable);
    }

    public VLBTask runSync(Runnable runnable, long ticks) {
        return new VLBTask(Via.getManager().getScheduler().schedule(runnable, ticks * 50L, TimeUnit.MILLISECONDS));
    }

    public VLBTask runRepeatingSync(Runnable runnable, long ticks) {
        return this.runRepeatingAsync(runnable, ticks);
    }

    public boolean isProxy() {
        return true;
    }

    public void onReload() {
    }

    public Logger getLogger() {
        return this.logger;
    }

    public ViaVersionConfig getConf() {
        return this.config;
    }

    public ViaAPI<UUID> getApi() {
        return this.api;
    }

    public File getDataFolder() {
        return ViaLoadingBase.getInstance().getRunDirectory();
    }

    public String getPluginVersion() {
        return "4.9.1";
    }

    public String getPlatformName() {
        return "ViaLoadingBase by FlorianMichael";
    }

    public String getPlatformVersion() {
        return "${vialoadingbase_version}";
    }

    public boolean isPluginEnabled() {
        return true;
    }

    public VLBViaConfig getConfig() {
        return this.config;
    }

    public Collection<UnsupportedSoftware> getUnsupportedSoftwareClasses() {
        return ViaPlatform.super.getUnsupportedSoftwareClasses();
    }

    public boolean hasPlugin(String s) {
        return false;
    }

    public JsonObject getDump() {
        return ViaLoadingBase.getInstance().getDumpSupplier() == null ? new JsonObject() : (JsonObject)ViaLoadingBase.getInstance().getDumpSupplier().get();
    }
}
