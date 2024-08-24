package Rename;

import net.minecraft.network.PacketBuffer;

public class Class429
implements Class441 {
    private String a;
    private String json;

    public Class429() {
    }

    @Override
    public void writePacketData(PacketBuffer packetBuffer) {
        packetBuffer.writeString(this.a);
        packetBuffer.writeString(this.json);
    }

    @Override
    public int getPacketId() {
        return 26;
    }

    public Class429(String string, String json) {
        this.a = string;
        this.json = json;
    }
}

