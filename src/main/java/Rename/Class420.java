package Rename;

import java.io.IOException;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.network.play.INetHandlerPlayClient;
import net.minecraft.util.IChatComponent;

public class Class420
implements Packet<INetHandlerPlayClient> {
    private IChatComponent chatComponent;
    private byte type;

    public Class420() {
    }

    public Class420(IChatComponent component) {
        this(component, (byte) 1);
    }

    public Class420(IChatComponent message, byte typeIn) {
        this.chatComponent = message;
        this.type = typeIn;
    }

    @Override
    public void readPacketData(PacketBuffer buf) throws IOException {
        this.chatComponent = buf.readChatComponent();
        this.type = buf.readByte();
    }

    @Override
    public void writePacketData(PacketBuffer buf) throws IOException {
        buf.writeChatComponent(this.chatComponent);
        buf.writeByte(this.type);
    }

    @Override
    public void processPacket(INetHandlerPlayClient handler) {
        handler.handleChat(this);
    }

    public IChatComponent getChatComponent() {
        return this.chatComponent;
    }

    public boolean isChat() {
        return this.type == 1 || this.type == 2;
    }

    public byte getType() {
        return this.type;
    }
}

