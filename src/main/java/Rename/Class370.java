package Rename;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class Class370
implements Packet<INetHandlerPlayClient> {
    private int leash;
    private int entityId;
    private int vehicleEntityId;

    public Class370() {
    }

    public Class370(int leashIn, Entity entityIn, Entity vehicle) {
        this.leash = leashIn;
        this.entityId = entityIn.getEntityId();
        this.vehicleEntityId = vehicle != null ? vehicle.getEntityId() : -1;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityId = buf.readInt();
        this.vehicleEntityId = buf.readInt();
        this.leash = buf.readUnsignedByte();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeInt(this.entityId);
        buf.writeInt(this.vehicleEntityId);
        buf.writeByte(this.leash);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleEntityAttach(this);
    }

    public int getLeash() {
        return this.leash;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public int getVehicleEntityId() {
        return this.vehicleEntityId;
    }
}

