package net.viamcp.vialoadingbase.provider;

import com.viaversion.viaversion.api.connection.UserConnection;
import com.viaversion.viaversion.protocols.base.BaseVersionProvider;
import net.viamcp.vialoadingbase.ViaLoadingBase;

public class VLBBaseVersionProvider
extends BaseVersionProvider {
    public int getClosestServerProtocol(UserConnection connection) throws Exception {
        if (connection.isClientSide()) {
            return ViaLoadingBase.getInstance().getTargetVersion().getVersion();
        }
        return super.getClosestServerProtocol(connection);
    }
}

