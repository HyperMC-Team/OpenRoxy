package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class Class336
implements Packet<INetHandlerPlayServer> {
    private int windowId;
    private int button;

    public Class336() {
    }

    public Class336(int windowId, int button) {
        this.windowId = windowId;
        this.button = button;
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processEnchantItem(this);
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.windowId = buf.readByte();
        this.button = buf.readByte();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.windowId);
        buf.writeByte(this.button);
    }

    public int getWindowId() {
        return this.windowId;
    }

    public int getButton() {
        return this.button;
    }
}

