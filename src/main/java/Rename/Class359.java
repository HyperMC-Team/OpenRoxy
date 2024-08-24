package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class Class359
implements Packet<INetHandlerPlayServer> {
    private int key;

    public Class359() {
    }

    public Class359(int key) {
        this.key = key;
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processKeepAlive(this);
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.key = buf.readVarIntFromBuffer();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.key);
    }

    public int getKey() {
        return this.key;
    }
}

