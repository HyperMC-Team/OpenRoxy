package Rename;

import java.io.IOException;
import java.util.UUID;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.world.WorldServer;

public class Class358
implements Packet<INetHandlerPlayServer> {
    private UUID id;

    public Class358() {
    }

    public Class358(UUID id) {
        this.id = id;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.id = buf.readUuid();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeUuid(this.id);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.handleSpectate(this);
    }

    public Entity getEntity(WorldServer worldIn) {
        return worldIn.getEntityFromUuid(this.id);
    }
}

