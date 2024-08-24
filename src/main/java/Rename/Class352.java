package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayServer;
import net.minecraft.util.BlockPos;
import org.apache.commons.lang3.StringUtils;

public class Class352
implements Packet<INetHandlerPlayServer> {
    private String message;
    private BlockPos targetBlock;

    public Class352() {
    }

    public Class352(String msg) {
        this(msg, null);
    }

    public Class352(String msg, BlockPos target) {
        this.message = msg;
        this.targetBlock = target;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.message = buf.readStringFromBuffer(Short.MAX_VALUE);
        boolean flag = buf.readBoolean();
        if (flag) {
            this.targetBlock = buf.readBlockPos();
        }
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeString(StringUtils.substring((String)this.message, (int)0, (int)Short.MAX_VALUE));
        boolean flag = this.targetBlock != null;
        buf.writeBoolean(flag);
        if (flag) {
            buf.writeBlockPos(this.targetBlock);
        }
    }

    @Override
    public void processPacket(INetHandlerPlayServer handler) {
        handler.processTabComplete(this);
    }

    public String getMessage() {
        return this.message;
    }

    public BlockPos getTargetBlock() {
        return this.targetBlock;
    }
}

