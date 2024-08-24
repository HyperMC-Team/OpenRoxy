package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class Class340
implements Packet<INetHandlerPlayClient> {
    private int windowId;

    public Class340() {
    }

    public Class340(int windowIdIn) {
        this.windowId = windowIdIn;
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleCloseWindow(this);
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.windowId = buf.readUnsignedByte();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.windowId);
    }
}

