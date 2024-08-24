package Rename;

import java.io.IOException;

import Rename.Class267;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class Class391
implements Packet<INetHandlerPlayClient> {
    private int windowId;
    private short actionNumber;
    private boolean field_148893_c;

    public Class391() {
    }

    public Class391(int windowIdIn, short actionNumberIn, boolean p_i45182_3_) {
        this.windowId = windowIdIn;
        this.actionNumber = actionNumberIn;
        this.field_148893_c = p_i45182_3_;
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleConfirmTransaction(this);
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.windowId = buf.readUnsignedByte();
        this.actionNumber = buf.readShort();
        this.field_148893_c = buf.readBoolean();
        if (Class267.getGrimPost() && this.actionNumber < 0) {
            Class267.pingPackets.add(Integer.valueOf(this.actionNumber));
        }
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.windowId);
        buf.writeShort(this.actionNumber);
        buf.writeBoolean(this.field_148893_c);
    }

    public int getWindowId() {
        return this.windowId;
    }

    public short getActionNumber() {
        return this.actionNumber;
    }

    public boolean func_148888_e() {
        return this.field_148893_c;
    }
}

