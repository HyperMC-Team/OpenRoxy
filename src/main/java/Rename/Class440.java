package Rename;

import net.minecraft.network.PacketBuffer;

public class Class440
implements Class3 {
    private final String name;

    public void sendToServer(String name, PacketBuffer buffer) {
        Class440.mc.thePlayer.sendQueue.addToSendQueue(new Class361(name, buffer));
    }

    public Class440(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}

