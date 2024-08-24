package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class Class371
implements Packet<INetHandlerPlayClient> {
    private int id;

    public Class371() {
    }

    public Class371(int idIn) {
        this.id = idIn;
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleKeepAlive(this);
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.id = buf.readVarIntFromBuffer();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.id);
    }

    public int func_149134_c() {
        return this.id;
    }
}

