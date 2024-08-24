package Rename;

import net.minecraft.network.PacketBuffer;

public class Class425
implements Class441 {
    private String key;

    public Class425() {
    }

    @Override
    public void writePacketData(PacketBuffer packetBuffer) {
        packetBuffer.writeString(this.key);
    }

    @Override
    public int getPacketId() {
        return 1;
    }

    public Class425(String key) {
        this.key = key;
    }

    public String getKey() {
        return this.key;
    }
}

