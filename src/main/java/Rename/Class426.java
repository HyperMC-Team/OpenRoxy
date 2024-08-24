package Rename;

import net.minecraft.network.PacketBuffer;

public class Class426
implements Class441 {
    private int key;
    private boolean state;

    public Class426() {
    }

    @Override
    public void writePacketData(PacketBuffer packetBuffer) {
        packetBuffer.writeInt(this.key);
        packetBuffer.writeBoolean(this.state);
    }

    @Override
    public int getPacketId() {
        return 3;
    }

    public Class426(int key, boolean state) {
        this.key = key;
        this.state = state;
    }
}

