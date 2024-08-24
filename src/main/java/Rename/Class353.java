package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class Class353
implements Packet<INetHandlerPlayServer> {
    private int windowId;
    private short uid;
    private boolean accepted;

    public Class353() {
    }

    public Class353(int windowId, short uid, boolean accepted) {
        this.windowId = windowId;
        this.uid = uid;
        this.accepted = accepted;
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processConfirmTransaction(this);
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.windowId = buf.readByte();
        this.uid = buf.readShort();
        this.accepted = buf.readByte() != 0;
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.windowId);
        buf.writeShort(this.uid);
        buf.writeByte(this.accepted ? 1 : 0);
    }

    public int getWindowId() {
        return this.windowId;
    }

    public short getUid() {
        return this.uid;
    }
}

