package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class Class377
implements Packet<INetHandlerPlayClient> {
    private String channel;
    private PacketBuffer data;

    public Class377() {
    }

    public Class377(String channelName, PacketBuffer dataIn) {
        this.channel = channelName;
        this.data = dataIn;
        if (dataIn.writerIndex() > 0x100000) {
            throw new IllegalArgumentException("Payload may not be larger than 1048576 bytes");
        }
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.channel = buf.readStringFromBuffer(20);
        int i = buf.readableBytes();
        if (i < 0 || i > 0x100000) {
            throw new IOException("Payload may not be larger than 1048576 bytes");
        }
        this.data = new PacketBuffer(buf.readBytes(i));
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(this.channel);
        buf.writeBytes(this.data);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleCustomPayload(this);
    }

    public String getChannelName() {
        return this.channel;
    }

    public PacketBuffer getBufferData() {
        return this.data;
    }
}

