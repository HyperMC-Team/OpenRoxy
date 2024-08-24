package net.viamcp.viamcp;

import java.io.File;
import net.viamcp.vialoadingbase.ViaLoadingBase;
import net.viamcp.viamcp.gui.AsyncVersionSlider;

public class ViaMCP {
    public static final int NATIVE_VERSION = 47;
    public static ViaMCP INSTANCE;
    private AsyncVersionSlider asyncVersionSlider;

    public static void create() {
        INSTANCE = new ViaMCP();
    }

    public ViaMCP() {
        ViaLoadingBase.ViaLoadingBaseBuilder.create().runDirectory(new File("ViaMCP")).nativeVersion(47).onProtocolReload(comparableProtocolVersion -> {
            if (this.getAsyncVersionSlider() != null) {
                this.getAsyncVersionSlider().setVersion(comparableProtocolVersion.getVersion());
            }
        }).build();
    }

    public void initAsyncSlider() {
        this.initAsyncSlider(5, 5, 110, 20);
    }

    public void initAsyncSlider(int x, int y, int width, int height) {
        this.asyncVersionSlider = new AsyncVersionSlider(-1, x, y, Math.max(width, 110), height);
    }

    public AsyncVersionSlider getAsyncVersionSlider() {
        return this.asyncVersionSlider;
    }
}

