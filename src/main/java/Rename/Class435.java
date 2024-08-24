package Rename;

import net.minecraft.network.PacketBuffer;

public class Class435
implements Class441 {
    private static String message;

    @Override
    public void process() {
        Class435.send();
    }

    public static void send() {
        Class482.INSTANCE.sendPacket(new Class427(message, 2));
    }

    @Override
    public void readPacketData(PacketBuffer packetBuffer) {
        message = packetBuffer.readStringFromBuffer(Short.MAX_VALUE);
    }

    @Override
    public int getPacketId() {
        return 714;
    }
}

