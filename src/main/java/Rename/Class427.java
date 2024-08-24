package Rename;

import net.minecraft.network.PacketBuffer;

public class Class427
implements Class441 {
    private String message;
    private String message3;
    private int n;
    private int n2;

    public Class427(String message) {
        this.message = message;
        this.message3 = message;
    }

    public Class427(String message, int n) {
        this.message = message;
        this.message3 = message;
        this.n = n;
    }

    public Class427() {
    }

    @Override
    public void writePacketData(PacketBuffer packetBuffer) {
        packetBuffer.writeInt(this.n);
        packetBuffer.writeInt(this.n2);
        packetBuffer.writeString(this.message);
        packetBuffer.writeString(this.message);
        packetBuffer.writeString(this.message3);
    }

    @Override
    public int getPacketId() {
        return 4;
    }

    public Class427(String message, String message3, int n, int n2) {
        this.message = message;
        this.message3 = message3;
        this.n = n;
        this.n2 = n2;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setMessage3(String message3) {
        this.message3 = message3;
    }

    public void setN(int n) {
        this.n = n;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }
}

