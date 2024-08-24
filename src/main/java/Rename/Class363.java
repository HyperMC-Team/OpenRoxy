package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class Class363
implements Packet<INetHandlerPlayServer> {
    public int windowId;

    public Class363() {
    }

    public Class363(int windowId) {
        this.windowId = windowId;
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processCloseWindow(this);
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.windowId = buf.readByte();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.windowId);
    }
}

