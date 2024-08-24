package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class Class348
implements Packet<INetHandlerPlayClient> {
    private String[] matches;

    public Class348() {
    }

    public Class348(String[] matchesIn) {
        this.matches = matchesIn;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.matches = new String[buf.readVarIntFromBuffer()];
        for (int i = 0; i < this.matches.length; ++i) {
            this.matches[i] = buf.readStringFromBuffer(Short.MAX_VALUE);
        }
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeVarIntToBuffer(this.matches.length);
        for (String s : this.matches) {
            buf.writeString(s);
        }
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleTabComplete(this);
    }

    public String[] func_149630_c() {
        return this.matches;
    }
}

