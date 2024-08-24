package Rename;

import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class Class343
implements Packet<INetHandlerPlayServer> {
    private int slotId;
    private ItemStack stack;

    public Class343() {
    }

    public Class343(int slotIdIn, ItemStack stackIn) {
        this.slotId = slotIdIn;
        this.stack = stackIn != null ? stackIn.copy() : null;
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processCreativeInventoryAction(this);
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.slotId = buf.readShort();
        this.stack = buf.readItemStackFromBuffer();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeShort(this.slotId);
        buf.writeItemStackToBuffer(this.stack);
    }

    public int getSlotId() {
        return this.slotId;
    }

    public ItemStack getStack() {
        return this.stack;
    }
}

