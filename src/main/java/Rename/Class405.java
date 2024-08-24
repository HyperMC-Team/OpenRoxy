package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.BlockPos;
import net.minecraft.util.IChatComponent;
import net.minecraft.world.World;

public class Class405
implements Packet<INetHandlerPlayClient> {
    private World world;
    private BlockPos blockPos;
    private IChatComponent[] lines;

    public Class405() {
    }

    public Class405(World worldIn, BlockPos blockPosIn, IChatComponent[] linesIn) {
        this.world = worldIn;
        this.blockPos = blockPosIn;
        this.lines = new IChatComponent[]{linesIn[0], linesIn[1], linesIn[2], linesIn[3]};
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.blockPos = buf.readBlockPos();
        this.lines = new IChatComponent[4];
        for (int i = 0; i < 4; ++i) {
            this.lines[i] = buf.readChatComponent();
        }
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeBlockPos(this.blockPos);
        for (int i = 0; i < 4; ++i) {
            buf.writeChatComponent(this.lines[i]);
        }
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleUpdateSign(this);
    }

    public BlockPos getPos() {
        return this.blockPos;
    }

    public IChatComponent[] getLines() {
        return this.lines;
    }
}

