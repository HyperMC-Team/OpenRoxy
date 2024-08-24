package Rename;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class Class367
implements Packet<INetHandlerPlayClient> {
    private int entityId;
    private int type;

    public Class367() {
    }

    public Class367(Entity ent, int animationType) {
        this.entityId = ent.getEntityId();
        this.type = animationType;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityId = buf.readVarIntFromBuffer();
        this.type = buf.readUnsignedByte();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.entityId);
        buf.writeByte(this.type);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleAnimation(this);
    }

    public int getEntityID() {
        return this.entityId;
    }

    public int getAnimationType() {
        return this.type;
    }
}

