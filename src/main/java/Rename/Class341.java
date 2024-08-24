package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;

public class Class341
implements Packet<INetHandlerPlayClient> {
    private long totalWorldTime;
    private long worldTime;

    public Class341() {
    }

    public Class341(long totalWorldTimeIn, long totalTimeIn, boolean doDayLightCycle) {
        this.totalWorldTime = totalWorldTimeIn;
        this.worldTime = totalTimeIn;
        if (!doDayLightCycle) {
            this.worldTime = -this.worldTime;
            if (this.worldTime == 0L) {
                this.worldTime = -1L;
            }
        }
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.totalWorldTime = buf.readLong();
        this.worldTime = buf.readLong();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeLong(this.totalWorldTime);
        buf.writeLong(this.worldTime);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleTimeUpdate(this);
    }

    public long getTotalWorldTime() {
        return this.totalWorldTime;
    }

    public long getWorldTime() {
        return this.worldTime;
    }
}

