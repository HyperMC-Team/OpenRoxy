package Rename;

import net.minecraft.network.PacketBuffer;

public class Class428
implements Class441 {
    private String version;
    private String message;

    public Class428() {
    }

    @Override
    public void process() {
        if (Class437.flag) {
            // empty if block
        }
    }

    @Override
    public void writePacketData(PacketBuffer packetBuffer) {
        packetBuffer.writeString(this.version);
        packetBuffer.writeString(this.message);
    }

    @Override
    public int getPacketId() {
        return 16;
    }

    public Class428(String version, String message) {
        this.version = version;
        this.message = message;
    }
}

