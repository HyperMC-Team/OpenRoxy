package Rename;

import net.minecraft.network.PacketBuffer;

public class Class436
implements Class441 {
    private int key;

    @Override
    public void process() {
        Class482.INSTANCE.getOutstandingKeys().add(this.key);
    }

    @Override
    public void writePacketData(PacketBuffer packetBuffer) {
        packetBuffer.writeInt(this.key);
    }

    @Override
    public void readPacketData(PacketBuffer packetBuffer) {
        this.key = packetBuffer.readInt();
    }

    @Override
    public int getPacketId() {
        return 723;
    }
}

