package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;

public class Class365
implements Packet<INetHandlerPlayServer> {
    private BlockPos position;
    private EnumFacing facing;
    private boolean half = false;
    private Action status;

    public Class365() {
    }

    public Class365(Action statusIn, BlockPos posIn, EnumFacing facingIn) {
        this.status = statusIn;
        this.position = posIn;
        this.facing = facingIn;
    }

    public Class365(Action statusIn) {
        this.status = statusIn;
        this.half = true;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.status = buf.readEnumValue(Action.class);
        this.position = buf.readBlockPos();
        this.facing = EnumFacing.getFront(buf.readUnsignedByte());
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeEnumValue(this.status);
        if (this.half) {
            return;
        }
        buf.writeBlockPos(this.position);
        buf.writeByte(this.facing.getIndex());
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processPlayerDigging(this);
    }

    public BlockPos getPosition() {
        return this.position;
    }

    public EnumFacing getFacing() {
        return this.facing;
    }

    public boolean isHalf() {
        return this.half;
    }

    public Action getStatus() {
        return this.status;
    }

    public static enum Action {
        START_DESTROY_BLOCK,
        ABORT_DESTROY_BLOCK,
        STOP_DESTROY_BLOCK,
        DROP_ALL_ITEMS,
        DROP_ITEM,
        RELEASE_USE_ITEM;

    }
}

