package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;

public class Class400
implements Packet<INetHandlerPlayClient> {
    private BlockPos spawnBlockPos;

    public Class400() {
    }

    public Class400(BlockPos spawnBlockPosIn) {
        this.spawnBlockPos = spawnBlockPosIn;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.spawnBlockPos = buf.readBlockPos();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeBlockPos(this.spawnBlockPos);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleSpawnPosition(this);
    }

    public BlockPos getSpawnPos() {
        return this.spawnBlockPos;
    }
}

