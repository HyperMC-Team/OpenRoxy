package Rename;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class Class410
implements Packet<INetHandlerPlayClient> {
    public int entityId;

    public Class410() {
    }

    public Class410(Entity entityIn) {
        this.entityId = entityIn.getEntityId();
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityId = buf.readVarIntFromBuffer();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.entityId);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleCamera(this);
    }

    public Entity getEntity(World worldIn) {
        return worldIn.getEntityByID(this.entityId);
    }
}

