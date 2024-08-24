package Rename;

import java.io.IOException;
import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.world.World;

public class Class424
implements Packet<INetHandlerPlayClient> {
    private int entityId;
    private NBTTagCompound tagCompound;

    public Class424() {
    }

    public Class424(int entityIdIn, NBTTagCompound tagCompoundIn) {
        this.entityId = entityIdIn;
        this.tagCompound = tagCompoundIn;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityId = buf.readVarIntFromBuffer();
        this.tagCompound = buf.readNBTTagCompoundFromBuffer();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.entityId);
        buf.writeNBTTagCompoundToBuffer(this.tagCompound);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleEntityNBT(this);
    }

    public NBTTagCompound getTagCompound() {
        return this.tagCompound;
    }

    public Entity getEntity(World worldIn) {
        return worldIn.getEntityByID(this.entityId);
    }
}

