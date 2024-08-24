package Rename;
/**
 * @HyperTeam
 * @author Treasure # 1337
 * @date 2024/8/23
 * @Description: thanks help for QianXia
 */

import net.minecraft.network.Packet;

public class Class36
extends Class4 {
    private Packet<?> packet;
    private boolean isNoEvent;

    public Class36(Packet<?> packet, boolean isNoEvent) {
        this.packet = packet;
        this.isNoEvent = isNoEvent;
    }

    public Packet<?> getPacket() {
        return this.packet;
    }

    public boolean isNoEvent() {
        return this.isNoEvent;
    }

    public void setPacket(Packet<?> packet) {
        this.packet = packet;
    }

    public void setNoEvent(boolean isNoEvent) {
        this.isNoEvent = isNoEvent;
    }
}

