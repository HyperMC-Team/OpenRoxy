package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.scoreboard.ScoreObjective;

public class Class372
implements Packet<INetHandlerPlayClient> {
    private int position;
    private String scoreName;

    public Class372() {
    }

    public Class372(int positionIn, ScoreObjective scoreIn) {
        this.position = positionIn;
        this.scoreName = scoreIn == null ? "" : scoreIn.getName();
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.position = buf.readByte();
        this.scoreName = buf.readStringFromBuffer(16);
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeByte(this.position);
        buf.writeString(this.scoreName);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleDisplayScoreboard(this);
    }

    public int func_149371_c() {
        return this.position;
    }

    public String func_149370_d() {
        return this.scoreName;
    }
}

