package Rename;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;

public class Class334
implements Packet<INetHandlerPlayServer> {
    private int entityId;
    private Action action;
    private Vec3 hitVec;

    public Class334() {
    }

    public Class334(Entity entity, Action action) {
        this.entityId = entity.getEntityId();
        this.action = action;
    }

    public Class334(Entity entity, Vec3 hitVec) {
        this(entity, Action.INTERACT_AT);
        this.hitVec = hitVec;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityId = buf.readVarIntFromBuffer();
        this.action = buf.readEnumValue(Action.class);
        if (this.action == Action.INTERACT_AT) {
            this.hitVec = new Vec3(buf.readFloat(), buf.readFloat(), buf.readFloat());
        }
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.entityId);
        buf.writeEnumValue(this.action);
        if (this.action == Action.INTERACT_AT) {
            buf.writeFloat((float)this.hitVec.xCoord);
            buf.writeFloat((float)this.hitVec.yCoord);
            buf.writeFloat((float)this.hitVec.zCoord);
        }
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processUseEntity(this);
    }

    public Entity getEntityFromWorld(World worldIn) {
        return worldIn.getEntityByID(this.entityId);
    }

    public Action getAction() {
        return this.action;
    }

    public Vec3 getHitVec() {
        return this.hitVec;
    }

    public int getEntityId() {
        return this.entityId;
    }

    public static enum Action {
        INTERACT,
        ATTACK,
        INTERACT_AT;

    }
}

