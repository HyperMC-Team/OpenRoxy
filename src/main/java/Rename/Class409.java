package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class Class409
implements Packet<INetHandlerPlayClient> {
    private IChatComponent header;
    private IChatComponent footer;

    public Class409() {
    }

    public Class409(IChatComponent headerIn) {
        this.header = headerIn;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.header = buf.readChatComponent();
        this.footer = buf.readChatComponent();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeChatComponent(this.header);
        buf.writeChatComponent(this.footer);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handlePlayerListHeaderFooter(this);
    }

    public IChatComponent getHeader() {
        return this.header;
    }

    public IChatComponent getFooter() {
        return this.footer;
    }
}

