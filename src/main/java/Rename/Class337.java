package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class Class337
implements Packet<INetHandlerPlayServer> {
    private EnumState status;

    public Class337() {
    }

    public Class337(EnumState statusIn) {
        this.status = statusIn;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.status = buf.readEnumValue(EnumState.class);
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeEnumValue(this.status);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processClientStatus(this);
    }

    public EnumState getStatus() {
        return this.status;
    }

    public static enum EnumState {
        PERFORM_RESPAWN,
        REQUEST_STATS,
        OPEN_INVENTORY_ACHIEVEMENT;

    }
}

