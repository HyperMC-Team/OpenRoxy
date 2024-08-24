package Rename;

import java.io.IOException;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class Class385
implements Packet<INetHandlerPlayClient> {
    private BlockPos blockPos;
    private int metadata;
    private NBTTagCompound nbt;

    public Class385() {
    }

    public Class385(BlockPos blockPosIn, int metadataIn, NBTTagCompound nbtIn) {
        this.blockPos = blockPosIn;
        this.metadata = metadataIn;
        this.nbt = nbtIn;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.blockPos = buf.readBlockPos();
        this.metadata = buf.readUnsignedByte();
        this.nbt = buf.readNBTTagCompoundFromBuffer();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeBlockPos(this.blockPos);
        buf.writeByte((byte)this.metadata);
        buf.writeNBTTagCompoundToBuffer(this.nbt);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleUpdateTileEntity(this);
    }

    public BlockPos getPos() {
        return this.blockPos;
    }

    public int getTileEntityType() {
        return this.metadata;
    }

    public NBTTagCompound getNbtCompound() {
        return this.nbt;
    }
}

