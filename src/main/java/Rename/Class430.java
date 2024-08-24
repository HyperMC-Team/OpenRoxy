package Rename;

import net.minecraft.network.PacketBuffer;

public class Class430
implements Class441 {
    private String message;
    private String message2;

    @Override
    public void process() {
    }

    @Override
    public void readPacketData(PacketBuffer packetBuffer) {
        this.message = packetBuffer.readStringFromBuffer(Short.MAX_VALUE);
        this.message2 = packetBuffer.readStringFromBuffer(Short.MAX_VALUE);
    }

    @Override
    public int getPacketId() {
        return 67;
    }
}

