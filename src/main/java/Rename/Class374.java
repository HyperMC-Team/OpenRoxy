package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class Class374
implements Packet<INetHandlerPlayClient> {
    private int heldItemHotbarIndex;

    public Class374() {
    }

    public Class374(int hotbarIndexIn) {
        this.heldItemHotbarIndex = hotbarIndexIn;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.heldItemHotbarIndex = buf.readByte();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.heldItemHotbarIndex);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleHeldItemChange(this);
    }

    public int getHeldItemHotbarIndex() {
        return this.heldItemHotbarIndex;
    }
}

