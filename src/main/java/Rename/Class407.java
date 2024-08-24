package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.potion.PotionEffect;

public class Class407
implements Packet<INetHandlerPlayClient> {
    private int entityId;
    private int effectId;

    public Class407() {
    }

    public Class407(int entityIdIn, PotionEffect effect) {
        this.entityId = entityIdIn;
        this.effectId = effect.getPotionID();
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityId = buf.readVarIntFromBuffer();
        this.effectId = buf.readUnsignedByte();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.entityId);
        buf.writeByte(this.effectId);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleRemoveEntityEffect(this);
    }

    public int getEntityId() {
        return this.entityId;
    }

    public int getEffectId() {
        return this.effectId;
    }
}

