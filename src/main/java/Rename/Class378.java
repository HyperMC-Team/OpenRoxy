package Rename;

import java.io.IOException;
import net.minecraft.item.ItemStack;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class Class378
implements Packet<INetHandlerPlayClient> {
    private int windowId;
    private int slot;
    private ItemStack item;

    public Class378() {
    }

    public Class378(int windowIdIn, int slotIn, ItemStack itemIn) {
        this.windowId = windowIdIn;
        this.slot = slotIn;
        this.item = itemIn == null ? null : itemIn.copy();
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleSetSlot(this);
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.windowId = buf.readByte();
        this.slot = buf.readShort();
        this.item = buf.readItemStackFromBuffer();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.windowId);
        buf.writeShort(this.slot);
        buf.writeItemStackToBuffer(this.item);
    }

    public int getSlot() {
        return this.slot;
    }

    public int getWindowId() {
        return this.windowId;
    }

    public ItemStack getItem() {
        return this.item;
    }
}

