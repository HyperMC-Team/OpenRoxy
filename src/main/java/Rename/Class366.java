package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;

public class Class366
implements Packet<INetHandlerPlayServer> {
    private String hash;
    private Action status;

    public Class366() {
    }

    public Class366(String hashIn, Action statusIn) {
        if (hashIn.length() > 40) {
            hashIn = hashIn.substring(0, 40);
        }
        this.hash = hashIn;
        this.status = statusIn;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.hash = buf.readStringFromBuffer(40);
        this.status = buf.readEnumValue(Action.class);
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(this.hash);
        buf.writeEnumValue(this.status);
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.handleResourcePackStatus(this);
    }

    public static enum Action {
        SUCCESSFULLY_LOADED,
        DECLINED,
        FAILED_DOWNLOAD,
        ACCEPTED;

    }
}

