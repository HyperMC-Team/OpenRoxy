package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class Class333
implements Packet<INetHandlerPlayServer> {
    private int slotId;

    public Class333() {
    }

    public Class333(int slotId) {
        this.slotId = slotId;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.slotId = buf.readShort();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeShort(this.slotId);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processHeldItemChange(this);
    }

    public int getSlotId() {
        return this.slotId;
    }
}

