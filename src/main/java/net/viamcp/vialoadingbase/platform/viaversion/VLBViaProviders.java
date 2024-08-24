package net.viamcp.vialoadingbase.platform.viaversion;

import com.viaversion.viaversion.api.Via;
import com.viaversion.viaversion.api.platform.ViaPlatformLoader;
import com.viaversion.viaversion.api.platform.providers.Provider;
import com.viaversion.viaversion.api.platform.providers.ViaProviders;
import com.viaversion.viaversion.api.protocol.version.VersionProvider;
import net.viamcp.vialoadingbase.ViaLoadingBase;
import net.viamcp.vialoadingbase.provider.VLBBaseVersionProvider;

public class VLBViaProviders
implements ViaPlatformLoader {
    public void load() {
        ViaProviders providers = Via.getManager().getProviders();
        providers.use(VersionProvider.class, new VLBBaseVersionProvider());
        if (ViaLoadingBase.getInstance().getProviders() != null) {
            ViaLoadingBase.getInstance().getProviders().accept(providers);
        }
    }

    public void unload() {
    }
}

