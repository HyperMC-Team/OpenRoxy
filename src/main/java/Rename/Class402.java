package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class Class402
implements Packet<INetHandlerPlayClient> {
    private int[] entityIDs;

    public Class402() {
    }

    public Class402(int ... entityIDsIn) {
        this.entityIDs = entityIDsIn;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.entityIDs = new int[buf.readVarIntFromBuffer()];
        for (int i = 0; i < this.entityIDs.length; ++i) {
            this.entityIDs[i] = buf.readVarIntFromBuffer();
        }
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.entityIDs.length);
        for (int i = 0; i < this.entityIDs.length; ++i) {
            buf.writeVarIntToBuffer(this.entityIDs[i]);
        }
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleDestroyEntities(this);
    }

    public int[] getEntityIDs() {
        return this.entityIDs;
    }
}

